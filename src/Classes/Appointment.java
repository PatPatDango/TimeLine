/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class which handles the {@link Appointment} within the project
 * @author patricia
 */
public class Appointment {

    private int id = -1;
    private String name = null;
    private User host = null;
    private LocalDateTime dateTime = null;
    private int duration = -1;
    private String location = null;
    private java.util.ArrayList<User> participants = null;
    //private String attachementfiles;
    private String priority = null;
    //private String reminder = null;

    
    public Appointment(int id, String name, User host,
            LocalDateTime dateTime, int duration,
            String location, String priority, ArrayList<User> participants) {
        this.setId(id);
        this.setName(name);
        this.setHost(host);
        this.setDateTime(dateTime);
        this.setDuration(duration);
        this.setLocation(location);
        this.setParticipants(participants);
        this.setPriority(priority);

    }

    public Appointment(String name, User host,
            LocalDateTime dateTime, int duration,
            String location,String priority, ArrayList<User> participants) {

        this.setName(name);
        this.setHost(host);
        this.setDateTime(dateTime);
        this.setDuration(duration);
        this.setLocation(location);
        this.setParticipants(participants);
        this.setPriority(priority);

    }

    public Appointment(User user) {
        setHost(user);
        setDateTime(LocalDateTime.now());
        setParticipants(user);
    }

    //methods 
    public void setId(int _id) {
        id = _id;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        if (name != null) {
            return name;
        }
        return "";
    }

    public void setName(String _name) {
        name = _name;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User _host) {
        host = _host;
    }

    public LocalDateTime getDateTime() {
        if (dateTime != null) {
            return this.dateTime;
        }
        return LocalDateTime.MAX;
    }

    public void setDateTime(LocalDateTime _dateTime) {
        dateTime = _dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int _duration) {
        duration = _duration;
    }

    public String getLocation() {
        if (location != null) {
            return location;
        }
        return "";
    }

    public void setLocation(String _location) {
        location = _location;
    }

    public java.util.ArrayList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(java.util.ArrayList<User> _participants) {
        participants = _participants;
    }

     
    public String getPriority() {
        return priority;
    }

    public void setPriority(String _priority) {
        priority = _priority;
    }

    
    @Override
    public String toString() {
        return this.getName() + " "
                + this.getDateTime().toLocalDate().toString() + " "
                + this.getHost();
    }

    @Override
    public boolean equals(Object other) {

        // If the object is compared with itself then return true   
        if (other == this) {
            return true;
        }

        /* Check if other is an instance of User or not 
          "null instanceof [User]" also returns false */
        if (!(other instanceof Appointment)) {
            return false;
        }

        // Compare the usernames and return accordingly 
        return this.id == ((Appointment) other).id;
    }

    private void setParticipants(User user) {
        if (this.participants == null) {
            this.participants = new ArrayList<User>();
        }

        this.participants.add(user);

    }
}
