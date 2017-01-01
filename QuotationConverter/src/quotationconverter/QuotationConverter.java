/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quotationconverter;

import java.io.IOException;
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
        // TODO code application logic here
        CSVReader reader;
        reader =  CSVReader.newInstance("http://www4.bcb.gov.br/Download/fechamento/20170106.csv");
                
        Set<Map.Entry<String, Double>> entrySet = reader.getCurrencyQuotationsAsHash().entrySet();
        System.out.println("entradas : " + entrySet.size());
        for (Map.Entry entry : entrySet){
            System.out.println("entry : " + entry.toString());
        }
        
        
    }
    
}
