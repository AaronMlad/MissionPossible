/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vmm_testfinal.UI.GUI.Components;

import com.mycompany.vmm_testfinal.Patterns.Command.CommandControl;
import com.mycompany.vmm_testfinal.Patterns.Command.DeleteTaskCommand;
import com.mycompany.vmm_testfinal.Patterns.Command.SaveTaskCommand;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Observer;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author racme
 */
public class taskBodyFactory extends JPanel{
    
    static JTextField taskTitle = new JTextField();
    static JTextArea taskDescription;
    static JLabel Status = new JLabel();
    static JLabel Deadline = new JLabel();
    
    public static JPanel createBody(Task task, int width, int height){
        
        CommandControl taskControl = new CommandControl();
        
//        JTextField taskTitle = new JTextField();
//        JTextArea taskDescription;
//        JLabel Status = new JLabel();
//        JLabel Deadline = new JLabel();
        
        JLabel Status_Icon = new JLabel();
        JScrollPane scrollPane;
        
        //BODY
        JPanel Body = new JPanel();
        Body.setBackground(Color.BLACK);
        Body.setLayout(new BorderLayout());
        
            JPanel Body_NorthLip = new JPanel();
            Body_NorthLip.setPreferredSize(new Dimension(Integer.MAX_VALUE,70));
            Body_NorthLip.setBackground(Color.LIGHT_GRAY);
            Body_NorthLip.setLayout(new BorderLayout());
        
        //
        JPanel Body_Title = new JPanel();
        Body_Title.setBackground(Color.DARK_GRAY); //COLOR
        Body_Title.setPreferredSize(new Dimension(450,Integer.MAX_VALUE));
        Body_Title.setLayout(new BorderLayout());
        Body_Title.setBorder(BorderFactory.createEmptyBorder(5,15,5,15));
        
            //TASK TITLE
            taskTitle.setOpaque(false);
            taskTitle.setBorder(null);
            taskTitle.setForeground(Color.WHITE);
            taskTitle.setCaretColor(Color.WHITE);
            taskTitle.setFont(Roboto(28));
            
            taskTitle.setText(task.getTitle());
            taskTitle.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            
            Body_Title.add(taskTitle);
            
            //TASK DESCRIPTION
            
            taskDescription = new JTextArea(task.getDescription());
            taskDescription.setForeground(Color.WHITE);
            taskDescription.setCaretColor(Color.WHITE);
            taskDescription.setOpaque(false);
            taskDescription.setBorder(null);
            taskDescription.setLineWrap(true);
            taskDescription.setWrapStyleWord(true);
            taskDescription.setFont(Roboto(12)); ///OPTIMIZE FONT LATER
//            taskDescription.setText(" Description : ");
            taskDescription.setText(" Description : "+task.getDescription());
            ///

            scrollPane = new JScrollPane(taskDescription,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setPreferredSize(new Dimension((int)((width-220)*0.8),
                                                      (int)((width-170)*0.7)));
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            
        //
        JPanel Body_Options = new JPanel();
        Body_Options.setLayout(new GridLayout(2,1,0,0));
            
            JPanel Body_Status = new JPanel();
            Body_Status.setBackground(Color.DARK_GRAY);     
            Body_Status.setLayout(new BorderLayout(0,0));

                Status.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
                Status.setForeground(Color.WHITE);
                Status.setFont(Roboto(14));
                Status.setText(" Status : Unknown ");
                
                Status.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseEntered(MouseEvent e){
                        Status.setOpaque(true);
                        Status.setBackground(Color.LIGHT_GRAY); //--------------CHANGE
                        Status.repaint();
                    };
                    @Override
                    public void mouseExited(MouseEvent e){
                        Status.setOpaque(false);
                        Status.repaint();
                    }
                    @Override
                    public void mouseClicked(MouseEvent e){
                        String[] options = {"Complete","Ongoing","Failed"};

                        int choice = JOptionPane.showOptionDialog(null, "Set New Status to:", "Set Status", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                        System.out.println(choice);
                        switch(choice){
                            case 0:
                                Status.setText(" Status : Completed");
//                                Status_Icon.setIcon(completed);
                                break;
                            case 1:
                                Status.setText(" Status : Ongoing");
//                                Status_Icon.setIcon(ongoing);
                                break;
                            case 2:
                                Status.setText(" Status : Failed");
//                                Status_Icon.setIcon(failed);
                                break;
                            default:
                                Status.setText(" Status : Unknown");
//                                Status_Icon.setIcon(unknown);
                                break;

                        }
                        Status_Icon.revalidate();
                        Status_Icon.repaint();
                        Status.revalidate();
                        Status.repaint();
                    }
                });
            JPanel Body_Status_Icon = new JPanel();
            Body_Status_Icon.setPreferredSize(new Dimension(35,35));
            Body_Status_Icon.setBackground(Color.DARK_GRAY);
            Body_Status_Icon.setLayout(new BorderLayout(0,0));
            Body_Status_Icon.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
                 
                Status_Icon.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
//                Status_Icon.setIcon(unknown);
                Status_Icon.setHorizontalAlignment(JLabel.CENTER);
                Status_Icon.setVerticalAlignment(JLabel.CENTER);
                
            JPanel Body_Status_Text = new JPanel();
            Body_Status_Text.setBackground(Color.DARK_GRAY);
            Body_Status_Text.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
            Body_Status_Text.setLayout(new BorderLayout(0,0));
            
            //ADD EVERYTHING
            Body_Status_Icon.add(Status_Icon);
            Body_Status_Text.add(Status);
            Body_Status.add(Body_Status_Icon,BorderLayout.WEST);
            Body_Status.add(Body_Status_Text,BorderLayout.CENTER);
            
            //DEADLINE
            
            JPanel Body_Deadline = new JPanel();
            Body_Deadline.setLayout(new BorderLayout());
            
                JPanel Body_Deadline_Icon = new JPanel();
                Body_Deadline_Icon.setPreferredSize(new Dimension(35,35));
                Body_Deadline_Icon.setBackground(Color.DARK_GRAY);
                Body_Deadline_Icon.setLayout(new BorderLayout(0,0));
                Body_Deadline_Icon.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));

                JPanel Body_Deadline_Text = new JPanel();
                Body_Deadline_Text.setBackground(Color.DARK_GRAY);
                Body_Deadline_Text.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
                Body_Deadline_Text.setLayout(new BorderLayout(0,0));

                JLabel Deadline_Icon = new JLabel();
                Deadline_Icon.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
    //            Deadline_Icon.setIcon(calendar_white);
                Deadline_Icon.setHorizontalAlignment(JLabel.CENTER);
                Deadline_Icon.setVerticalAlignment(JLabel.CENTER);

                Deadline.setFont(Roboto(14));
                Deadline.setForeground(Color.WHITE);
                Deadline.setText(" Deadline : ");
                Deadline.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
                
                Deadline.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseEntered(MouseEvent e){
                        Deadline.setOpaque(true);
                        Deadline.setBackground(Color.LIGHT_GRAY); //--------------CHANGE
                        Deadline.repaint();
                    };
                    @Override
                    public void mouseExited(MouseEvent e){
                        Deadline.setOpaque(false);
                        Deadline.repaint();
                    }
                    @Override
                    public void mouseClicked(MouseEvent e){
                        boolean valid = false;
                        String dateChosen = "";
                        int month = 0;
                        int day = 0;
                        int year = 0;
                        while(!valid){
                            dateChosen = JOptionPane.showInputDialog(null,"Input Date: \n(Follow the Format MM-DD-YYYY)");
                            String[] date = dateChosen.split("-");

                            try{
                                month = Integer.parseInt(date[0]);
                                day = Integer.parseInt(date[1]);
                                year = Integer.parseInt(date[2]);
                            }catch(Exception ex){
                                System.out.println("ouch");
                            }

                            System.out.println(month+" "+day+" "+year);

                            if(year>0){
                                if(month<=12&&month>0){
                                    if(month==2&&day<=28){
                                        valid= !valid;
                                    }else if((month==4||month==6||month==9||month==11)&&day<=30){
                                        valid= !valid;
                                    }else if(day<=31){
                                        valid= !valid;
                                    }
                                }
                            }
                        }
                        Deadline.setText(" Deadline: "+dateChosen);
                    }
                });
            Body_Deadline_Text.add(Deadline);
            Body_Deadline_Icon.add(Deadline_Icon);
            Body_Deadline.add(Body_Deadline_Icon, BorderLayout.WEST);
            Body_Deadline.add(Body_Deadline_Text);
            
            //BODY SETTINGS
            JPanel Body_Settings = new JPanel();
            Body_Settings.setBackground(Color.DARK_GRAY);
            Body_Settings.setPreferredSize(new Dimension(150,Integer.MAX_VALUE));
            Body_Settings.setLayout(new FlowLayout(FlowLayout.CENTER,8,4));
            
                JLabel Save_Button = new JLabel();
                Save_Button.setPreferredSize(new Dimension(65,65));
//                Save_Button.setIcon(save);
                Save_Button.setHorizontalAlignment(JLabel.CENTER);
                Save_Button.setVerticalAlignment(JLabel.CENTER);
                Save_Button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                
                Save_Button.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseEntered(MouseEvent e){
                        Save_Button.setOpaque(true);
                        Save_Button.setBackground(Color.LIGHT_GRAY); //--------------CHANGE
                        Save_Button.repaint();
                    };
                    @Override
                    public void mouseExited(MouseEvent e){
                        Save_Button.setOpaque(false);
                        Save_Button.repaint();
                    }
                    @Override
                    public void mouseClicked(MouseEvent e){
                        System.out.println("Task Saved");
                        
                        String[] information = new String[4];
                        information[0] = taskTitle.getText();
                        information[1] = taskDescription.getText();
                        information[2] = Status.getText();
                        information[3] = Deadline.getText();
                        
                        SaveTaskCommand stc = new SaveTaskCommand();
//                        Observer tpf = new taskPanelFactory();
//                        stc.addObserver(tpf);
                        
                        stc.saveTask(information);
                        taskControl.setCommand(stc);
                        taskControl.clickedButton();

//                          task.Save(information);

//                        String type = "";
//                        String title = taskTitle.getText();
//                        String description = taskDescription.getText();
//                        String status = Status.getText().substring(9);
//                        String deadline = Deadline.getText().substring(11);
//
//                        System.out.println(title+" "+description+"||"+status+" "+deadline);

//                        BorderLayout layout = (BorderLayout) currentlySelectedPanel.getLayout();
//                        Component centermostComponent = layout.getLayoutComponent(BorderLayout.CENTER);
//                        Component leftmostComponent = layout.getLayoutComponent(BorderLayout.WEST);
//
//                        if(centermostComponent instanceof JLabel){
//                            ((JLabel)centermostComponent).setText(title);
//                        }
//                        if(((JLabel)leftmostComponent).getIcon()==complete_simpletask||
//                           ((JLabel)leftmostComponent).getIcon()==ongoing_simpletask||
//                           ((JLabel)leftmostComponent).getIcon()==failed_simpletask||
//                           ((JLabel)leftmostComponent).getIcon()==unknown_simpletask){
//                            type = "SIMPLE";
//                        }
//
//                        if(leftmostComponent instanceof JLabel){
//                            if(status.contains("Completed")){
//                                if(type=="SIMPLE") ((JLabel)leftmostComponent).setIcon(complete_simpletask);
//                                else ((JLabel)leftmostComponent).setIcon(complete_projecttask);
//                            }else if(status.contains("Ongoing")){
//                                if(type=="SIMPLE") ((JLabel)leftmostComponent).setIcon(ongoing_simpletask);
//                                else ((JLabel)leftmostComponent).setIcon(ongoing_projecttask);
//                            }else if(status.contains("Failed")){
//                                if(type=="SIMPLE") ((JLabel)leftmostComponent).setIcon(failed_simpletask);
//                                else ((JLabel)leftmostComponent).setIcon(failed_projecttask);
//                            }else{
//                                if(type=="SIMPLE") ((JLabel)leftmostComponent).setIcon(unknown_simpletask);
//                                else ((JLabel)leftmostComponent).setIcon(unknown_projecttask);
//                            }
//                        }
//
//                        currentlySelectedTask.setTitle(title);
//                        currentlySelectedTask.setDescription(description);
//                        currentlySelectedTask.setStatus(" Status: "+status);
//                        currentlySelectedTask.setDeadline(" Deadline: "+deadline);
                    }
            });
            JLabel Delete_Button = new JLabel();
            Delete_Button.setPreferredSize(new Dimension(65,65));
//            Delete_Button.setIcon(delete);
            Delete_Button.setHorizontalAlignment(JLabel.CENTER);
            Delete_Button.setVerticalAlignment(JLabel.CENTER);
            Delete_Button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            Delete_Button.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseEntered(MouseEvent e){
                        Delete_Button.setOpaque(true);
                        Delete_Button.setBackground(Color.LIGHT_GRAY); //--------------CHANGE
                        Delete_Button.repaint();
                    };
                    @Override
                    public void mouseExited(MouseEvent e){
                        Delete_Button.setOpaque(false);
                        Delete_Button.repaint();
                    }
                    public void mouseClicked(MouseEvent e){
                        if((JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this task?","Confirmation",JOptionPane.YES_NO_OPTION))==0){
                            System.out.println("Task Deleted");
                            DeleteTaskCommand dtc = new DeleteTaskCommand();
    //                        Observer tpf = new taskPanelFactory();
    //                        stc.addObserver(tpf);

                            dtc.deleteTask(task);
                            taskControl.setCommand(dtc);
                            taskControl.clickedButton();
                        }
                    }
            });
            
            Body_Settings.add(Save_Button);
            Body_Settings.add(Delete_Button);


            JPanel Body_Center = new JPanel();
            Body_Center.setBackground(Color.DARK_GRAY);
            Body_Center.setLayout(new BorderLayout());
            ///Body_Center.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
            Body_Center.setBorder(
                    BorderFactory.createEmptyBorder(
                            (int)((height-170)*0.10),
                            (int)((width-220)*0.09),
                            (int)((height-170)*0.10),
                            (int)((width-220)*0.09)
                    ));
            
            JPanel Body_Center_Inside = new JPanel();
            Body_Center_Inside.setPreferredSize(new Dimension((int)((width-220)*0.8),
                                                              (int)((height-170)*0.7)));
            Body_Center_Inside.setBackground(Color.DARK_GRAY);
            Body_Center_Inside.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        //
        
        Body.add(Body_NorthLip,BorderLayout.NORTH);
        Body.add(Body_Center,BorderLayout.CENTER);
        
        Body_NorthLip.add(Body_Title,BorderLayout.WEST);
        Body_NorthLip.add(Body_Options,BorderLayout.CENTER);
        Body_NorthLip.add(Body_Settings,BorderLayout.EAST);
        
        Body_Options.add(Body_Status);
        Body_Options.add(Body_Deadline);
        
        Body_Center.add(Body_Center_Inside, BorderLayout.CENTER);
        Body_Center_Inside.add(scrollPane);
        
        System.out.println(task.getTitle()+" EEEE");
        System.out.println(taskTitle.getText()+" AAAA");
        
        return Body;
    }
    
    public static void setValues(String title,String description, String status, String deadline){
        taskTitle.setText(title);
        taskDescription.setText(description);
        Status.setText(status);
        Deadline.setText(deadline);
        
    }
    
    public static String[] getValues(){
        String[] information = new String[4];
        
        information[0] = taskTitle.getText();
        information[1] = taskDescription.getText();
        information[2] = Status.getText();
        information[3] = Deadline.getText();
        
        return information;
    }
    
    private static Font Roboto(int fontSize){
        Font Roboto = new Font("Roboto",Font.BOLD,fontSize);
        return Roboto; 
    }
}
