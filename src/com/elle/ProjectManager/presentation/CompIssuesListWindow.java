
package com.elle.ProjectManager.presentation;

import com.elle.ProjectManager.database.DBConnection;
import com.elle.ProjectManager.database.SQL_Commands;
import com.elle.ProjectManager.logic.CompIssuesItem;
import com.elle.ProjectManager.logic.ReadWriteFiles;
import com.elle.ProjectManager.logic.TextAreaList;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Carlos Igreja
 * @since  2016 January 2
 */
public class CompIssuesListWindow extends javax.swing.JFrame {
    
    // the USER name that is logged in and default path / file
    private final String FRAME_TITLE = "Compile Issue List";
    private final String USER = DBConnection.getUserName().substring(7);
    private final String DIR_PATH = ".\\PM Support Files\\";
    private final File DIR = new File(DIR_PATH); // DIR to keep compile.txt files
    private String path = ".\\PM Support Files\\" + USER + "_Compile.txt";

    private final String DB_TABLE_NAME = "issues"; // database table name
    private final String COL_ID = "ID";
    private final String COL_APP = "app";
    private final String COL_TITLE = "title";
    private final String COL_DESCRIPTION = "description";
    private final String COL_PROGRAMMER = "programmer";
    private final String COL_DATE_OPENED = "dateOpened";
    private final String COL_RK = "rk";
    private final String COL_VERSION = "version";
    private final String COL_DATE_CLOSED = "dateClosed";
    
    private final String ALL = "All";         // used for the all option
    private final String DATES_ALL = "All";   // dates combo box selection
    private final String DATES_OPENED = "Opened Dates";   // dates combo box selection
    private final String DATES_CLOSED = "Closed Dates";   // dates combo box selection
    private final String DATES_STILL_OPENED = "Still Open";   // dates combo box selection
    
    private final String DATE_FORMAT = "yyyy-MM-dd";  // format of date
    private final String HYPHENS = "--------------------------------------------------------"
                                 + "--------------------------------------------------------"
                                 + "----------------";
    
    private SQL_Commands sql;
    private ReadWriteFiles rwFiles;
    private JFileChooser fc;
    private HashMap<String,ArrayList<Object>> map;
    private TextAreaList textAreaList;
    private ArrayList<CompIssuesItem> compIssueItems;
    
    /**
     * Creates new form CompIssuesWireFrame
     */
    public CompIssuesListWindow() {
        initComponents();
        
        this.setTitle(FRAME_TITLE);
        
        // initialize class variables
        sql = new SQL_Commands(DBConnection.getConnection());
        rwFiles = new ReadWriteFiles();
        map = new HashMap<>();
        
        // initialize all combo boxes
        initComboBoxes();

        textAreaList = new TextAreaList();
        setTextAreaListListener(textAreaList);
        
        // text area item array
        compIssueItems = new ArrayList<>();
        
        // add textAreaList to a scrollpane
        ScrollPane scroll = new ScrollPane();
        scroll.add(textAreaList);
        scroll.setPreferredSize(panelOutputDisplay.getPreferredSize());
        
        panelOutputDisplay.setLayout(new BorderLayout());
        panelOutputDisplay.add(scroll, BorderLayout.CENTER);
        
        // JFileChooser for choosing a file or directory
        fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        fc.setDialogTitle("File Chooser");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if(!DIR.exists())
            DIR.mkdirs(); // make directory if does not exist
        fc.setCurrentDirectory(DIR);
        fc.setSelectedFile(new File(path));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelControls = new javax.swing.JPanel();
        labelProgrammer = new javax.swing.JLabel();
        btnCompile = new javax.swing.JButton();
        labelApp = new javax.swing.JLabel();
        labelFromDB = new javax.swing.JLabel();
        labelToDB = new javax.swing.JLabel();
        cboxOpenCloseDB = new javax.swing.JComboBox<>();
        cboxProgrammer = new javax.swing.JComboBox<>();
        cboxApp = new javax.swing.JComboBox<>();
        btnWriteToTextFile = new javax.swing.JButton();
        datePickerFrom = new org.jdesktop.swingx.JXDatePicker();
        datePickerTo = new org.jdesktop.swingx.JXDatePicker();
        btnReadFromTextFile = new javax.swing.JButton();
        labelDisplay = new javax.swing.JLabel();
        panelOutputDisplay = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelProgrammer.setText("Programmer:");

        btnCompile.setText("Compile");
        btnCompile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompileActionPerformed(evt);
            }
        });

        labelApp.setText("App:");

        labelFromDB.setText("From:");

        labelToDB.setText("To:");

        cboxOpenCloseDB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboxOpenCloseDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxOpenCloseDBActionPerformed(evt);
            }
        });

        cboxProgrammer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboxProgrammer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxProgrammerActionPerformed(evt);
            }
        });

        cboxApp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnWriteToTextFile.setText("Write to Text File");
        btnWriteToTextFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWriteToTextFileActionPerformed(evt);
            }
        });

        btnReadFromTextFile.setText("Read from Text File");
        btnReadFromTextFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadFromTextFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelControlsLayout = new javax.swing.GroupLayout(panelControls);
        panelControls.setLayout(panelControlsLayout);
        panelControlsLayout.setHorizontalGroup(
            panelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlsLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelControlsLayout.createSequentialGroup()
                        .addGroup(panelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelControlsLayout.createSequentialGroup()
                                .addGroup(panelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelFromDB)
                                    .addComponent(labelToDB))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboxOpenCloseDB, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(datePickerFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(datePickerTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnReadFromTextFile, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(panelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnCompile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelControlsLayout.createSequentialGroup()
                                .addComponent(labelProgrammer)
                                .addGap(18, 18, 18)
                                .addComponent(cboxProgrammer, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelControlsLayout.createSequentialGroup()
                                .addComponent(labelApp)
                                .addGap(18, 18, 18)
                                .addComponent(cboxApp, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnWriteToTextFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelControlsLayout.createSequentialGroup()
                        .addComponent(labelDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190))))
        );
        panelControlsLayout.setVerticalGroup(
            panelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelControlsLayout.createSequentialGroup()
                .addComponent(labelDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelControlsLayout.createSequentialGroup()
                            .addComponent(cboxOpenCloseDB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelFromDB)
                                .addComponent(datePickerFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelControlsLayout.createSequentialGroup()
                            .addGap(61, 61, 61)
                            .addGroup(panelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelToDB)
                                .addComponent(datePickerTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelControlsLayout.createSequentialGroup()
                        .addGroup(panelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelProgrammer)
                            .addComponent(cboxProgrammer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelApp)
                            .addComponent(cboxApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCompile)))
                .addGap(25, 25, 25)
                .addGroup(panelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnWriteToTextFile)
                    .addComponent(btnReadFromTextFile))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout panelOutputDisplayLayout = new javax.swing.GroupLayout(panelOutputDisplay);
        panelOutputDisplay.setLayout(panelOutputDisplayLayout);
        panelOutputDisplayLayout.setHorizontalGroup(
            panelOutputDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelOutputDisplayLayout.setVerticalGroup(
            panelOutputDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelControls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelOutputDisplay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelOutputDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboxProgrammerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxProgrammerActionPerformed
        
    }//GEN-LAST:event_cboxProgrammerActionPerformed

    private void btnWriteToTextFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWriteToTextFileActionPerformed

        
        fc.showOpenDialog(this); 
        
        // file must be selected or null pointer is thrown
        try{
            path = fc.getSelectedFile().getAbsolutePath();
//            if(writeFromTextAreaToTextFile(textAreaList, path))
//                messageBox("written successfully!");
        }
        catch(NullPointerException e){
            messageBox("Please select a file.");
        }
    }//GEN-LAST:event_btnWriteToTextFileActionPerformed

    private void cboxOpenCloseDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxOpenCloseDBActionPerformed
        if(cboxOpenCloseDB.getSelectedItem() != null
                && (cboxOpenCloseDB.getSelectedItem().toString().equals(DATES_ALL)
                || cboxOpenCloseDB.getSelectedItem().toString().equals(DATES_STILL_OPENED))){
            datePickerFrom.setEnabled(false);
            datePickerTo.setEnabled(false);
        }else{
            datePickerFrom.setEnabled(true);
            datePickerTo.setEnabled(true);
        }
    }//GEN-LAST:event_cboxOpenCloseDBActionPerformed

    private void btnReadFromTextFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadFromTextFileActionPerformed
        
        fc.showOpenDialog(this); 
        
        // file must be selected or null pointer is 
        try{
            path = fc.getSelectedFile().getAbsolutePath(); // check first for null pointer
            BufferedReader reader = rwFiles.getReader(path);
            //textAreaCompiledOutputPane.setText("");
            String line = reader.readLine();
            while(line != null){
                //textAreaCompiledOutputPane.append(line + "\n");
                line = reader.readLine();
            }
        }catch(NullPointerException e){
            messageBox("Please select a file.");
        } catch (IOException ex) {
            messageBox(ex.getMessage());
        }
        
    }//GEN-LAST:event_btnReadFromTextFileActionPerformed

    private void btnCompileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompileActionPerformed
        map = getTableData();
        writeToTextArea(textAreaList, map);
    }//GEN-LAST:event_btnCompileActionPerformed

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
            java.util.logging.Logger.getLogger(CompIssuesListWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompIssuesListWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompIssuesListWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompIssuesListWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompIssuesListWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompile;
    private javax.swing.JButton btnReadFromTextFile;
    private javax.swing.JButton btnWriteToTextFile;
    private javax.swing.JComboBox<String> cboxApp;
    private javax.swing.JComboBox<String> cboxOpenCloseDB;
    private javax.swing.JComboBox<String> cboxProgrammer;
    private org.jdesktop.swingx.JXDatePicker datePickerFrom;
    private org.jdesktop.swingx.JXDatePicker datePickerTo;
    private javax.swing.JLabel labelApp;
    private javax.swing.JLabel labelDisplay;
    private javax.swing.JLabel labelFromDB;
    private javax.swing.JLabel labelProgrammer;
    private javax.swing.JLabel labelToDB;
    private javax.swing.JPanel panelControls;
    private javax.swing.JPanel panelOutputDisplay;
    // End of variables declaration//GEN-END:variables

    private void initComboBoxes() {
        
        HashMap<String,ArrayList<Object>> map;
        
        // make sure the connection is open
        DBConnection.close();     // close old connection
        DBConnection.open();      // open new connection
        sql.setConnection(DBConnection.getConnection());    // set connection
        sql.createStatement(DBConnection.getConnection());  // create statement
        
        // Opened or Closed Dates
        cboxOpenCloseDB.removeAllItems();
        cboxOpenCloseDB.addItem(DATES_STILL_OPENED);
        cboxOpenCloseDB.addItem(DATES_CLOSED);
        cboxOpenCloseDB.addItem(DATES_OPENED);
        cboxOpenCloseDB.addItem(DATES_ALL);
        datePickerFrom.setEnabled(false);
        datePickerTo.setEnabled(false);
        Date date = new Date();
        datePickerTo.setDate(date);
        
        // App combobox
        map = sql.getDistinctColumnValues(DB_TABLE_NAME, COL_APP);
        cboxApp.removeAllItems();
        cboxApp.addItem(ALL);
        for(int i = 0; i < map.get(COL_APP).size(); i++){
            cboxApp.addItem(map.get(COL_APP).get(i).toString());
        }
        
        // Programmer combobox
        map = sql.getDistinctColumnValues(DB_TABLE_NAME, COL_PROGRAMMER);
        cboxProgrammer.removeAllItems();
        cboxProgrammer.addItem(ALL);
        for(int i = 0; i < map.get(COL_PROGRAMMER).size(); i++){
            cboxProgrammer.addItem(map.get(COL_PROGRAMMER).get(i).toString());
        }
    }

    private String getAppQuery(String app) {
        if(!app.equals(ALL)){
            return COL_APP + " = '" + app + "' ";
        }
        return "";
    }

    private String getProgrammerQuery(String programmer) {
        if(!programmer.equals(ALL)){
            return COL_PROGRAMMER + " = '" + programmer + "' ";
        }
        return "";
    }

    private String getDatesQuery(String useDates, Date dateFrom, Date dateTo) {
        if(!useDates.equals(DATES_ALL)){
            if(!useDates.equals(DATES_STILL_OPENED)){
                //!Validator.isValidDate("yyyy-MM-dd", cellValue.toString())
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                String from = sdf.format(dateFrom);
                String to = sdf.format(dateTo);
                if(useDates.equals(DATES_OPENED))
                    return COL_DATE_OPENED + " BETWEEN '" + from + "' AND '" + to + "' ";
                else 
                    return COL_DATE_CLOSED + " BETWEEN '" + from + "' AND '" + to + "' ";
            }
            return COL_DATE_CLOSED + " IS NULL ";
        }
        return "";
    }

    // writes the text area content to file
    private boolean writeToFile(String file, HashMap<String, ArrayList<Object>> map) {
        
        PrintWriter writer = rwFiles.getWriter(file); // writer to write to file
        
        // get data for each record
        int records = map.get(COL_APP).size(); // had to hard code - used app
        for(int i = 0; i < records; i++){
            
            String app = "";
            String title= "";
            String rk= "";
            String programmer= "";
            String opened= "";
            String closed= "";
            String version= "";
            String description= "";
            
            if(map.get(COL_APP).get(i) != null)
                app = COL_APP + ": " + map.get(COL_APP).get(i).toString() + " ";
            if(map.get(COL_TITLE).get(i) != null)
                title = COL_TITLE + ": " + map.get(COL_TITLE).get(i).toString() + " ";
            if(map.get(COL_RK).get(i) != null)
                rk = COL_RK + ": " + map.get(COL_RK).get(i).toString() + " ";
            if(map.get(COL_PROGRAMMER).get(i) != null)
                programmer = COL_PROGRAMMER + ": " + map.get(COL_PROGRAMMER).get(i).toString() + " ";
            if(map.get(COL_DATE_OPENED).get(i) != null)
                opened = COL_DATE_OPENED + ": " + map.get(COL_DATE_OPENED).get(i).toString() + " ";
            if(map.get(COL_DATE_CLOSED).get(i) != null)
                closed = COL_DATE_CLOSED + ": " + map.get(COL_DATE_CLOSED).get(i).toString() + " ";
            if(map.get(COL_VERSION).get(i) != null)
                version = COL_VERSION + ": " + map.get(COL_VERSION).get(i).toString() + " ";
            if(map.get(COL_DESCRIPTION).get(i) != null)
                description = COL_DESCRIPTION + ": " + map.get(COL_DESCRIPTION).get(i).toString() + " ";
            
            /**
             * print to file
             * FORMAT
             * app title rk programmer opened closed version
             * ----------------------------------------------
             * descriptions
             */
            writer.println(rk + app + title + programmer);
            writer.println("--------------------------------------------------------");
            // break up the description from one long line to ten words per line
            String[] desc = description.split(" ");
            description = "";
            for(int words = 0; words < desc.length; words++){
                description += desc[words] + " ";
                if(words !=0 && words%10 == 0){
                    writer.println(description);
                    description = "";
                }
            }
            writer.println(); // a line break between records
        }
        writer.flush();
        writer.close();
        return true;
    }
    
    // writes the text area content to file
    public boolean writeToTextArea(TextAreaList textAreaList, HashMap<String, ArrayList<Object>> map) {
        
        textAreaList.removeAll();
        compIssueItems.clear();
        CompIssuesItem item;
        
        // get data for each record
        int records = map.get(COL_APP).size(); // had to hard code - used app
        for(int i = 0; i < records; i++){
            
            String id = "";
            String app = "";
            String title= "";
            String rk= "";
            String programmer= "";
            String opened= "";
            String closed= "";
            String version= "";
            String description= "";
            
            if(map.get(COL_ID).get(i) != null)
                id = COL_ID + ": " + map.get(COL_ID).get(i).toString() + " ";
            if(map.get(COL_APP).get(i) != null)
                app = COL_APP + ": " + map.get(COL_APP).get(i).toString() + " ";
            if(map.get(COL_TITLE).get(i) != null)
                title = COL_TITLE + ": " + map.get(COL_TITLE).get(i).toString() + " ";
            if(map.get(COL_RK).get(i) != null)
                rk = COL_RK + ": " + map.get(COL_RK).get(i).toString() + " ";
            if(map.get(COL_PROGRAMMER).get(i) != null)
                programmer = COL_PROGRAMMER + ": " + map.get(COL_PROGRAMMER).get(i).toString() + " ";
            if(map.get(COL_DATE_OPENED).get(i) != null)
                opened = COL_DATE_OPENED + ": " + map.get(COL_DATE_OPENED).get(i).toString() + " ";
            if(map.get(COL_DATE_CLOSED).get(i) != null)
                closed = COL_DATE_CLOSED + ": " + map.get(COL_DATE_CLOSED).get(i).toString() + " ";
            if(map.get(COL_VERSION).get(i) != null)
                version = COL_VERSION + ": " + map.get(COL_VERSION).get(i).toString() + " ";
            if(map.get(COL_DESCRIPTION).get(i) != null)
                description = COL_DESCRIPTION + ": " + map.get(COL_DESCRIPTION).get(i).toString() + " ";
            
            // only show if rk exists
            if(rk.equals(""))
                continue;
            
            // store information for the item
            item = new CompIssuesItem(id);
            
            /**
             * print to text area
             * FORMAT
             *
             * ID app programmer rank dateOpened dateClosed
             * title
             *
             * Description
             * 
             * ---------------------------------------------
             */
            item.append("\n");
            item.append(id + app + programmer + rk + opened + closed + "\n");
            item.append(title + "\n");
            item.append("\n");
            item.append(description + "\n");
            
            // Just appending and let word wrap handle length.
            // length of the textArea is the length of the issue window text area.
            item.append("\n"); // a line break between records
            item.append(HYPHENS + "\n");
            compIssueItems.add(item);
            
            // only show 10 items
            if(compIssueItems.size() == 10)
                break;
        }
        textAreaList.setListData(compIssueItems.toArray());
        return true;
    }
    
    public boolean writeFromTextAreaToTextFile(JTextArea textArea, String path ){
        
        PrintWriter writer = rwFiles.getWriter(path); // writer to write to file
        String[] lines = textArea.getText().split("\n");
        for(int i =0; i < lines.length; i++){
            writer.println(lines[i]);
        }
        writer.flush();
        writer.close();
        return true;
    }

    private void messageBox(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private HashMap<String, ArrayList<Object>> getTableData() {
        
        // get all the information for the query
        String useDates = cboxOpenCloseDB.getSelectedItem().toString();
        Date date = new Date();
        if(datePickerTo.getDate()==null){
           datePickerTo.setDate(date); // set today's date
        }
        Date dateTo = datePickerTo.getDate();
        if(datePickerFrom.getDate()==null){
           date = new Date(-1900,0,1);
           datePickerFrom.setDate(date);
        }
        Date dateFrom = datePickerFrom.getDate();
        String app = cboxApp.getSelectedItem().toString();
        String programmer = cboxProgrammer.getSelectedItem().toString();
        // write the query
        String query = "SELECT * FROM " + DB_TABLE_NAME + " ";
        ArrayList<String> queries = new ArrayList<>();
        queries.add(getAppQuery(app));
        queries.add(getProgrammerQuery(programmer));
        queries.add(getDatesQuery(useDates,dateFrom,dateTo));
        queries.add("ORDER BY " + COL_RK + " ASC");
        boolean needsWhereClause = true;
        for(int i = 0; i < queries.size(); i++){
            if(!queries.get(i).equals("")){
                if(i == queries.size() - 1){ // this is the sorting query
                    query += queries.get(i) + " ";
                }else if(needsWhereClause){
                    query += "WHERE " + queries.get(i) + " ";
                    needsWhereClause = false;
                }else{
                    query += "AND " + queries.get(i) + " ";
                }
            }
        }
        // make sure connection is open
        DBConnection.close();
        DBConnection.open();
        // execute query and return data to hash map
        sql = new SQL_Commands(DBConnection.getConnection());
        HashMap<String,ArrayList<Object>> map;
        System.out.println(query); // for debugging
        map = sql.getTableData(sql.executeQuery(query));
        return map;
    }
    
    /**
     * This formats long string into lines of a fixed length.
     * This checks if a word will fit on that line so that words are not 
     * split up.
     * @param string the string to format the lines
     * @param maxCharsPerLine the max char length of the line breaks
     * @return ArrayList<String> the formatted lines
     */
    public ArrayList formatLineBreaks(String string, int maxCharsPerLine){
        
        ArrayList<String> lines = new ArrayList<>();
        String[] words = string.split(" ");
        String line = "";

        for(int i = 0; i < words.length; i++){
            if(line.length() + words[i].length() + 1 > maxCharsPerLine){
                lines.add(line);
                line = "";
            }
            line += words[i] + " ";
        }
        lines.add(line);
        return lines;
    }
    
    private void setTextAreaListListener(TextAreaList list) {
        
        // add mouseListener to the list
        list.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                // get the text area item index
               int index = list.locationToIndex(e.getPoint());

               // index cannot be null
               if (index != -1) {
                   
                   // get the text area item at this index
                  CompIssuesItem item = (CompIssuesItem)
                              list.getModel().getElementAt(index);
                  
                  labelDisplay.setText(item.getId());

                  list.repaint(); // redraw graphics

               }
            }
        });
    }
}
