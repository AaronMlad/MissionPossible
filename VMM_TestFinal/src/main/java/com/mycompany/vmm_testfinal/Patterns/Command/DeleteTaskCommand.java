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
import java.awt.Container;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
/**
 *
 * @author racme
 */
public class DeleteTaskCommand implements Command{
    
    CommandControl taskControl = new CommandControl();
    private Task task;
    private static JPanel panel;
    
    public DeleteTaskCommand(){
    }
    
    public DeleteTaskCommand(JPanel panel){
        this.panel = panel;
    }
    
    public void deleteTask(Task task){
        this.task = task;
    }
    
    @Override
    public void execute() {
        if (task == null) {
            return;
        }
        try {
            Connection con = DBConnection.getInstance().getCon();
            if (task instanceof ProjectTask) {
                deleteProjectFromDatabase(con, (ProjectTask) task);
            } else {
                deleteTodoFromDatabase(con, task instanceof SimpleTask ? (SimpleTask) task : null);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        task.Delete();

        UpdateBodyCommand ubc = new UpdateBodyCommand();
        ubc.newBody(null);
        taskControl.setCommand(ubc);
        taskControl.clickedButton();

        removePanelFromUi();
    }

    private void deleteTodoFromDatabase(Connection con, SimpleTask simpleTask) throws SQLException {
        Integer todoId = resolveTodoId(con, simpleTask);
        if (todoId == null) {
            System.out.println("Skip todo deletion: no id found for title=" + (simpleTask != null ? simpleTask.getTitle() : "<unknown>"));
            return;
        }
        String deleteSql = "DELETE FROM todos WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(deleteSql)) {
            ps.setInt(1, todoId);
            ps.executeUpdate();
        }
    }

    private void deleteProjectFromDatabase(Connection con, ProjectTask projectTask) throws SQLException {
        Integer projectId = resolveProjectId(con, projectTask);
        if (projectId == null) {
            System.out.println("Skip project deletion: no project_id found for title=" + (projectTask != null ? projectTask.getTitle() : "<unknown>"));
            return;
        }

        String deleteTodosSql = "DELETE FROM todos WHERE project_id = ?";
        try (PreparedStatement ps = con.prepareStatement(deleteTodosSql)) {
            ps.setInt(1, projectId);
            ps.executeUpdate();
        }

        String deleteProjectSql = "DELETE FROM projects WHERE project_id = ?";
        try (PreparedStatement ps = con.prepareStatement(deleteProjectSql)) {
            ps.setInt(1, projectId);
            ps.executeUpdate();
        }
    }

    private Integer resolveTodoId(Connection con, SimpleTask simpleTask) throws SQLException {
        if (simpleTask != null) {
            int currentId = simpleTask.getId();
            if (currentId > 0) {
                return currentId;
            }
        }
        if ((simpleTask == null) || simpleTask.getTitle() == null || simpleTask.getTitle().isBlank()) {
            return null;
        }
        return fetchTodoIdByTitle(con, simpleTask.getTitle());
    }

    private Integer resolveProjectId(Connection con, ProjectTask projectTask) throws SQLException {
        if (projectTask != null) {
            int currentId = projectTask.getId();
            if (currentId > 0) {
                return currentId;
            }
        }
        if (projectTask == null || projectTask.getTitle() == null || projectTask.getTitle().isBlank()) {
            return null;
        }
        return fetchProjectIdByTitle(con, projectTask.getTitle());
    }

    private Integer fetchTodoIdByTitle(Connection con, String title) throws SQLException {
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

    private Integer fetchProjectIdByTitle(Connection con, String title) throws SQLException {
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

    private void removePanelFromUi() {
        if (panel == null) {
            return;
        }
        Container parentContainer = panel.getParent();
        if (parentContainer == null) {
            return;
        }

        parentContainer.remove(panel);
        parentContainer.revalidate();
        parentContainer.repaint();

        Container ancestor = parentContainer.getParent();
        while (ancestor != null) {
            ancestor.revalidate();
            ancestor.repaint();
            ancestor = ancestor.getParent();
        }
    }
    
}
