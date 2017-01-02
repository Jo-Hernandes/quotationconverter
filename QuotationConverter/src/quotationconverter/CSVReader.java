/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quotationconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.Stream;


public class CSVReader {
    
    private String url;
    
    private CSVReader(String url){
        this.url = url;
    }
    
    public static CSVReader newInstance(String url){
        return new CSVReader(url);
    }
    
    /**
     * handles the csv from the url given in the constructor using the consuming action passed
     * as parameter on the method signature
     * Throws IOException if some problem happens during file download
     * @param action
     * @throws IOException 
     */
    public void handleCSV(Consumer<String> action) throws IOException{
        URL url = new URL(this.url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (connection.getResponseCode() == 200) {
            try (InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(streamReader);
            Stream<String> lines = br.lines()) {
//            lines.forEach(s -> System.out.println(s));
            lines.forEach(action);
            }
        }
    }
    
    /**
     * Gets CSV file and handles it to build a hashmap containing the quotation values for each 
     * currency, using the currency name as the hash key 
     * @return valueToQuotations 
     */
    public HashMap<String, BigDecimal> getCurrencyQuotationsAsHash() throws IOException{
        HashMap<String, BigDecimal> valueToQuotations;
        valueToQuotations = new HashMap<>();
        handleCSV(new Consumer<String>() {
            @Override
            public void accept(String t) {
                String[] stringArray = t.split(";");
                String quotationType = stringArray[3];
                BigDecimal quotationValue =  new BigDecimal(stringArray[4].replace(",", "."));// Double.parseDouble(stringArray[4].replace(",", "."));
                valueToQuotations.put(quotationType, quotationValue);
            }
        });      
            return valueToQuotations;
        
    }
    
}
