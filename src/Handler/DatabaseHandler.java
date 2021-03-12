/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import Classes.Appointment;
import java.sql.*;
import Classes.User;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.*;

/**
 * The Database Handler of the Project which manages storing data and fetching
 * them
 * @author patricia
 */
public class DatabaseHandler {

    private static Connection con;
    
    public DatabaseHandler() {
        con = getConnection();
    }

    /**
     * establishes a connection to the database
     * @return 
     */
    public static Connection getConnection() {
        final String USERNAME = "Admin";
        final String PASSWORD = "admin";
        String URL = "jdbc:mysql://localhost:3306/MyTimeScheduler?zeroDateTimeBehavior=convertToNull&serverTimezone=Europe/Berlin";
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return con;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * creates a new user within the database
     * @param User user which will be created
     * @param password encrypted
     */
    public void createNewUser(User User, String password) {
        String setuser = "INSERT INTO user( u_username, u_email, u_password, u_firstName, u_lastName) VALUES (?,?,?,?,?)";
        try ( PreparedStatement st = con.prepareStatement(setuser)) {
            st.setString(1, User.getUsername());
            st.setString(2, User.getEmail());
            st.setString(3, password);
            st.setString(4, User.getFirstname());
            st.setString(5, User.getLastname());
            st.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DatabaseHandler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        System.out.println("Insert new User Successfull");

    }

    /**
     * creates a new event within the database
     * @param event {@link Appointment} containing the information of the event
     * which will be created
     */ 
    public void createNewEvent(Appointment event) {
        String sql = "INSERT INTO event (E_eventName, E_DateTime, E_duration, E_location, E_host, E_priority) VALUES (?,?,?,?,?,?); ";
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, event.getName());
            st.setTimestamp(2, Timestamp.valueOf(event.getDateTime()));
            st.setInt(3, event.getDuration());
            st.setString(4, event.getLocation());
            st.setString(5, event.getHost().getUsername());
            st.setString(6, event.getPriority());
            st.executeUpdate();
            int event_ID = GetMaxEvents();
            InsertParticipants(event_ID, event.getParticipants());

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Returns the ID of the most recent created event
     * @return {@link Integer} 
     */
    private int GetMaxEvents() {
        String getID = "SELECT max(E_EventID) as EventID FROM Event";
        try ( PreparedStatement st = con.prepareStatement(getID)) {
            try ( ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int event_ID = rs.getInt(1);
                    return event_ID;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }


    /**
     * Inserts the Participants of an Event into the Database
     * @param eventID ID of the Appointment
     * @param participants List of Participants taking part in the appointment
     */
    private void InsertParticipants(int eventID, ArrayList<User> participants) {
        String set_p = "INSERT INTO participant VALUES (?,?)";
        try ( PreparedStatement st = con.prepareStatement(set_p)) {
            for (User p : participants) {
                st.setString(1, p.getUsername());
                st.setInt(2, eventID);
                st.addBatch();
            }
            st.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Checks whether an User exists with the entered username and password 
     * @param username 
     * @param password encrypted password
     * @return {@link User} if the user exists, else null
     */
    public User checkifUserExists(String username, String password) {
        User user = null;
        String sql = "Select * FROM user where u_username = ? AND u_password = ? AND  U_DELETED = 0 ";

        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, username);
            st.setString(2, password);
            try ( ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    user = getUser(rs);
                }

            }
        } catch (SQLException ex) {
            
        }
        return user;
    }

    /**
     * Checks whether an Email has already been taken
     * @param toBeChecked
     * @return true, if the Email is available, false else
     */
    public boolean UserEmailAvailable(Classes.Registration toBeChecked) {
        String sql = "Select  u_email FROM user WHERE u_email = ? AND u_deleted = 0;";
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, toBeChecked.getEmail());
            try ( ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     * Checks whether a username has already been taken
     * @param toBeChecked
     * @return true, if username is available, false else
     */
    public boolean UserNameAvailable(Classes.Registration toBeChecked) {
        String sql = "Select u_username FROM user WHERE u_username =? AND u_deleted = 0;";
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, toBeChecked.getUsername());
            try ( ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    /**
     * Function to edit information of a {@link User}
     * @param user which will be edited
     */         
    public void EditUser(User user) {    
        String sql = "Update user SET u_email = ?, u_firstName = ?, u_lastName = ?, u_admin = ? WHERE u_username = ?";
        try { 
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstname());
            ps.setString(3, user.getLastname());
            ps.setBoolean(4, user.getRole());
            ps.setString(5, user.getUsername());            
            ps.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DatabaseHandler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    /**
     * Edits the password of a user within the database
     * @param user the user who wants to change their password
     * @param newPassword the encrypted new password
     */
    public void EditPasswort(User user, String newPassword) {
        
        String updatepasswort = "Update user SET u_password = ? WHERE u_username = ?;";

        try ( PreparedStatement ps = con.prepareStatement(updatepasswort)) {
            
            ps.setString(1, newPassword);
            ps.setString(2, user.getUsername());
            ps.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DatabaseHandler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    /**
     * fetches the Password of a {@link User} from the database
     * @param user 
     * @return encrypted password of a user 
     */
    public String getPassword(User user) {
        String getPassword = "SELECT u_password From User WHERE u_username = ?;";
        String password = "";
        try ( PreparedStatement ps = con.prepareStatement(getPassword)) {
            ps.setString(1, user.getUsername());
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    password = rs.getString("u_password");
                }
            }
        } catch (SQLException e) {
            
        }
        return password;
    }

    /**
     * Returns an {@link ArrayList} of all Events of a {@link User} 
     * @param user who takes part in the events
     * @return List of Events
     */
    
    public ArrayList<Appointment> getUsersEvents(User user) {
        String sql
                = "SELECT * FROM event "
                + "WHERE E_eventID IN (SELECT P_eventID "
                + "FROM participant WHERE p_username = ?) "
                + "AND E_deleted = 0 "
                + "ORDER BY E_DateTime DESC;";
        ArrayList<Appointment> usersEvents = new ArrayList<>();
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, user.getUsername());
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Appointment temp = getEvent(rs);
                    usersEvents.add(temp);
                }
                setParticipants(usersEvents);
            }
        } catch (SQLException ex) {
            System.out.println("get Users Events:" + ex.getMessage());
        }
        return usersEvents;
    }

    /**
     * Returns an {@link User} Object by the username 
     * @param username of the logged in user
     * @return an User Object
     */
    public User getUserByUserName(String username) {
        String sql = "SELECT * FROM User WHERE u_username = ?;";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return getUser(rs);
                }
            }
        } catch (SQLException ex) {
            
        }
        return null;
    }

    /**
     * fetches the information of one dataset from the database 
     * and sets these information into an new {@link Appointment} 
     * @param rs
     * @return an new appointment object
     * @throws SQLException 
     */
    //getEvents from the Database
    //E_eventID, E_eventName, E_DateTime, E_duration, E_deleted, E_location, E_Priority, E_host
    public Appointment getEvent(ResultSet rs) throws SQLException {
        return new Appointment(
                rs.getInt(1),
                rs.getString(2),
                getUserByUserName(rs.getString(8)),
                rs.getTimestamp(3).toLocalDateTime(),
                rs.getInt(4),
                rs.getString(6),
                rs.getString(7),
                null);
        
    }

    /**
     * returns an {@link ArrayList} of all {@link User} which are stored in the 
     * database and are not deleted
     * @return list of all users
     */
    //get all user from the database for the admin interface 
    public ArrayList<User> getAllUser() {
        String sql = "SELECT * FROM User WHERE U_DELETED = 0";
        ArrayList<User> UserList = new ArrayList<>();
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    User user = getUser(rs);
                    UserList.add(user);
                }
                return UserList;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return UserList;
    }
    
    /**
     * fetches the information of one dataset from the database 
     * and sets these information into an new {@link User} 
     * @param rs
     * @return an new User object
     * @throws SQLException 
     */
    // User String firstname, String lastname, String username, String email)
    // u_username, u_email, u_password, u_firstName, u_lastName, u_deleted, u_admin
    public User getUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getString(4),
                rs.getString(5),
                rs.getString(1),
                rs.getString(2),
                rs.getBoolean(7)
        );

    }

    /**
     * The new updated information of an {@link User} who already exists 
     * get saved/ updated in the database
     * @param user 
     */
    //Admin Interface - User aktualisieren 
    public void UpdateUser(User user) {
        String sql = "Update user SET u_firstname = ? , u_lastname = ?, u_mail = ?, u_role = ? WHERE u_username = ?;";
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, user.getFirstname());
            st.setString(2, user.getLastname());
            st.setString(3, user.getEmail());
            st.setBoolean(4, user.getRole());
            st.setString(5, user.getUsername());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * The new updated information of an {@link Appointment} which already exists 
     * get saved/ updated in the database
     * @param event 
     */
    public void UpdateEvent(Appointment event) {
        String sql = "Update event SET E_EventName = ?,"
                + " E_Location = ?, E_DateTime = ?,"
                + " E_duration = ?, E_Priority = ?  WHERE E_EventID = ?;";
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, event.getName());
            st.setString(2, event.getLocation());
            st.setTimestamp(3, Timestamp.valueOf(event.getDateTime()));
            st.setInt(4, event.getDuration());
            st.setString(5, event.getPriority());
            st.setInt(6, event.getID());
            st.executeUpdate();
            deleteParticipants(event.getID());
            InsertParticipants(event.getID(), event.getParticipants());
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * An list of all participants is getting created for an certain event
     * @param eventID
     * @return an list of all participants for the specific event
     */
    public ArrayList<User> getParticipants(int eventID) {
        String sql = "SELECT u.* FROM (user u INNER JOIN participant p ON p_username = u.u_username) WHERE p.P_eventID = ?;";
        ArrayList<User> participants = new ArrayList<>();
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, eventID);
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Classes.User participant = getUser(rs);
                    participants.add(participant);
                }
            }
        } catch (SQLException ex) {
        }
        return participants;
    }

    /**
     * sets the list of participants of an appointment which is fetched from 
     * the database for several appointments
     * @param usersEvents 
     */
    private void setParticipants(ArrayList<Appointment> usersEvents) {
        usersEvents.stream().forEach(appointment -> {
            appointment.setParticipants(getParticipants(appointment.getID()));
        });
    }

    /**
     * deletes all participants from an certain {@link Appointment} 
     * in the database
     * @param eventID of the used appointment
     */
    private void deleteParticipants(int eventID) {
        String sql = "DELETE FROM participant WHERE p_eventID = ?";
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, eventID);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Fetches all {@link Appointment} from the database of the current week 
     * and saves them into a list
     * @param user
     * @return a list of {@link Appointment}
     */
    public ArrayList<Appointment> getEventOfWeek(User user) {
        ArrayList<Appointment> usersEvents = new ArrayList<>();
        String sql = "SELECT * FROM event  "
                + "WHERE E_eventID IN (SELECT P_eventID FROM participant WHERE p_username = ?)   "
                + "AND E_deleted = 0  "
                + "AND DATE(E_DateTime)  BETWEEN DATE(NOW())  "
                + "AND DATE(NOW() + INTERVAL (6 - WEEKDAY(NOW())) DAY) ;";

        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, user.getUsername());
            
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Appointment temp = getEvent(rs);
                    usersEvents.add(temp);
                }
                setParticipants(usersEvents);
            }
        } catch (SQLException ex) {
        }
        return usersEvents;
    }
    /**
     * Returns an {@link ArrayList} of Events of the next Month of a {@link User}
     * @param user of which the events will be fetched
     * @return List of Events
     */
    public ArrayList<Appointment> getEventOfMonth(User user) {
        ArrayList<Appointment> usersEvents = new ArrayList<>();
        String sql = "SELECT * FROM event  "
                + "WHERE E_eventID IN (SELECT P_eventID FROM participant WHERE p_username = ?)   "
                + "AND E_deleted = 0  "
                + "AND DATE(E_dateTime) BETWEEN DATE(now()) AND "
                + "CAST(DATE_FORMAT(DATE(now() + INTERVAL 1 MONTH),'%Y-%m-01') as DATE);";

        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, user.getUsername());
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Appointment temp = getEvent(rs);
                    usersEvents.add(temp);
                }
                setParticipants(usersEvents);
            }
        } catch (SQLException ex) {
        }
        return usersEvents;
    }

    /**
     * Returns an {@link ArrayList} of {@link Appointment}s of a certain 
     * {@link java.util.Date} of an {@link User}
     * @param user who takes part in the events
     * @param date of the events
     * @return List of Events
     */
    public ArrayList<Appointment> getEventsOfDay(User user, LocalDate date) {
        String sql
                = "SELECT * FROM event "
                + "WHERE E_eventID IN (SELECT P_eventID "
                + "FROM participant WHERE p_username = ?) "
                + "AND E_deleted = 0 AND DATE(E_DateTime) = DATE(?) ;";
        ArrayList<Appointment> usersEvents = new ArrayList<>();
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, user.getUsername());
            st.setString(2, date.toString());
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Appointment temp = getEvent(rs);
                    usersEvents.add(temp);
                }
                setParticipants(usersEvents);
            }
        } catch (SQLException ex) {
        }
        return usersEvents;
    }

    /**
     * Deletes an {@link User}
     * @param user which will be deleted
     */
    public void deleteUser(User user) {
        String sql = "Update user SET u_deleted = 1  WHERE u_username = ?";
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, user.getUsername());
            st.executeUpdate();

            deleteUserinParticipants(user);
            deleteEventsOfHost(user);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        //delete everywhere where host and event 
    }

    /**
     * deletes an Event 
     * @param event event which will be deleted
     */
    public void deleteEvent(Appointment event) {
        String sql = "Update event SET e_deleted = 1  WHERE e_eventID = ?";
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, event.getID());
            st.executeUpdate();
            deleteParticipants(event.getID());
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * deletes a user from all {@link Appointment}
     * @param user which will be deleted
     */
    private void deleteUserinParticipants(User user) {
        String sql = "DELETE FROM participant WHERE p_username = ?";
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, user.getUsername());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Deletes user from an Event within the database
     * @param user which will be deleted
     * @param evt from which the user will be deleted
     */
    public void deleteUserFromEvent(User user, Appointment evt){
        String sql = "DELETE FROM participant WHERE p_username = ? AND p_eventID = ?";
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, user.getUsername());
            st.setInt(2, evt.getID());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Deletes all the {@link Appointment}  of a {@link User} which he's been
     * hosting
     * @param user 
     */
    private void deleteEventsOfHost(User user) {
        String sql = "UPDATE Event SET E_deleted = 0 WHERE E_host = ?";
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, user.getUsername());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * returns an array containing integer numbers respectively to the amount
     * of events of their priority
     * @param user
     * @return {@link Array} of {@link Integer}
     */
    public int[] getThisWeeksPriority(User user) {
        int[] arr = {0, 0, 0};
        String sql = "SELECT count(E_Priority) as qty, E_Priority "
                + "FROM event "
                + "WHERE E_eventID IN (SELECT P_eventID FROM participant WHERE p_username = ?) "
                + "AND E_deleted = 0 "
                + "AND DATE(E_DateTime) BETWEEN DATE(NOW()) "
                + "AND DATE(NOW() + INTERVAL (6 - WEEKDAY(NOW())) DAY) "
                + "GROUP BY E_Priority; ";
        try ( PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, user.getUsername());
            
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    switch (rs.getString("E_Priority")) {
                        case "Low":
                            arr[0] = rs.getInt("qty");
                            break;
                        case "Medium":
                            arr[1] = rs.getInt("qty");
                            break;
                        case "High":
                            arr[2] = rs.getInt("qty");
                            break;
                        default:
                            break;
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
}
