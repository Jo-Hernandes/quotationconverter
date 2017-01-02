/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quotationconverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Converter {
    
    private static final String CBC_Url = "http://www4.bcb.gov.br/Download/fechamento/%s.csv";
    private HashMap<String, BigDecimal> savedQuotation;
    
    public Converter(){
        savedQuotation = new HashMap<>();
    }
    
    public Converter(String quotation){
        try {
            saveQuotation(quotation);
        } catch (Exception ex) {
            savedQuotation = new HashMap<>();
        } 
    }
    
    
    /**
     * gives the currency quotation of a value from a currency to 
     * another based on the quotation day passed as parameter in the method signature
     * @param from the initial currency
     * @param to the currency to convert
     * @param value the value to convert
     * @param quotation thay of the quotation, must be in the format “dd/MM/yyyy”
     * @return BigDecimal with the converted value
     * @throws RuntimeException if any parameter is not correct
     */
    public BigDecimal currencyQuotation(String from, String to, Number value, String quotation) throws RuntimeException {
        if (value.floatValue() < 0) throw new RuntimeException("value parameter cannot be negative");
        try {
            saveQuotation(quotation);
        } catch (IOException ex) {
            throw new RuntimeException("failed to access url", ex);
        }
        
        if (! savedQuotation.containsKey(from)) throw new RuntimeException("currency not found");
        if (! savedQuotation.containsKey(to)) throw new RuntimeException("currency not found");
        
        BigDecimal  val = savedQuotation.get(from).multiply(new BigDecimal(value.doubleValue()));
    
        return convert(val, savedQuotation.get(to));
    }
    
    /**
     * gives the currency quotation of a value from a currency to 
     * another based on the saved quotation day values saved previously in the other method call
     * that this ones overloads
     * @param from the initial currency
     * @param to the currency to convert
     * @param value the value to convert
     * @param quotation thay of the quotation, must be in the format “dd/MM/yyyy”
     * @return BigDecimal with the converted value
     * @throws RuntimeException if any parameter is not correct of if there is no savedQuotation values
     */
    public BigDecimal currencyQuotation(String from, String to, Number value) throws RuntimeException {
        if (value.floatValue() < 0) throw new RuntimeException("value parameter cannot be negative");
        if (savedQuotation == null || savedQuotation.isEmpty()) throw new RuntimeException("currency quotations not available");
        if (! savedQuotation.containsKey(from)) throw new RuntimeException("currency not found");
        if (! savedQuotation.containsKey(to)) throw new RuntimeException("currency not found");
        
        BigDecimal  val = savedQuotation.get(from).multiply(new BigDecimal(value.doubleValue()));
    
        return convert(val, savedQuotation.get(to));
    }
    
    /**
     * converts a value to another, based on the quotation passed as parameter with 2 decimal places and rounded half down
     * @param value the value to be converted
     * @param quotation the quotation value to use to convert
     * @return 
     */
    private BigDecimal convert(BigDecimal value, BigDecimal quotation){
        return new BigDecimal(value.doubleValue()).divide(quotation, 2, RoundingMode.HALF_DOWN);
    }
    
    
    /**
     * gets the weekday based on the date passed as parameter
     * if the date is a weekday, returns itself. if it's a weekend day, 
     * will return the next weekday from this date
     * @param date the day wich should be a weekday
     * @return a date on the weekday
     */
    private Date getWeekday(Date date){
        Date aux = date;
        while(DateUtils.isWeekdend(aux)) aux = DateUtils.getNextDay(aux);
        return aux;
    }
    
    /**
     * formats a date value on a string based on the BCB string used on the 
     * URL to download the CSV in the page's database
     * @param date the date to download the CSV
     * @return a string formated on the BCB format ("yyyyMMdd")
     */
    private String getDateStringBCBFormated(Date date){
        // BCB format >>> yyyyMMdd
        Format  df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }
    
    
    /**
     * saves the quotation values on this object's private hashmap based on the date passed as String
     * @param quotation on the format "yyyyMMdd"
     * @throws RuntimeException if the date format is not valid
     * @throws IOException if it fails to access the given URL
     */
    private void saveQuotation(String quotation) throws RuntimeException, IOException{
        Date dateFromString;
        try {
            dateFromString = DateUtils.getDateFromStringFormat(quotation, "dd/MM/yyyy");
        } catch (ParseException ex) {
            throw new RuntimeException("date format not valid", ex);
        }
        
        dateFromString = getWeekday(dateFromString);
        String quotationUrl = String.format(CBC_Url, getDateStringBCBFormated(dateFromString));
                
        savedQuotation = CSVReader.newInstance(quotationUrl).getCurrencyQuotationsAsHash();
        if (savedQuotation.isEmpty()) throw new RuntimeException("currency quotations not available");
    }
    
}
