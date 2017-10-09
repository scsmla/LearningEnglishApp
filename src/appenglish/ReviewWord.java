/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appenglish;

import java.util.*;
import java.sql.*;
import javax.swing.ImageIcon;
/**
 *
 * @author Bui
 */
public class ReviewWord extends javax.swing.JFrame {

    /**
     * Creates new form ReviewWord
     */
    public String connectionUrl = "jdbc:sqlserver://localhost:1433;" +  
     "databaseName=AppEnglish;integratedSecurity=true;"; 
    public Connection connection = null;  
    public  Statement statement = null;   
    public  ResultSet resultSet = null;  
    
    public PreparedStatement prepsInsertProduct = null; 
    public static List<String> wordList = new ArrayList<String>();
    public static List<String> meaningList = new ArrayList<String>();
    public static List<String> imgList = new ArrayList<String>();
    private static String subjectToReview;
    public static int length;
    public static int index = 0;
    public ReviewWord(String subject) 
    {
        
        subjectToReview = subject;       
        initComponents();
        readDBToLists();
        displaytheWord();
    }
    public void readDBToLists()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String sqlquery = "SELECT * FROM dbo.wordData WHERE subjectOfWord = N'"+subjectToReview+
                    "' AND learned = 1";
            connection = DriverManager.getConnection(connectionUrl);
            statement = connection.createStatement();
            resultSet =  statement.executeQuery(sqlquery);
            
            if(!resultSet.isBeforeFirst())
            {
                notifyLabel.setVisible(true);            
            }
            else
            {
                length = resultSet.getRow();
                while (resultSet.next())
                {
                    wordList.add(resultSet.getString(2));
                    meaningList.add(resultSet.getString(3));
                    imgList.add(resultSet.getString(8));
                }   
                length = wordList.size();
//                System.out.print(length);
            }
        }
        
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }
   
    public void displaytheWord()
    {
        if(index == length - 1)
            nextButton.setEnabled(false);
        else
            nextButton.setEnabled(true);
        if(index == 0)
            previousButton.setEnabled(false);
        else
            previousButton.setEnabled(true);
        if(length > 0)
        {
            wordDisplayLabel.setText(wordList.get(index));
            meaningDisplayLabel.setText(meaningList.get(index));
            ImageIcon imgWord = new ImageIcon(imgList.get(index));
            imageLabel.setIcon(imgWord);
        }
        else
            return;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subjectLabel = new javax.swing.JLabel();
        wordLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        meaningDisplayLabel = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        notifyLabel = new javax.swing.JLabel();
        wordDisplayLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 398));

        subjectLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        subjectLabel.setForeground(new java.awt.Color(153, 0, 153));
        subjectLabel.setText("Chủ đề " + subjectToReview);
        subjectLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        wordLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        wordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wordLabel.setText("Từ:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nghĩa:");
        jLabel4.setToolTipText("");

        meaningDisplayLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        meaningDisplayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        meaningDisplayLabel.setToolTipText("");

        previousButton.setText("Previous");
        previousButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                previousButtonMouseClicked(evt);
            }
        });

        nextButton.setText("Next");
        nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextButtonMouseClicked(evt);
            }
        });

        notifyLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        notifyLabel.setForeground(new java.awt.Color(255, 51, 51));
        notifyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notifyLabel.setText("Bạn chưa hoc từ nào");
        notifyLabel.setVisible(false);

        wordDisplayLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        wordDisplayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(previousButton)
                .addGap(101, 101, 101)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subjectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(meaningDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(142, 142, 142)
                                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(notifyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(wordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wordDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(subjectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wordDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nextButton)
                            .addComponent(previousButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(meaningDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(notifyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void previousButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousButtonMouseClicked
        index --;
        displaytheWord();
    }//GEN-LAST:event_previousButtonMouseClicked

    private void nextButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseClicked
        index ++;
        displaytheWord();
    }//GEN-LAST:event_nextButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReviewWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReviewWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReviewWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReviewWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReviewWord(subjectToReview).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel meaningDisplayLabel;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel notifyLabel;
    private javax.swing.JButton previousButton;
    private javax.swing.JLabel subjectLabel;
    private javax.swing.JLabel wordDisplayLabel;
    private javax.swing.JLabel wordLabel;
    // End of variables declaration//GEN-END:variables
}