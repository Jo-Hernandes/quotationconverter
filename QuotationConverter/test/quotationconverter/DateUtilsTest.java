/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quotationconverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joohe
 */
public class DateUtilsTest {
    
    public DateUtilsTest() {
    }
  
    /**
     * Test of getDateFromStringFormat method, of class DateUtils.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetDateFromStringFormat() throws Exception {
        System.out.println("getDateFromStringFormat");
        String dateString = "02/01/2017";
        String formatString = "dd/MM/yyyy";
        
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date expResult = format.parse("January 2, 2017");
        Date result = DateUtils.getDateFromStringFormat(dateString, formatString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of isWeekdend method, of class DateUtils.
     */
    @Test
    public void testIsWeekdend() {
        System.out.println("isWeekdend");
        Date date = new Date(2017, 0, 1);
        boolean expResult = false;
        boolean result = DateUtils.isWeekdend(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNextDay method, of class DateUtils.
     */
    @Test
    public void testGetNextDay() {
        System.out.println("getNextDay");
        Date date = new Date(2017, 0, 1);
        Date expResult = new Date(2017, 0, 2);
        Date result = DateUtils.getNextDay(date);
        assertEquals(expResult, result);
    }
    
}
