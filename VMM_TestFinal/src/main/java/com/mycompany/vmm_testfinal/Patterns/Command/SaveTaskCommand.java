/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.vmm_testfinal.Patterns.Command;

import com.mycompany.vmm_testfinal.Patterns.Interfaces.Command;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;
import com.mycompany.vmm_testfinal.Patterns.Singleton.DBConnection;
import com.mycompany.vmm_testfinal.Patterns.Tasks.ProjectTask;
import com.mycompany.vmm_testfinal.Patterns.Tasks.SimpleTask;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 *
 * @author racme
 */
public class SaveTaskCommand implements Command{
    CommandControl taskControl = new CommandControl();
    private static Task task;
    private String[] information;
    
    /////////////////////////////
    
    public SaveTaskCommand(){
    }
    
    public SaveTaskCommand(Task task){
        this.task = task;
    }
    
    public void saveTask(String[] information){
        this.information = information;
    }
    
    /////////////////////////////
    
    @Override
    public void execute() {
        if (information == null || information.length < 4) {
            return;
        }
        try {
            Connection con = DBConnection.getInstance().getCon();

            String title = information[0] != null ? information[0].trim() : "";
            String description = cleanDescription(information[1]);
            String statusLabel = cleanStatus(information[2]);
            LocalDate parsedDueDate = parseDueDate(cleanDeadline(information[3]));

            Task currentTask = task;
            if (currentTask instanceof ProjectTask) {
                saveProject(con, (ProjectTask) currentTask, title, description, statusLabel, parsedDueDate);
            } else {
                boolean isDone = "completed".equalsIgnoreCase(statusLabel);
                saveTodo(con, currentTask instanceof SimpleTask ? (SimpleTask) currentTask : null, title, description, isDone, parsedDueDate);
            }

            if (currentTask != null) {
                currentTask.Save(information);
                if (currentTask instanceof SimpleTask) {
                    SimpleTask simpleTask = (SimpleTask) currentTask;
                    simpleTask.setProjectId(determineProjectId(simpleTask));
                }
            }

            UpdateTaskPanelCommand utpc = new UpdateTaskPanelCommand();
            utpc.updateLabel(currentTask);
            taskControl.setCommand(utpc);
            taskControl.clickedButton();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String cleanDescription(String rawDescription) {
        if (rawDescription == null) {
            return "";
        }
        return rawDescription.replaceFirst("(?i)^\\s*descr(?:iption)?\\s*:?\\s*", "").trim();
    }

    private String cleanStatus(String rawStatus) {
        if (rawStatus == null) {
            return "";
        }
        return rawStatus.replaceFirst("(?i)^\\s*status\\s*:?\\s*", "").trim();
    }

    private String cleanDeadline(String rawDeadline) {
        if (rawDeadline == null) {
            return "";
        }
        return rawDeadline.replaceFirst("(?i)^\\s*deadline\\s*:?\\s*", "").trim();
    }

    private LocalDate parseDueDate(String deadlineValue) {
        if (deadlineValue == null || deadlineValue.isBlank()) {
            return null;
        }

        String[] patterns = {"MM-dd-uuuu", "uuuu-MM-dd"};
        for (String pattern : patterns) {
            try {
                return LocalDate.parse(deadlineValue, DateTimeFormatter.ofPattern(pattern));
            } catch (DateTimeParseException ignored) {
            }
        }
        return null;
    }

    private void saveTodo(Connection con, SimpleTask simpleTask, String title, String description, boolean isDone, LocalDate parsedDueDate) throws SQLException {
        int projectId = determineProjectId(simpleTask);
        Integer todoId = resolveExistingTodoId(con, simpleTask, title);
        java.sql.Date sqlDate = parsedDueDate != null ? java.sql.Date.valueOf(parsedDueDate) : null;

        if (todoId != null) {
            String updateSql = "UPDATE todos SET title = ?, descr = ?, status = ?, due_date = ?, project_id = ? WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(updateSql)) {
                ps.setString(1, title);
                ps.setString(2, description);
                ps.setBoolean(3, isDone);
                if (sqlDate != null) {
                    ps.setDate(4, sqlDate);
                } else {
                    ps.setNull(4, Types.DATE);
                }
                ps.setInt(5, projectId);
                ps.setInt(6, todoId);
                ps.executeUpdate();
            }
            if (simpleTask != null) {
                simpleTask.setId(todoId);
                simpleTask.setProjectId(projectId);
            }
        } else {
            String insertSql = "INSERT INTO todos (project_id, title, descr, due_date, status) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, projectId);
                ps.setString(2, title);
                ps.setString(3, description);
                if (sqlDate != null) {
                    ps.setDate(4, sqlDate);
                } else {
                    ps.setNull(4, Types.DATE);
                }
                ps.setBoolean(5, isDone);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next() && simpleTask != null) {
                        simpleTask.setId(rs.getInt(1));
                        simpleTask.setProjectId(projectId);
                    }
                }
            }
        }
    }

    private void saveProject(Connection con, ProjectTask projectTask, String title, String description, String statusLabel, LocalDate parsedDueDate) throws SQLException {
        String normalizedStatus = statusLabel.isBlank() ? "Unknown" : statusLabel;
        Integer projectId = resolveExistingProjectId(con, projectTask, title);
        java.sql.Date sqlDate = parsedDueDate != null ? java.sql.Date.valueOf(parsedDueDate) : null;
        boolean statusValue = interpretStatusAsDone(normalizedStatus);

        if (projectId != null) {
            String updateSql = "UPDATE projects SET name = ?, descr = ?, deadline = ?, status = ? WHERE project_id = ?";
            try (PreparedStatement ps = con.prepareStatement(updateSql)) {
                ps.setString(1, title);
                ps.setString(2, description);
                if (sqlDate != null) {
                    ps.setDate(3, sqlDate);
                } else {
                    ps.setNull(3, Types.DATE);
                }
                ps.setBoolean(4, statusValue);
                ps.setInt(5, projectId);
                ps.executeUpdate();
            }
            projectTask.setId(projectId);
        } else {
            String insertSql = "INSERT INTO projects (name, descr, deadline, status) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, title);
                ps.setString(2, description);
                if (sqlDate != null) {
                    ps.setDate(3, sqlDate);
                } else {
                    ps.setNull(3, Types.DATE);
                }
                ps.setBoolean(4, statusValue);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        projectTask.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    private int determineProjectId(SimpleTask simpleTask) {
        if (simpleTask == null) {
            return 0;
        }
        ProjectTask parentProject = simpleTask.getParentProject();
        if (parentProject != null) {
            return parentProject.getId();
        }
        Integer projectId = simpleTask.getProjectId();
        return projectId != null ? projectId : 0;
    }

    private Integer resolveExistingTodoId(Connection con, SimpleTask simpleTask, String updatedTitle) throws SQLException {
        if (simpleTask != null) {
            int currentId = simpleTask.getId();
            if (currentId > 0) {
                Integer byId = fetchTodoIdById(con, currentId);
                if (byId != null) {
                    return byId;
                }
            }
        }
        if (updatedTitle == null || updatedTitle.isBlank()) {
            return null;
        }
        return fetchTodoIdByTitle(con, updatedTitle);
    }

    private Integer resolveExistingProjectId(Connection con, ProjectTask projectTask, String updatedTitle) throws SQLException {
        if (projectTask != null) {
            int currentId = projectTask.getId();
            if (currentId > 0) {
                Integer byId = fetchProjectIdById(con, currentId);
                if (byId != null) {
                    return byId;
                }
            }
        }
        if (updatedTitle == null || updatedTitle.isBlank()) {
            return null;
        }
        return fetchProjectIdByTitle(con, updatedTitle);
    }

    private Integer fetchTodoIdByTitle(Connection con, String title) throws SQLException {
        if (title == null || title.isBlank()) {
            return null;
        }
        String findSql = "SELECT id FROM todos WHERE title = ? LIMIT 1";
        try (PreparedStatement ps = con.prepareStatement(findSql)) {
            ps.setString(1, title.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return null;
    }

    private Integer fetchTodoIdById(Connection con, int id) throws SQLException {
        String findSql = "SELECT id FROM todos WHERE id = ? LIMIT 1";
        try (PreparedStatement ps = con.prepareStatement(findSql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return null;
    }

    private Integer fetchProjectIdByTitle(Connection con, String title) throws SQLException {
        if (title == null || title.isBlank()) {
            return null;
        }
        String findSql = "SELECT project_id FROM projects WHERE name = ? LIMIT 1";
        try (PreparedStatement ps = con.prepareStatement(findSql)) {
            ps.setString(1, title.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("project_id");
                }
            }
        }
        return null;
    }

    private Integer fetchProjectIdById(Connection con, int id) throws SQLException {
        String findSql = "SELECT project_id FROM projects WHERE project_id = ? LIMIT 1";
        try (PreparedStatement ps = con.prepareStatement(findSql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("project_id");
                }
            }
        }
        return null;
    }

    private boolean interpretStatusAsDone(String statusLabel) {
        if (statusLabel == null) {
            return false;
        }
        String normalized = statusLabel.trim().toLowerCase();
        return normalized.contains("complete") || normalized.contains("done");
    }
}
