package com.minipaypal;

import com.minipaypal.api.CurrencyConverterUtils;
import com.minipaypal.api.beans.CurrencyType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrencyConverterUtilsTest {

    @Test
    public void testDollarConversion() {

        long dollar_euro = CurrencyConverterUtils.convert(CurrencyType.DOLLAR, CurrencyType.EURO, 100);
        assertEquals(87, dollar_euro, 0.1);

        long dollar_pound = CurrencyConverterUtils.convert(CurrencyType.DOLLAR, CurrencyType.POUND , 100);
        assertEquals(78, dollar_pound, 0.1);

        long dollar_rupee = CurrencyConverterUtils.convert(CurrencyType.DOLLAR, CurrencyType.RUPEE, 100);
        assertEquals(7003, dollar_rupee, 0.1);

    }

    @Test
    public void testEuroConversion() {

        long euro_dollar = CurrencyConverterUtils.convert(CurrencyType.EURO, CurrencyType.DOLLAR, 100);
        assertEquals(115, euro_dollar, 0.1);

        long euro_pound = CurrencyConverterUtils.convert(CurrencyType.EURO, CurrencyType.POUND , 100);
        assertEquals(90, euro_pound, 0.1);

        long euro_rupee = CurrencyConverterUtils.convert(CurrencyType.EURO, CurrencyType.RUPEE, 100);
        assertEquals(8195, euro_rupee, 0.1);

    }

    @Test
    public void testPoundConversion() {

        long pound_dollar = CurrencyConverterUtils.convert(CurrencyType.POUND, CurrencyType.DOLLAR, 100);
        assertEquals(129, pound_dollar, 0.1);

        long pound_euro = CurrencyConverterUtils.convert(CurrencyType.POUND, CurrencyType.EURO , 100);
        assertEquals(111, pound_euro, 0.1);

        long pound_rupee = CurrencyConverterUtils.convert(CurrencyType.POUND, CurrencyType.RUPEE, 100);
        assertEquals(9019, pound_rupee, 0.1);

    }

    @Test
    public void testRupeeConversion() {

        long rupee_pound = CurrencyConverterUtils.convert(CurrencyType.RUPEE, CurrencyType.POUND, 100);
        assertEquals(1.10, rupee_pound, 0.15);

        long rupee_euro = CurrencyConverterUtils.convert(CurrencyType.RUPEE, CurrencyType.EURO , 100);
        assertEquals(1.12, rupee_euro, 0.15);

        long rupee_dollar = CurrencyConverterUtils.convert(CurrencyType.RUPEE, CurrencyType.DOLLAR, 100);
        assertEquals(1.14, rupee_dollar, 0.15);

    }



}
