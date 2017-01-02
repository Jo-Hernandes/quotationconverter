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
    
    public BigDecimal currencyQuotation(String from, String to, Number value, String quotation) throws Exception {
        if (value.floatValue() < 0) throw new RuntimeException("parameter cannot be negative");
        saveQuotation(quotation);
        
        
        if (! savedQuotation.containsKey(from)) throw new RuntimeException("currency not found");
        if (! savedQuotation.containsKey(to)) throw new RuntimeException("currency not found");
        
        BigDecimal  val = savedQuotation.get(from).multiply(new BigDecimal(value.doubleValue()));
    
        return convert(val, savedQuotation.get(to));
    }
    
    private BigDecimal convert(BigDecimal value, BigDecimal quotation){
        return new BigDecimal(value.doubleValue()).divide(quotation, 2, RoundingMode.HALF_DOWN);
    }
    
    private Date getWeekday(Date date){
        Date aux = date;
        while(DateUtils.isWeekdend(aux)) aux = DateUtils.getNextDay(aux);
        return aux;
    }
    
    private String getDateStringBCBFormated(Date date){
        // BCB format >>> yyyyMMdd
        Format  df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }
    
    
    private void saveQuotation(String quotation) throws RuntimeException, IOException{
        Date dateFromString;
        try {
            dateFromString = DateUtils.getDateFromStringFormat(quotation, "dd/MM/yyyy");
        } catch (ParseException ex) {
            throw new RuntimeException("date format not valid", ex);
        }
        
        dateFromString = getWeekday(dateFromString);
        String quotationUrl = String.format(CBC_Url, getDateStringBCBFormated(dateFromString));
                
        System.out.println(quotationUrl);
        savedQuotation = CSVReader.newInstance(quotationUrl).getCurrencyQuotationsAsHash();
        if (savedQuotation.isEmpty()) throw new RuntimeException("currency quotations not available");
    }
    
}
