package com.mycompany.donezodraft.InternalFrames;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class Dashboard extends javax.swing.JInternalFrame {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    static int intTaskCounter = 0;
    static float flTimeAllotted = 0;
    static int intCompleted = 0;
    static int intDoImmediately = 0;
    static int intStartEarly = 0;
    static int intScheduleLater = 0;
    private static final String FILE_PATH = "database.txt";

    public Dashboard() {
        loadTasks();
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UI = (BasicInternalFrameUI) this.getUI();
        UI.setNorthPane(null);

        loadFont("/fontStyles/Outfit-ExtraBold.ttf", 28f, lblHeading);
        loadFont("/fontStyles/Outfit-ExtraBold.ttf", 40f, lblGreeting);
        loadFont("/fontStyles/Montserrat-Medium.ttf", 16f, lblMessage);
        loadFont("/fontStyles/Outfit-Bold.ttf", 20f, lblTasks, lblTime, lblProgress, lblPriorities);
        loadFont("/fontStyles/Outfit-Regular.ttf", 16f, lblTasksRemaining, lblTimeRemaining, lblProgressDone);
        loadFont("/fontStyles/Outfit-Regular.ttf", 14f, lblCountImmediate, lblCountStart, lblCountLater);
        loadFont("/fontStyles/Outfit-SemiBold.ttf", 16f, lblImmediate, lblStartEarly, lblScheduleLater);

        initializeListeners();
    }

    private void loadFont(String path, float size, JLabel... labels) {
        try {
            InputStream fontStream = getClass().getResourceAsStream(path);
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(size);
            for (JLabel label : labels) {
                label.setFont(font);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scaleImage() {
        // Scale and set images for all labels
        setScaledImage(iconTasks, "/IconImages/tasks.png");
        setScaledImage(iconTime, "/IconImages/time.png");
        setScaledImage(iconProgress, "/IconImages/progress.png");
    }

    private void setScaledImage(JLabel label, String resourcePath) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(resourcePath));
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaledImg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addResizeListenerToLabels(JLabel... labels) {
        ComponentAdapter resizeListener = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                scaleImage();
            }
        };

        for (JLabel label : labels) {
            label.addComponentListener(resizeListener);
        }
    }

    private void initializeListeners() {
        // Add the resize listener to all relevant labels
        addResizeListenerToLabels(iconTasks, iconTime, iconProgress);
    }
    private void loadTasks() {
        ArrayList<Task> loadedTasks = FileH.funcReadFile(FILE_PATH);
        intCompleted = 0;
        if (loadedTasks != null) {
            tasks.clear();
            tasks.addAll(loadedTasks);
            dashboardFunctions(loadedTasks);
        } else {
            System.out.println("No saved tasks found.");
        }
    }
    private void dashboardFunctions(ArrayList<Task> tasks){
        intTaskCounter = tasks.size(); //For Task Remaining
            for (Task task : tasks) {
                flTimeAllotted += task.getTimeAllotted(); //For Time Remaining
                if(task.getProgress().equals("Completed")) //For Completed
                {
                    intCompleted++; 
                }
            }
        flTimeAllotted = flTimeAllotted / 60; //For minutes to hours
        intStartEarly = 0;
        intDoImmediately = 0;
        intScheduleLater = 0;
        LocalDate currentDate = LocalDate.now();
        for (Task task : tasks) {
            LocalDate dueDate = task.getDueDate();
            long daysLeft = ChronoUnit.DAYS.between(currentDate, dueDate);
            if(!(task.getProgress().equals("Completed"))){
                if(task.getDifficulty().equals("Easy")){
                    if(daysLeft <= 1)
                        intDoImmediately++;
                    else if(daysLeft <= 6 && daysLeft >= 2)
                        intStartEarly++;
                    else
                        intScheduleLater++;
                }
                else if(task.getDifficulty().equals("Medium")){
                    if(daysLeft <= 4)
                        intDoImmediately++;
                    else if(daysLeft <= 5 && daysLeft >= 14)
                        intStartEarly++;
                    else
                        intScheduleLater++;
                }
                else if(task.getDifficulty().equals("Hard")){
                    if(daysLeft <= 7)
                        intDoImmediately++;
                    else
                        intStartEarly++;
                }
            }
            
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jMenuItem1 = new javax.swing.JMenuItem();
        lblHeading = new javax.swing.JLabel();
        pnlGreeting = new javax.swing.JPanel();
        lblGreeting = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblMascot = new javax.swing.JLabel();
        lblSummary = new javax.swing.JLabel();
        pnlProgress = new javax.swing.JPanel();
        iconProgress = new javax.swing.JLabel();
        lblProgressDone = new javax.swing.JLabel();
        lblProgress = new javax.swing.JLabel();
        pnlPriorities = new javax.swing.JPanel();
        lblPriorities = new javax.swing.JLabel();
        lblImmediate = new javax.swing.JLabel();
        lblCountImmediate = new javax.swing.JLabel();
        lblStartEarly = new javax.swing.JLabel();
        lblCountStart = new javax.swing.JLabel();
        lblScheduleLater = new javax.swing.JLabel();
        lblCountLater = new javax.swing.JLabel();
        pnlTime = new javax.swing.JPanel();
        iconTime = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblTimeRemaining = new javax.swing.JLabel();
        pnlTaskRemaining = new javax.swing.JPanel();
        iconTasks = new javax.swing.JLabel();
        lblTasks = new javax.swing.JLabel();
        lblTasksRemaining = new javax.swing.JLabel();
        jCalendar2 = new com.toedter.calendar.JCalendar();
        btnWorkflow = new javax.swing.JButton();
        btnAddTask = new javax.swing.JButton();

        jPasswordField1.setText("jPasswordField1");

        jMenuItem1.setText("jMenuItem1");

        setBackground(new java.awt.Color(231, 231, 231));
        setPreferredSize(new java.awt.Dimension(979, 693));
        setRequestFocusEnabled(false);
        getContentPane().setLayout(null);

        lblHeading.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblHeading.setText("Dashboard");
        getContentPane().add(lblHeading);
        lblHeading.setBounds(28, 17, 183, 48);

        pnlGreeting.setBackground(new java.awt.Color(0, 0, 102));
        pnlGreeting.setForeground(new java.awt.Color(0, 0, 102));

        lblGreeting.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblGreeting.setForeground(new java.awt.Color(255, 255, 255));
        lblGreeting.setText("Hello, Name!");

        lblMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMessage.setForeground(new java.awt.Color(255, 255, 255));
        lblMessage.setText("Are you ready to start your day with me?");

        jPanel2.setBackground(new java.awt.Color(231, 231, 231));

        lblMascot.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblMascot.setText("MASCOT NIYO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(160, Short.MAX_VALUE)
                .addComponent(lblMascot)
                .addGap(143, 143, 143))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMascot)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlGreetingLayout = new javax.swing.GroupLayout(pnlGreeting);
        pnlGreeting.setLayout(pnlGreetingLayout);
        pnlGreetingLayout.setHorizontalGroup(
            pnlGreetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGreetingLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlGreetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGreetingLayout.createSequentialGroup()
                        .addComponent(lblMessage)
                        .addGap(0, 136, Short.MAX_VALUE))
                    .addComponent(lblGreeting, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlGreetingLayout.setVerticalGroup(
            pnlGreetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGreetingLayout.createSequentialGroup()
                .addGroup(pnlGreetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGreetingLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lblGreeting, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlGreetingLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        getContentPane().add(pnlGreeting);
        pnlGreeting.setBounds(28, 77, 895, 137);

        lblSummary.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSummary.setForeground(new java.awt.Color(102, 102, 102));
        lblSummary.setText("Task Summary");
        getContentPane().add(lblSummary);
        lblSummary.setBounds(28, 232, 137, 25);

        pnlProgress.setBackground(new java.awt.Color(255, 255, 255));

        lblProgressDone.setForeground(new java.awt.Color(1, 33, 66));
        lblProgressDone.setText("No./No. tasks completed! ");

        lblProgress.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblProgress.setForeground(new java.awt.Color(1, 33, 66));
        lblProgress.setText("Your Progress");

        javax.swing.GroupLayout pnlProgressLayout = new javax.swing.GroupLayout(pnlProgress);
        pnlProgress.setLayout(pnlProgressLayout);
        pnlProgressLayout.setHorizontalGroup(
            pnlProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProgressLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(iconProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProgress)
                    .addComponent(lblProgressDone))
                .addContainerGap(329, Short.MAX_VALUE))
        );
        pnlProgressLayout.setVerticalGroup(
            pnlProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProgressLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProgressLayout.createSequentialGroup()
                        .addComponent(lblProgress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblProgressDone))
                    .addComponent(iconProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        getContentPane().add(pnlProgress);
        pnlProgress.setBounds(28, 509, 584, 131);

        pnlPriorities.setBackground(new java.awt.Color(255, 255, 255));

        lblPriorities.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPriorities.setForeground(new java.awt.Color(1, 33, 66));
        lblPriorities.setText("Tasks Priorities");

        lblImmediate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblImmediate.setForeground(new java.awt.Color(119, 19, 19));
        lblImmediate.setText("Do Immediately:");

        lblCountImmediate.setForeground(new java.awt.Color(1, 33, 66));
        lblCountImmediate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCountImmediate.setText("No. task/s remaining");

        lblStartEarly.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStartEarly.setForeground(new java.awt.Color(21, 41, 124));
        lblStartEarly.setText("Start Early:");

        lblCountStart.setForeground(new java.awt.Color(1, 33, 66));
        lblCountStart.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCountStart.setText("No. task/s remaining");

        lblScheduleLater.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblScheduleLater.setForeground(new java.awt.Color(43, 117, 24));
        lblScheduleLater.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblScheduleLater.setText("Schedule Later:");

        lblCountLater.setForeground(new java.awt.Color(1, 33, 66));
        lblCountLater.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCountLater.setText("No. task/s remaining");

        javax.swing.GroupLayout pnlPrioritiesLayout = new javax.swing.GroupLayout(pnlPriorities);
        pnlPriorities.setLayout(pnlPrioritiesLayout);
        pnlPrioritiesLayout.setHorizontalGroup(
            pnlPrioritiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrioritiesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlPrioritiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPriorities)
                    .addGroup(pnlPrioritiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlPrioritiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPrioritiesLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblCountStart))
                            .addComponent(lblStartEarly))
                        .addGroup(pnlPrioritiesLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(pnlPrioritiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlPrioritiesLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(lblCountImmediate))
                                .addComponent(lblImmediate)))
                        .addGroup(pnlPrioritiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPrioritiesLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblCountLater))
                            .addComponent(lblScheduleLater))))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        pnlPrioritiesLayout.setVerticalGroup(
            pnlPrioritiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrioritiesLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblPriorities)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImmediate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCountImmediate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStartEarly)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCountStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblScheduleLater)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCountLater)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlPriorities);
        pnlPriorities.setBounds(390, 263, 222, 233);

        pnlTime.setBackground(new java.awt.Color(255, 255, 255));

        lblTime.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTime.setForeground(new java.awt.Color(1, 33, 66));
        lblTime.setText("Time");

        lblTimeRemaining.setForeground(new java.awt.Color(1, 33, 66));
        lblTimeRemaining.setText("No. Hours Remaining");

        javax.swing.GroupLayout pnlTimeLayout = new javax.swing.GroupLayout(pnlTime);
        pnlTime.setLayout(pnlTimeLayout);
        pnlTimeLayout.setHorizontalGroup(
            pnlTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimeLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(iconTime, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTime)
                    .addComponent(lblTimeRemaining))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        pnlTimeLayout.setVerticalGroup(
            pnlTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimeLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(pnlTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTimeLayout.createSequentialGroup()
                        .addComponent(lblTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTimeRemaining))
                    .addComponent(iconTime, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        getContentPane().add(pnlTime);
        pnlTime.setBounds(28, 389, 344, 107);

        pnlTaskRemaining.setBackground(new java.awt.Color(255, 255, 255));

        lblTasks.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTasks.setForeground(new java.awt.Color(1, 33, 66));
        lblTasks.setText("Tasks");

        lblTasksRemaining.setForeground(new java.awt.Color(1, 33, 66));
        lblTasksRemaining.setText("No. Task Remaining");

        javax.swing.GroupLayout pnlTaskRemainingLayout = new javax.swing.GroupLayout(pnlTaskRemaining);
        pnlTaskRemaining.setLayout(pnlTaskRemainingLayout);
        pnlTaskRemainingLayout.setHorizontalGroup(
            pnlTaskRemainingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaskRemainingLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(iconTasks, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlTaskRemainingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTasks)
                    .addComponent(lblTasksRemaining))
                .addContainerGap(132, Short.MAX_VALUE))
        );
        pnlTaskRemainingLayout.setVerticalGroup(
            pnlTaskRemainingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaskRemainingLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlTaskRemainingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconTasks, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTaskRemainingLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblTasks)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTasksRemaining)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        getContentPane().add(pnlTaskRemaining);
        pnlTaskRemaining.setBounds(28, 263, 344, 108);
        getContentPane().add(jCalendar2);
        jCalendar2.setBounds(665, 263, 250, 240);

        btnWorkflow.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnWorkflow.setForeground(new java.awt.Color(28, 35, 74));
        btnWorkflow.setText("View Workflow");
        btnWorkflow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWorkflowActionPerformed(evt);
            }
        });
        getContentPane().add(btnWorkflow);
        btnWorkflow.setBounds(760, 580, 150, 40);

        btnAddTask.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddTask.setForeground(new java.awt.Color(28, 35, 74));
        btnAddTask.setText("Add Task");
        btnAddTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTaskActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddTask);
        btnAddTask.setBounds(760, 530, 150, 40);

        setBounds(0, 0, 973, 699);
    }// </editor-fold>//GEN-END:initComponents

    private void btnWorkflowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWorkflowActionPerformed
        JDesktopPane desktopPane = getDesktopPane();
    
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            frame.dispose();
        }

        Workflow workflow = new Workflow();
        desktopPane.add(workflow);
        workflow.setVisible(true); 
        
    }//GEN-LAST:event_btnWorkflowActionPerformed

    private void btnAddTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTaskActionPerformed
        JDesktopPane desktopPane = getDesktopPane();
    
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            frame.dispose();
        }

        TaskList tasks = new TaskList();
        desktopPane.add(tasks);
        tasks.setVisible(true);
    }//GEN-LAST:event_btnAddTaskActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTask;
    private javax.swing.JButton btnWorkflow;
    private javax.swing.JLabel iconProgress;
    private javax.swing.JLabel iconTasks;
    private javax.swing.JLabel iconTime;
    private com.toedter.calendar.JCalendar jCalendar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JLabel lblCountImmediate;
    private javax.swing.JLabel lblCountLater;
    private javax.swing.JLabel lblCountStart;
    private javax.swing.JLabel lblGreeting;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblImmediate;
    private javax.swing.JLabel lblMascot;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblPriorities;
    private javax.swing.JLabel lblProgress;
    private javax.swing.JLabel lblProgressDone;
    private javax.swing.JLabel lblScheduleLater;
    private javax.swing.JLabel lblStartEarly;
    private javax.swing.JLabel lblSummary;
    private javax.swing.JLabel lblTasks;
    private javax.swing.JLabel lblTasksRemaining;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTimeRemaining;
    private javax.swing.JPanel pnlGreeting;
    private javax.swing.JPanel pnlPriorities;
    private javax.swing.JPanel pnlProgress;
    private javax.swing.JPanel pnlTaskRemaining;
    private javax.swing.JPanel pnlTime;
    // End of variables declaration//GEN-END:variables
}
