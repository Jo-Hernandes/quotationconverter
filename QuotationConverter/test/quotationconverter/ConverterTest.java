/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quotationconverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author joohe
 */
public class ConverterTest {
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    
    public ConverterTest() {
    }
    @BeforeClass
    public static void setUpClass() {
    }
    @AfterClass
    public static void tearDownClass() {
    } 
    @Before
    public void setUp() {
    }
    @After
    public void tearDown() {
    }

    
    /**
     * Test of currencyQuotation method with 4 arguments, of class Converter. expected different results
     */
    @Test
    public void testCurrencyQuotation_4args_toFail() {
        String from = "USD";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "01/01/2017";
        Converter instance = new Converter();
        BigDecimal expResult = new BigDecimal(100.50).setScale(2, RoundingMode.CEILING);
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
        assertNotEquals(expResult, result);
    }
    
    /**
     * Test of currencyQuotation method with 4 arguments, of class Converter. expected the same results
     */
    @Test
    public void testCurrencyQuotation_4args_toPass() {
        String from = "USD";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "01/01/2017";
        Converter instance = new Converter();
        BigDecimal expResult = new BigDecimal(95.5).setScale(2, RoundingMode.CEILING);
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
        assertEquals(expResult, result);
    }
    

    
    /**
     * Test of currencyQuotation method with 3 arguments, of class Converter. expected to fail
     */
    @Test
    public void testCurrencyQuotation_3args_toFail() {
        String from = "USD";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "01/01/2017";
        Converter instance = new Converter(quotation);
        BigDecimal expResult = new BigDecimal(100.50).setScale(2, RoundingMode.CEILING);
        BigDecimal result = instance.currencyQuotation(from, to, value);
        assertNotEquals(expResult, result);
    }
    
    /**
     * Test of currencyQuotation method with 3 arguments, of class Converter. expected to pass
     */
    @Test
    public void testCurrencyQuotation_3args_toPass() {
        String from = "USD";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "01/01/2017";
        Converter instance = new Converter(quotation);
        BigDecimal expResult = new BigDecimal(95.5).setScale(2, RoundingMode.CEILING);
        BigDecimal result = instance.currencyQuotation(from, to, value);
        assertEquals(expResult, result);
    }    
    
    
    ///
    ///  TESTING METHOD TO VALUE ZERO ON THE QUOTATION VALUE
    ///

    @Test
    public void testCurrencyQuotation_3args_toZero() {
        String from = "USD";
        String to = "EUR";
        Number value = 0;
        String quotation = "01/01/2017";
        Converter instance = new Converter(quotation);
        BigDecimal expResult = new BigDecimal(0).setScale(2, RoundingMode.CEILING);
        BigDecimal result = instance.currencyQuotation(from, to, value);
        assertEquals(expResult, result);
    }

    @Test
    public void testCurrencyQuotation_4args_toZero() {
        String from = "USD";
        String to = "EUR";
        Number value = 0;
        String quotation = "01/01/2017";
        Converter instance = new Converter();
        BigDecimal expResult = new BigDecimal(0).setScale(2, RoundingMode.CEILING);
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
        assertEquals(expResult, result);
    }
    
    
        ///
    ///  TESTING METHOD TO MAX DOUBLE VALUE ON THE QUOTATION VALUE
    ///

    @Test
    public void testCurrencyQuotation_3args_toMAXINT() {
        String from = "USD";
        String to = "EUR";
        Number value = Integer.MAX_VALUE;
        String quotation = "01/01/2017";
        Converter instance = new Converter(quotation);
        BigDecimal expResult = new BigDecimal(2050902036.56).setScale(2, RoundingMode.CEILING);
        BigDecimal result = instance.currencyQuotation(from, to, value);
        assertEquals(expResult, result);
    }

    @Test
    public void testCurrencyQuotation_4args_toMAXINT() {
        String from = "USD";
        String to = "EUR";
        Number value = Integer.MAX_VALUE;
        String quotation = "01/01/2017";
        Converter instance = new Converter();
        BigDecimal expResult = new BigDecimal(2050902036.56).setScale(2, RoundingMode.CEILING);
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
        assertEquals(expResult, result);
    }
    
    
    
    ///
    ///  TESTING METHOD TO NEGATIVE VALUE ON THE QUOTATION VALUE, RETURNING ERROR
    ///

    @Test
    public void testCurrencyQuotation_3args_toNegative() {
        String from = "USD";
        String to = "EUR";
        Number value = -100.00;
        String quotation = "01/01/2017";
        Converter instance = new Converter(quotation);
        BigDecimal expResult = new BigDecimal(0).setScale(2, RoundingMode.CEILING);
        
        exception.expect(RuntimeException.class);
        BigDecimal result = instance.currencyQuotation(from, to, value);
    }

    @Test
    public void testCurrencyQuotation_4args_toNegative() {
        String from = "USD";
        String to = "EUR";
        Number value = -100.00;
        String quotation = "01/01/2017";
        Converter instance = new Converter();
        BigDecimal expResult = new BigDecimal(0).setScale(2, RoundingMode.CEILING);
        
        exception.expect(RuntimeException.class);
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
    }
    

    ///
    ///  TESTING METHOD TO INEXISTENT FROM QUOTATION, RETURNING ERROR
    ///

    @Test
    public void testCurrencyQuotation_3args_noFROMQuotation() {
        String from = "ASD";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "01/01/2017";
        Converter instance = new Converter(quotation);
        BigDecimal expResult = new BigDecimal(0).setScale(2, RoundingMode.CEILING);
        
        exception.expect(RuntimeException.class);
        BigDecimal result = instance.currencyQuotation(from, to, value);
    }

    @Test
    public void testCurrencyQuotation_4args_noFROMQuotation() {
        String from = "ASD";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "01/01/2017";
        Converter instance = new Converter();
        BigDecimal expResult = new BigDecimal(0).setScale(2, RoundingMode.CEILING);
        
        exception.expect(RuntimeException.class);
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
    }
    
    
    ///
    ///  TESTING METHOD TO INEXISTENT TO QUOTATION, RETURNING ERROR
    ///

    @Test
    public void testCurrencyQuotation_3args_noTOQuotation() {
        String from = "USD";
        String to = "ASD";
        Number value = 100.00;
        String quotation = "01/01/2017";
        Converter instance = new Converter(quotation);
        BigDecimal expResult = new BigDecimal(0).setScale(2, RoundingMode.CEILING);
        
        exception.expect(RuntimeException.class);
        BigDecimal result = instance.currencyQuotation(from, to, value);
    }

    @Test
    public void testCurrencyQuotation_4args_noTOQuotation() {
        String from = "USD";
        String to = "ASD";
        Number value = 100.00;
        String quotation = "01/01/2017";
        Converter instance = new Converter();
        BigDecimal expResult = new BigDecimal(0).setScale(2, RoundingMode.CEILING);
        
        exception.expect(RuntimeException.class);
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
    }
    

    ///
    ///  TESTING METHOD TO INVALID DATE
    ///

    @Test
    public void testCurrencyQuotation_3args_invalidDate() {
        String from = "USD";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "30/12/2017";
        Converter instance = new Converter(quotation);
        BigDecimal expResult = new BigDecimal(0).setScale(2, RoundingMode.CEILING);
        
        exception.expect(RuntimeException.class);
        BigDecimal result = instance.currencyQuotation(from, to, value);
    }

    @Test
    public void testCurrencyQuotation_4args_invalidDate() {
        String from = "USD";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "30/12/2017";
        Converter instance = new Converter();
        BigDecimal expResult = new BigDecimal(0).setScale(2, RoundingMode.CEILING);
        
        exception.expect(RuntimeException.class);
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
    }

     @Test
    public void testCurrencyQuotation_3args_invalidPARSEDDate() {
        String from = "USD";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "30-12-2017";
        Converter instance = new Converter(quotation);
        BigDecimal expResult = new BigDecimal(0).setScale(2, RoundingMode.CEILING);
        
        exception.expect(RuntimeException.class);
        BigDecimal result = instance.currencyQuotation(from, to, value);
    }

    @Test
    public void testCurrencyQuotation_4args_invalidPARSEDDate() {
        String from = "USD";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "30-12-2017";
        Converter instance = new Converter();
        BigDecimal expResult = new BigDecimal(0).setScale(2, RoundingMode.CEILING);
        
        exception.expect(RuntimeException.class);
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
    }
    
}
