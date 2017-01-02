/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quotationconverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
  
    /**
     * gets a Date object from a string containing a date and the string format it is shown
     * example : 25/11/1965 string has the format dd/mm/yyyy
     * @param dateString containting the date
     * @param formatString with the formate the date is shown
     * @return date formated
     * @throws ParseException 
     */
    public static Date getDateFromStringFormat(String dateString, String formatString) throws ParseException{
        DateFormat formater;
        formater = new SimpleDateFormat(formatString, Locale.ENGLISH);
        Date date = formater.parse(dateString);
        return date;        
    }   
    
    /**
     * tells if the date passed as parameter is a weekend day or not
     * @param date
     * @return true if is weekend or false if it's not
     */
    public static boolean isWeekdend(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return !(dayOfWeek != Calendar.SUNDAY && dayOfWeek != Calendar.SATURDAY);
    }
    
    /**
     * gets a Date object that is the next day from the date passed as parameter
     * @param date
     * @return the next day from date
     */
    public static Date getNextDay(Date date){
        Calendar c = Calendar.getInstance(); 
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.getTime();    
    }     
    
}