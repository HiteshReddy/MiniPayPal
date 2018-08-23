package com.minipaypal.api;

import com.minipaypal.api.beans.CurrencyType;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterUtils {

    private static Map<CurrencyType, Integer> map = new HashMap<>();

    static {
        map.put(CurrencyType.DOLLAR, 1);
        map.put(CurrencyType.EURO, 2);
        map.put(CurrencyType.POUND, 3);
        map.put(CurrencyType.RUPEE, 4);
    }

    public static long convert(CurrencyType from, CurrencyType to, long amount) {
        int choice = map.get(from);
        int output = map.get(to);

        //If from and to are same, no conversion is required
        if(choice == output) {
            return amount;
        }
        long rate = -1;
        if (choice == 1) {
            if (output == 2) {
                double dollar_euro_rate = 0.87;
                rate = (long)(amount * dollar_euro_rate);
            } else if (output == 3) {
                double dollar_pound_rate = 0.78;
                rate = (long)(amount * dollar_pound_rate);
            } else if (output == 4) {
                double dollar_rupeee_rate = 70.03;
                rate = (long)(amount * dollar_rupeee_rate);
            }
        }
        else if(choice == 2) {
            if (output == 1) {
                double euro_dollar_rate = 1.16;
                rate = (long)(amount * euro_dollar_rate);
            } else if (output == 3) {
                double euro_pound_rate = 0.90;
                rate = (long)(amount * euro_pound_rate);
            } else if (output == 4) {
                double euro_rupee_rate = 81.95;
                rate = (long)(amount * euro_rupee_rate);
            }
        }
        else if(choice == 3) {
            if (output == 1) {
                double pound_dollar_rate = 1.29;
                rate = (long)(amount * pound_dollar_rate);
            } else if (output == 2) {
                double pound_euro_rate = 1.11;
                rate = (long)(amount * pound_euro_rate);
            } else if (output == 4) {
                double pound_rupee_rate = 90.19;
                rate = (long)(amount * pound_rupee_rate);
            }
        } else if(choice == 4) {
            if (output == 1) {
                double rupee_dollar_rate = 0.014;
                rate = (long)(amount * rupee_dollar_rate);
            } else if (output == 2) {
                double rupee_euro_rate = 0.012;
                rate = (long)(amount * rupee_euro_rate);
            } else if (output == 3) {
                double rupee_pound_rate = 0.011;
                rate = (long)(amount * rupee_pound_rate);
            }
        }
        return rate;
    }
}
