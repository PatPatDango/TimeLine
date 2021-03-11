/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import Classes.Appointment;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Handler which handles certain functions regarding appointments to 
 * facilitate a better flow of the program.
 * @author patricia
 */
public class AppointmentHandler {
    private DatabaseHandler dbhandler = new DatabaseHandler();
    
    /**
     * Returns an {@link ArrayList} of {@link Appointment}s of a certain day
     * @param usersEvents
     * @param chosenDate
     * @return {@link ArrayList}
     */
    public ArrayList<Appointment> getEventsOfDay(ArrayList<Appointment> usersEvents, Date chosenDate) {
        ArrayList<Appointment> daysEvents = new ArrayList<>();
     
        usersEvents.stream().forEach(evt -> {
            if (compareDates(evt.getDateTime(), chosenDate)) {
                daysEvents.add(evt);
            }
        });

        return daysEvents;
    }
    
    /**
     * static method to compare a {@link Date} with a {@link LocalDateTime}
     * @param eventDate 
     * @param chosenDate
     * @return true if the dates are equal 
     */
    private static boolean compareDates(LocalDateTime eventDate, Date chosenDate){   
        return eventDate.toLocalDate().equals(dateToLocalDate(chosenDate));   
    }
    /**
     * Converts a {@link Date} to {@link LocalDate}
     * @param date
     * @return a {@link LocalDate}
     */
    public static LocalDate dateToLocalDate(Date date){
        LocalDate converted = null;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateFormatted = formatter.format(date);
        converted = LocalDate.parse(dateFormatted);
        return converted;
    }
    
    /**
     * Converts a {@link Date} to {@link LocalDateTime}
     * @param date
     * @param time
     * @return a {@link LocalDateTime} 
     */
    public static LocalDateTime convertToDateTime(Date date, String time){
        LocalDateTime converted = null;
        
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String dateFormatted = dateToLocalDate(date).toString();
        converted = LocalDateTime.parse(dateFormatted+" "+time,formatter);
        
        return converted;
    }

}
