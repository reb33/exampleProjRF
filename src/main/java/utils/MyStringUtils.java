package utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

/**
 * Created by konstantin on 24.07.2017.
 */
public class MyStringUtils {

    public static String splittedNumberCard(String str){
        if (str.length()<16)
            return "";
        return new StringBuilder(str).insert(4," ").insert(9," ").insert(14," ").toString();
    }

    public static String getAmountString(String amount, String amountType){
        String ret = amountType.equals("0")?"−":"+";
//        NumberFormat nf = NumberFormat.getInstance();
//        nf.setMinimumFractionDigits(2);
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbols);
        formatter.setMinimumFractionDigits(2);
        ret+=formatter.format(new BigDecimal(amount)).replace(".",",")+" \u20BD";
        return ret;
    }
}
