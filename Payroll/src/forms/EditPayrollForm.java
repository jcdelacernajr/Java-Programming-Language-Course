/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import components.ComboItem;
import components.SortEmployee;
import components.SortPayroll;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import library.Read;
import library.Writer;
import pojo.Data;
import pojo.EditWorkingDays;
import pojo.Employee;
import pojo.Payroll;

/**
 *
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class EditPayrollForm extends Form {

    private int hours, overtime, absences;
    private int selectedEmployeeId, selectedRecordId;
    private int selectedTableRowId;

    private EditPayrollForm() {
    }

    /**
     * Creates new form EditPayrollForm
     *
     * @param selectedEmployeeId
     * @param selectedRecordId
     */
    public EditPayrollForm(int selectedEmployeeId, int selectedRecordId) {
        this.selectedEmployeeId = selectedEmployeeId;
        this.selectedRecordId = selectedRecordId;
        hours = 0;
        overtime = 0;
        absences = 0;
        selectedTableRowId = 0;
        initComponents();

        comboBoxEmployee();
        payrollTable();
    }

    /**
     * First initialize payroll data
     */
    public void payrollTable() {
        // Days work table
        DefaultTableModel table = (DefaultTableModel) jTableWorkingDays.getModel();
        table.setRowCount(0);

        ArrayList<Payroll> payrollList = new Read(Data.PAYROLL).payrollList();
        Collections.sort(payrollList, new SortPayroll().reversed());

        // transfer the payroll data
        ArrayList<EditWorkingDays> editWorkingDaysList = new ArrayList<>();
        for (Payroll p : payrollList) {
            if (selectedEmployeeId == p.getEmployeeId() && p.getRecordsId() == selectedRecordId) {
                //System.out.println("selected record id: " + p.getRecordsId());
                //System.out.println(p.getRecordsId());
                EditWorkingDays e = new EditWorkingDays();
                e.setRecordId(selectedRecordId);
                e.setId(p.getId());
                e.setEmployeeId(selectedEmployeeId);
                e.setDate(p.getDate());
                e.setDay(p.getDay());
                e.setHours(p.getHours());
                e.setOvertime(p.getOvertime());
                editWorkingDaysList.add(e);
            }
        }
        // Write the payroll data
        Writer w = new Writer(Data.EDIT_WORKING_DAYS);
        w.addEditWorkingsDay(editWorkingDaysList);

        // Get the data gain
        ArrayList<EditWorkingDays> workingDaysList = new Read(Data.EDIT_WORKING_DAYS).editWorkingDaysList();

        int totalHours = 0, totalOvertime = 0, totalAbsences = 0;
        for (EditWorkingDays eW : workingDaysList) {
            if (selectedEmployeeId == eW.getEmployeeId() && eW.getRecordId() == selectedRecordId) {
                // Employee type
                if (employeeType(selectedEmployeeId).equals("Teaching")) {
                    // Get overtime for teaching employee
                    totalHours = totalHours + eW.getHours();
                    totalOvertime = getOverTimeForTeachingEmployee(totalHours);
                } else {
                    // For non teaching employee
                    totalHours = totalHours + eW.getHours();
                    totalOvertime = totalOvertime + eW.getOvertime();
                }

                if (eW.getHours() == 0) {
                    totalAbsences = totalAbsences + 1;
                }

                Vector v = new Vector();
                v.add(eW.getId());
                v.add(eW.getDate());
                v.add(eW.getDay());
                v.add(eW.getHours());
                v.add(eW.getOvertime());
                table.addRow(v);
            }
        }

        hours = totalHours;
        overtime = totalOvertime;
        absences = totalAbsences;

        jLabelRecordId.setText("<html> <font color='blue'>" + String.valueOf(selectedRecordId) + "</font></html>");
        jLabelEmployeeType.setText("<html><font color='blue'>" + " - " + employeeType(selectedEmployeeId) + "</font></html>");
        jLabelTotalHours.setText(String.valueOf(totalHours));
        jLabelTotalOvertime.setText(String.valueOf(totalOvertime));
        jLabelTotalAbsences.setText(String.valueOf(totalAbsences));
    }

    /**
     * Load the update working days data
     *
     */
    public void updateWorkingDays() {
        try {
            // Days work table
            DefaultTableModel table = (DefaultTableModel) jTableWorkingDays.getModel();
            table.setRowCount(0);
            ArrayList<EditWorkingDays> workingDaysList = new Read(Data.EDIT_WORKING_DAYS).editWorkingDaysList();
            int totalHours = 0, totalOvertime = 0, totalAbsences = 0;
            for (EditWorkingDays eW : workingDaysList) {
                // System.out.println("getRecordId: " + eW.getRecordId() + " selectedRecordId: " + selectedRecordId);
                if (eW.getRecordId() == selectedRecordId) {
                    // Employee type
                    if (employeeType(selectedEmployeeId).equals("Teaching")) {
                        // Get overtime for teaching employee
                        totalHours = totalHours + eW.getHours();
                        totalOvertime = getOverTimeForTeachingEmployee(totalHours);
                    } else {
                        // For non teaching employee
                        totalHours = totalHours + eW.getHours();
                        totalOvertime = totalOvertime + eW.getOvertime();
                    }

                    if (eW.getHours() == 0) {
                        totalAbsences = totalAbsences + 1;
                    }

                    Vector v = new Vector();
                    v.add(eW.getId());
                    v.add(eW.getDate());
                    v.add(eW.getDay());
                    v.add(eW.getHours());
                    v.add(eW.getOvertime());
                    table.addRow(v);
                }
            }

            hours = totalHours;
            overtime = totalOvertime;
            absences = totalAbsences;

            jLabelRecordId.setText("<html> <font color='blue'>" + String.valueOf(selectedRecordId) + "</font></html>");
            jLabelEmployeeType.setText("<html><font color='blue'>" + " - " + employeeType(selectedEmployeeId) + "</font></html>");
            jLabelTotalHours.setText(String.valueOf(totalHours));
            jLabelTotalOvertime.setText(String.valueOf(totalOvertime));
            jLabelTotalAbsences.setText(String.valueOf(totalAbsences));
        } catch (Exception e) {
            System.out.println("updateWorkingDays" + e.getMessage());
        }
    }

    /**
     * Initialize the combo box department
     */
    public void comboBoxEmployee() {
        // Clear combobox department
        jComboBoxEmployee.removeAllItems();
        ArrayList<Employee> employeeList = new Read(Data.EMPLOYEE).employeeList();
        Collections.sort(employeeList, new SortEmployee().reversed());
        DefaultComboBoxModel jCEmployee = (DefaultComboBoxModel) jComboBoxEmployee.getModel();
        for (Employee e : employeeList) {
            // Object for comboItem employee
            String name = e.getFirstName() + " " + e.getSureName();
            Object obj = new ComboItem(e.getEmployeeId(), name);
            if (selectedEmployeeId == e.getEmployeeId()) {
                // Set the selected item
                jCEmployee.setSelectedItem(obj);
                jCEmployee.addElement(obj);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabelEmployeeType = new javax.swing.JLabel();
        jComboBoxEmployee = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabelRecordId = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButtonUpdate = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelTotalHours = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelTotalOvertime = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableWorkingDays = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabelTotalAbsences = new javax.swing.JLabel();
        jButtonCancel = new javax.swing.JButton();
        jButtonUpdatePayroll = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit Payroll");
        setAlwaysOnTop(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setText("EMPLOYEE:");

        jLabelEmployeeType.setText("- EMPLOYEE TYPE");

        jComboBoxEmployee.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxEmployee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("RECORD ID:");

        jLabelRecordId.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxEmployee, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelEmployeeType, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelRecordId, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelRecordId))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabelEmployeeType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("TOTAL HOURS:");

        jLabelTotalHours.setText("0");

        jLabel5.setText("TOTAL OVERTIME:");

        jLabelTotalOvertime.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTotalHours, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTotalOvertime, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 23, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelTotalHours)
                    .addComponent(jLabel5)
                    .addComponent(jLabelTotalOvertime)))
        );

        jTableWorkingDays.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "#", "DATE", "DAY", "HOURS", "OVERTIME"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableWorkingDays.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableWorkingDays.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableWorkingDaysMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableWorkingDays);
        if (jTableWorkingDays.getColumnModel().getColumnCount() > 0) {
            jTableWorkingDays.getColumnModel().getColumn(0).setMinWidth(40);
            jTableWorkingDays.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        jLabel1.setText("TOTAL ABSENCES:");

        jLabelTotalAbsences.setText("0");

        jButtonCancel.setText("CANCEL");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonUpdatePayroll.setText("UPDATE PAYROLL");
        jButtonUpdatePayroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdatePayrollActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButtonUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTotalAbsences, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonUpdatePayroll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonUpdate)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabelTotalAbsences))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonUpdatePayroll, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        // Check if not empty
        ArrayList<EditWorkingDays> workingDayList = new Read(Data.EDIT_WORKING_DAYS).editWorkingDaysList();
        if (workingDayList.isEmpty()) {
            this.dispose();
        } else {
            // Show input confirm dialog to ask the user 
            int result = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to cancel, Any changes on the table will not be save",
                    "Warning", JOptionPane.WARNING_MESSAGE);

            if (result == 0) {
                // Clear the data
                Writer w = new Writer(Data.EDIT_WORKING_DAYS);
                w.clearEditWorkingDaysList(new ArrayList<EditWorkingDays>());
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jTableWorkingDaysMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableWorkingDaysMouseClicked
        try {

            DefaultTableModel table = (DefaultTableModel) jTableWorkingDays.getModel();
            int selected = jTableWorkingDays.getSelectedRow();
            selectedTableRowId = Integer.parseInt(table.getValueAt(selected, 0).toString());

        } catch (Exception e) {
            System.out.println("jTableWorkingDaysMouseClicked() -> " + e.getMessage());
        }

    }//GEN-LAST:event_jTableWorkingDaysMouseClicked

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        if (selectedTableRowId > 0) {
            EditWorkingDayForm editWorkingDayForm = new EditWorkingDayForm(selectedEmployeeId, selectedRecordId, selectedTableRowId);
            // Form listener
            FormWindowListener listener = new FormWindowListener();
            editWorkingDayForm.addWindowListener(listener);
            editWorkingDayForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            editWorkingDayForm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Double click the table row to edit a record",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonUpdatePayrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdatePayrollActionPerformed
        System.out.println("employee id: " + employeeType(selectedEmployeeId) + " rate: " + employeeRate(selectedEmployeeId) + " hour: " + hours + " overtime: " + overtime + " absences: " + absences);
        try {
            // TODO - Update payrol records
            JOptionPane.showMessageDialog(this, "<html>This feature is not implemented yet! <br> Thank you and God bless us all...</html>", "Info", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No payroll data found!"
                    + " Please try again", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButtonUpdatePayrollActionPerformed

    public class FormWindowListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
        }

        @Override
        public void windowClosed(WindowEvent e) {
            updateWorkingDays();
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }

    }

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
            java.util.logging.Logger.getLogger(EditPayrollForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditPayrollForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditPayrollForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditPayrollForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditPayrollForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JButton jButtonUpdatePayroll;
    private javax.swing.JComboBox<String> jComboBoxEmployee;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelEmployeeType;
    private javax.swing.JLabel jLabelRecordId;
    private javax.swing.JLabel jLabelTotalAbsences;
    private javax.swing.JLabel jLabelTotalHours;
    private javax.swing.JLabel jLabelTotalOvertime;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableWorkingDays;
    // End of variables declaration//GEN-END:variables
}
