/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quotationconverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joohe
 */
public class QuotationConverter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Converter converter = new Converter();
        try {
            System.out.println("R$ " + converter.currencyQuotation("USD", "EUR", 100.00, "10/01/2017"));
        } catch (Exception ex) {
            Logger.getLogger(QuotationConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
