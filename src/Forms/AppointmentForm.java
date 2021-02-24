/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Classes.*;
import Handler.DatabaseHandler;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * The Form which deals with Appointments and their Informations
 * @author patricia
 */
public class AppointmentForm extends javax.swing.JPanel {

    private Appointment evt = null;
    private DatabaseHandler dbHandler = new DatabaseHandler();
    private ArrayList<User> participants = new ArrayList<>();
    private ArrayList<User> otherUser = new ArrayList<>();
    private DefaultListModel modelParticipants = new DefaultListModel();
    private DefaultListModel modelOtherUser = new DefaultListModel();

    /**
     * Creates new form JPAppointment to Add a new Event
     */
    public AppointmentForm(boolean isAppointment, boolean isEnabled) {
        initComponents();
        setComponent(isAppointment);
        setAccessMode(isEnabled);
        setButtonLayout(isAppointment, isEnabled);
        //this.button_saveNewEvent.setEnabled(true);
        //this.button_saveNewEvent.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Header = new javax.swing.JPanel();
        label_Editor = new javax.swing.JLabel();
        Center = new javax.swing.JPanel();
        Middle = new javax.swing.JPanel();
        txt_eventname = new javax.swing.JTextField();
        txt_HostName = new javax.swing.JTextField();
        LabelDuration = new javax.swing.JLabel();
        txt_eventduration = new javax.swing.JFormattedTextField();
        LabelMinutes = new javax.swing.JLabel();
        LabelLocation = new javax.swing.JLabel();
        txt_eventlocation = new javax.swing.JTextField();
        combobox_eventpriority = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jDC_eventDate = new com.toedter.calendar.JDateChooser();
        combobox_eventTime = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        button_addParticipant = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        List_otherUser = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        button_removeParticipant = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        List_Participants = new javax.swing.JList<>();
        button_saveNewEvent = new javax.swing.JButton();
        button_saveSettings = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jBDeleteEvent = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        Header.setBackground(new java.awt.Color(196, 209, 248));

        label_Editor.setBackground(new java.awt.Color(37, 186, 190));
        label_Editor.setFont(new java.awt.Font("Apple Color Emoji", 1, 24)); // NOI18N
        label_Editor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Editor.setText("~ Appointment Editor ~");

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_Editor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_Editor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );

        add(Header, java.awt.BorderLayout.PAGE_START);

        Center.setBackground(new java.awt.Color(255, 255, 255));

        Middle.setBackground(new java.awt.Color(255, 255, 255));

        txt_eventname.setText("enter an Event Name");

        txt_HostName.setText("enter a Host Name");

        LabelDuration.setText("Duration:");

        txt_eventduration.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        txt_eventduration.setText("Duration in");

        LabelMinutes.setText("Minutes");

        LabelLocation.setText("Location:");

        txt_eventlocation.setText("enter a Location");

        combobox_eventpriority.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Low", "Medium", "High" }));
        combobox_eventpriority.setSelectedIndex(1);
        combobox_eventpriority.setToolTipText("");

        jLabel10.setText("Event Name:");

        jLabel11.setText("Host Name:");

        jLabel9.setText("Date/Time:");

        jDC_eventDate.setOpaque(false);

        combobox_eventTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08:15:00", "08:30:00", "08:45:00", "09:00:00", "09:15:00", "09:30:00", "09:45:00", "10:00:00", "10:15:00", "10:30:00", "10:45:00", "11:00:00", "11:15:00", "11:30:00", "11:45:00", "12:00:00", "12:15:00", "12:30:00", "12:45:00", "13:00:00", "13:15:00", "13:30:00", "13:45:00", "14:00:00", "14:15:00", "14:30:00", "14:45:00", "15:00:00", "15:15:00", "15:30:00", "15:45:00", "16:00:00", "16:15:00", "16:30:00", "16:45:00", "17:00:00", "17:15:00", "17:30:00", "17:45:00", "18:00:00", "18:15:00", "18:30:00", "18:45:00", "19:00:00", "19:15:00", "19:30:00", "19:45:00", "20:00:00", " " }));
        combobox_eventTime.setToolTipText("");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Priority:");

        javax.swing.GroupLayout MiddleLayout = new javax.swing.GroupLayout(Middle);
        Middle.setLayout(MiddleLayout);
        MiddleLayout.setHorizontalGroup(
            MiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MiddleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(LabelDuration)
                    .addComponent(LabelLocation)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MiddleLayout.createSequentialGroup()
                        .addGroup(MiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_HostName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_eventlocation, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_eventname, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                        .addGap(5, 5, 5))
                    .addGroup(MiddleLayout.createSequentialGroup()
                        .addGroup(MiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDC_eventDate, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(MiddleLayout.createSequentialGroup()
                                .addComponent(txt_eventduration, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(LabelMinutes)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(MiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(combobox_eventpriority, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combobox_eventTime, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        MiddleLayout.setVerticalGroup(
            MiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MiddleLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(MiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_eventname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_HostName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_eventlocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelLocation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(MiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDC_eventDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combobox_eventTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelDuration)
                    .addComponent(txt_eventduration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combobox_eventpriority, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addGap(10, 10, 10))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Users:");

        button_addParticipant.setText("+");
        button_addParticipant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_addParticipantActionPerformed(evt);
            }
        });

        List_otherUser.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        List_otherUser.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                List_otherUserValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(List_otherUser);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(button_addParticipant, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_addParticipant))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Participants");

        button_removeParticipant.setText("-");
        button_removeParticipant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_removeParticipantActionPerformed(evt);
            }
        });

        List_Participants.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                List_ParticipantsValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(List_Participants);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_removeParticipant, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_removeParticipant))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
        );

        button_saveNewEvent.setText("Save New Event");
        button_saveNewEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_saveNewEventActionPerformed(evt);
            }
        });

        button_saveSettings.setText("Save Settings");
        button_saveSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_saveSettingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button_saveNewEvent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(button_saveSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_saveSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_saveNewEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jPanel4.setBackground(new java.awt.Color(196, 209, 248));

        jBDeleteEvent.setText("DeleteEvent");
        jBDeleteEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDeleteEventActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBDeleteEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBDeleteEvent, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout CenterLayout = new javax.swing.GroupLayout(Center);
        Center.setLayout(CenterLayout);
        CenterLayout.setHorizontalGroup(
            CenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Middle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CenterLayout.setVerticalGroup(
            CenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CenterLayout.createSequentialGroup()
                .addComponent(Middle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(Center, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Adds a user to the List of Participants of an Event
     * @param evt 
     */
    private void button_addParticipantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_addParticipantActionPerformed
        //Fetch Object which is to be Added
        if (List_otherUser.getSelectedIndex() >= 0) {
            Classes.User addToEvent = otherUser.get(List_otherUser.getSelectedIndex());
            Object userMail = modelOtherUser.getElementAt(List_otherUser.getSelectedIndex());

            //Get Model of the JList
            modelOtherUser = (DefaultListModel) List_otherUser.getModel();
            //remove element from ArrayList otherUser
            otherUser.remove(List_otherUser.getSelectedIndex());

            //remove the Element from the Model of the List
            modelOtherUser.removeElementAt(List_otherUser.getSelectedIndex());
            //Set the Model of the new List
            List_otherUser.setModel(modelOtherUser);
            //Get the Participant model
            modelParticipants = (DefaultListModel) List_Participants.getModel();
            //Add the deleted Row to the other one
            modelParticipants.addElement(userMail);
            //Add the user to the userList
            participants.add(addToEvent);
            //Set Model of other Participants
            List_Participants.setModel(modelParticipants);
        }
    }//GEN-LAST:event_button_addParticipantActionPerformed
    /**
     * Removes an Participant from the List of Participants
     * @param evt 
     */
    private void button_removeParticipantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_removeParticipantActionPerformed

        //Temp Speichern.
        Classes.User deleteFromEvent = participants.get(List_Participants.getSelectedIndex());
        if (!deleteFromEvent.getEmail().trim().equals(this.txt_HostName.getText().trim())) {
            Object userMail = modelParticipants.getElementAt(List_Participants.getSelectedIndex());
            //Get Model of the JList
            modelParticipants = (DefaultListModel) List_Participants.getModel();

            //remove element from ArrayList participants
            participants.remove(List_Participants.getSelectedIndex());
            //remove the Element from the Model of the List
            modelParticipants.removeElementAt(List_Participants.getSelectedIndex());
            //Set the Model of the new List
            List_Participants.setModel(modelParticipants);

            //Get the other User model
            modelOtherUser = (DefaultListModel) List_otherUser.getModel();
            //Add the deleted Row to the other one
            modelOtherUser.addElement(userMail);
            //Add the user to the userList
            otherUser.add(deleteFromEvent);
            //Set Model of other User
            List_otherUser.setModel(modelOtherUser);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "You can't delete the host");
        }

    }//GEN-LAST:event_button_removeParticipantActionPerformed
    /**
     * Only enable the button if a valid value of the list is selected
     * @param evt 
     */
    private void List_otherUserValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_List_otherUserValueChanged
        if (List_otherUser.getSelectedIndex() >= 0) {
            button_addParticipant.setEnabled(true);
        } else {
            button_addParticipant.setEnabled(false);
        }
    }//GEN-LAST:event_List_otherUserValueChanged
    /**
     * Only enable the button if a valid value of the list is selected
     * @param evt 
     */
    private void List_ParticipantsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_List_ParticipantsValueChanged
        if (List_Participants.getSelectedIndex() >= 0
                && !(participants.get(List_Participants.getSelectedIndex()).getEmail().equals(txt_HostName.getText().trim()))) {
            button_removeParticipant.setEnabled(true);
        } else {
            button_removeParticipant.setEnabled(false);
        }

    }//GEN-LAST:event_List_ParticipantsValueChanged

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    /**
     * Saves the information given within this form if all of them are valid
     * @param evt 
     */
    private void button_saveSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_saveSettingsActionPerformed

        newEventSpecifications();
        if (testIfEventInformationIsSufficient(this.evt)) {
            dbHandler.UpdateEvent(getEvent());
            MainFrame.getObject().updateEvents();
        } else {
            JOptionPane.showMessageDialog(null, "Please Check your Input");
        }

    }//GEN-LAST:event_button_saveSettingsActionPerformed
    /**
     * Saves the information of a newly created Event, but only if given input is valid
     * @param evt 
     */
    private void button_saveNewEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_saveNewEventActionPerformed
        newEventSpecifications();
        dbHandler.createNewEvent(getEvent());
        MainFrame.getObject().updateEvents();
    }//GEN-LAST:event_button_saveNewEventActionPerformed
    /**
     * Deletes a selected Event
     * @param evt 
     */
    private void jBDeleteEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDeleteEventActionPerformed
        dbHandler.deleteEvent(getEvent());
        MainFrame.getObject().updateEvents();
    }//GEN-LAST:event_jBDeleteEventActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Center;
    private javax.swing.JPanel Header;
    private javax.swing.JLabel LabelDuration;
    private javax.swing.JLabel LabelLocation;
    private javax.swing.JLabel LabelMinutes;
    private javax.swing.JList<String> List_Participants;
    private javax.swing.JList<String> List_otherUser;
    private javax.swing.JPanel Middle;
    private javax.swing.JButton button_addParticipant;
    private javax.swing.JButton button_removeParticipant;
    private javax.swing.JButton button_saveNewEvent;
    private javax.swing.JButton button_saveSettings;
    private javax.swing.JComboBox<String> combobox_eventTime;
    private javax.swing.JComboBox<String> combobox_eventpriority;
    private javax.swing.JButton jBDeleteEvent;
    private com.toedter.calendar.JDateChooser jDC_eventDate;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label_Editor;
    private javax.swing.JTextField txt_HostName;
    private javax.swing.JFormattedTextField txt_eventduration;
    private javax.swing.JTextField txt_eventlocation;
    private javax.swing.JTextField txt_eventname;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Sets components of the form, depending whether it's a new Appointment
     * or an Appointment which has to be Edited
     * @param isAppointment true if there's an appointment which has to be 
     * edited
     */
    private void setComponent(boolean isAppointment) {

        if (isAppointment) {
            setEvent(MainFrame.getObject().getSelectedEvent());
            setEventSpecification();
        } else {
            setEvent(new Appointment(MainFrame.getObject().getUser()));
            setEventSpecification();
        }
    }
    /**
     * Sets the access Mode of the form. 
     * @param settings true, if the Appointment was clicked on twice, false
     * otherwise
     */
    private void setAccessMode(boolean settings) {
        button_addParticipant.setEnabled(false);
        button_removeParticipant.setEnabled(false);

        if (!settings) {
            txt_HostName.setEnabled(settings);
            txt_eventname.setEnabled(settings);
            txt_eventlocation.setEnabled(settings);
            txt_eventduration.setEnabled(settings);
            combobox_eventpriority.setEnabled(settings);
            combobox_eventTime.setEnabled(settings);
            jDC_eventDate.setEnabled(settings);
            button_saveSettings.setEnabled(false);
            jBDeleteEvent.setEnabled(true);
        }
    }
    /**
     * @return the Name of the Appointment which was typed in the form
     */
    public String getEventName() {
        return txt_eventname.getText();
    }

    /**
     * @return the Location which was typed in the form
     */
    public String getEventLocation() {
        return txt_eventlocation.getText();
    }
    /**
     * @return the Duration which was typed in the form
     */
    public int getDuration() {
        return Integer.parseInt(txt_eventduration.getText());
    }
    /**
     * @return the Duration which was chosen in the form
     */
    private Date getDate() {
        return this.jDC_eventDate.getDate();
    }
    /**
     * @return the time which was chosen in the form
     */
    private String getTime() {
        return this.combobox_eventTime.getSelectedItem().toString();
    }
    /**
     * @return the Priority which was chosen in the form
     */
    public String getPriority() {
        return this.combobox_eventpriority.getSelectedItem().toString();
    }

    /**
     * @return the Date which was typed in the form
     */
    public LocalDateTime getDateTime() {
        return Handler.AppointmentHandler.convertToDateTime(getDate(), getTime());
    }
    /**
     * 
     * @return the List of Users which were selected to partake in an 
     * Appointment
     */
    public ArrayList<User> getParticipants() {
        return this.participants;
    }
    /**
     * Sets the Event which has been parsed to the form by the user
     * @param selectedEvent 
     */
    private void setEvent(Appointment selectedEvent) {
        if (selectedEvent != null) {
            this.evt = selectedEvent;
        }
    }
    /** 
     * @return the Event
     */
    private Appointment getEvent() {
        return evt;
    }
    /**
     * Fills the form with the needed information of the Event which wants to be 
     * edited
     */
    private void setEventSpecification() {
        this.txt_HostName.setText(evt.getHost().getEmail());
        this.txt_HostName.setEnabled(false);
        this.txt_eventname.setText(evt.getName());
        this.txt_eventlocation.setText(evt.getLocation());
        this.txt_eventduration.setText(String.valueOf(evt.getDuration()));
        this.jDC_eventDate.setDate(Date.from(evt.getDateTime().toInstant(ZoneOffset.UTC)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formatDateTime = evt.getDateTime().format(formatter);
        this.combobox_eventTime.setSelectedItem(formatDateTime);
        this.combobox_eventpriority.setSelectedItem(evt.getPriority());
        this.participants = evt.getParticipants();
        
        //Participants
        participants.stream().forEach((User) -> {
            modelParticipants.addElement(User.getEmail());
        });

        List_Participants.setModel(modelParticipants);
        List_Participants.setSelectionMode(javax.swing.DefaultListSelectionModel.SINGLE_SELECTION);

        //Other user
        this.otherUser = dbHandler.getAllUser();

        otherUser.removeAll(evt.getParticipants());
        otherUser.stream().forEach(User -> {
            modelOtherUser.addElement(User.getEmail());
        });
        List_otherUser.setModel(this.modelOtherUser);
        List_otherUser.setSelectionMode(javax.swing.DefaultListSelectionModel.SINGLE_SELECTION);
    }
    /**
     * Updates the Events Specification after it has been modified or newly 
     * been state
     */
    private void newEventSpecifications() {
        evt.setName(getEventName());                                       //Name
        evt.setDuration(getDuration());                                    //Duration
        evt.setDateTime(getDateTime());                                    //DateTime
        evt.setHost(MainFrame.getObject().getUser());                      //Host
        evt.setParticipants(getParticipants());                            //Participants
        evt.setPriority(getPriority());                                    //Priority 
        evt.setLocation(getEventLocation());
    }

    
    /**
     * Depending on the Action of the user, certain buttons are enabled
     * @param appointment tells the form whether there is an {@link Appointment}
     * to be edited or not
     * @param settings tells the form whether the user just wants to view or 
     * edit an {@link Appointment}
     */
    private void setButtonLayout(boolean appointment, boolean settings) {
        // if app && setting => save settings ; 
        // if app => delete
        // if no app && setting => save new Event
            button_saveSettings.setVisible(appointment && settings);
            button_saveSettings.setEnabled(appointment && settings);
            button_saveNewEvent.setVisible(!appointment && settings);
            button_saveNewEvent.setEnabled(!appointment && settings);
            jBDeleteEvent.setVisible(appointment && !settings);
            jBDeleteEvent.setEnabled(appointment && !settings);
    }
    /**
     * 
     * @param evt informations of an {@link Appointment}
     * @return true, if input of the user was valid, else false
     */
    private boolean testIfEventInformationIsSufficient(Appointment evt) {

        return (evt.getName().matches("^[a-zA-Z\\s]*$")
                && evt.getDuration() > 0
                && evt.getLocation().trim().matches("^[a-zA-Z]*$"));
    }

}