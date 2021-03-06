/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Classes.Appointment;
import Classes.User;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * The Table which is displayed within the MainFrame containing all the 
 * necessary informations 
 * @author Patricia
 */
public class EventTable extends javax.swing.JPanel {

    /**
     * Creates new form EventTable
     */
    public EventTable() {
        initComponents();
        refreshTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EventPane = new javax.swing.JScrollPane();
        EventTable = new javax.swing.JTable();

        EventPane.setBackground(new java.awt.Color(255, 255, 255));
        EventPane.setForeground(new java.awt.Color(255, 255, 255));

        EventTable.setForeground(new java.awt.Color(0, 0, 0));
        EventTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event ID", "Event Name", "Event Date", "Host"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        EventTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EventTableMousePressed(evt);
            }
        });
        EventTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EventTableKeyPressed(evt);
            }
        });
        EventPane.setViewportView(EventTable);
        if (EventTable.getColumnModel().getColumnCount() > 0) {
            EventTable.getColumnModel().getColumn(0).setMinWidth(0);
            EventTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            EventTable.getColumnModel().getColumn(0).setMaxWidth(0);
            EventTable.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EventPane, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EventPane, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    /**
     * When the mouse is pressed further EventDetails are displayed in an 
     * {@link AppointmentForm} within the MainFrame, a double click will enable 
     * the user to edit the events information within the form.
     * 
     * @param evt a mouseclick 
     */
    private void EventTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EventTableMousePressed
        MainFrame.getObject().setSelectedEvent(EventTable.getSelectedRow());
        Appointment selectedEvent = MainFrame.getObject().getSelectedEvent();
        User user = MainFrame.getObject().getUser();
        if (evt.getClickCount() > 1) {
            if (user.equals(selectedEvent.getHost())) {
                MainFrame.getObject().showAppointment(true, true);
            } else {
                JOptionPane.showMessageDialog(null, "Not your Event to Edit");
            }
        } else {
            MainFrame.getObject().showAppointment(true, false);
        }
    }//GEN-LAST:event_EventTableMousePressed
    /**
     * With the Arrow Up & Arrow Down Keys, the user can go through the event
     * table which will display each event in an {@link AppointmentForm} within
     * the MainFrame.
     * @param evt the Key which was pressed 
     */
    private void EventTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EventTableKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DOWN 
                || evt.getKeyCode() == KeyEvent.VK_UP){
        MainFrame.getObject().setSelectedEvent(EventTable.getSelectedRow());
        Appointment selectedEvent = MainFrame.getObject().getSelectedEvent();
        User user = MainFrame.getObject().getUser();
        MainFrame.getObject().showAppointment(true, false);
        }
    }//GEN-LAST:event_EventTableKeyPressed
    
    /**
     * this function will refresh the table content whenever it's needed
     */
    public void refreshTable() {
        DefaultTableModel dfm = (DefaultTableModel) EventTable.getModel();
        MainFrame.getObject().getEvents().stream().forEach(
                (Appointment Appointment) -> {
            dfm.addRow(new Object[]{Appointment.getID(),
                Appointment.getName(),
                Appointment.getDateTime().toLocalDate(),
                Appointment.getHost().getEmail()
            });
        });
        EventTable.revalidate();
        EventTable.repaint();
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane EventPane;
    private javax.swing.JTable EventTable;
    // End of variables declaration//GEN-END:variables
    

}
