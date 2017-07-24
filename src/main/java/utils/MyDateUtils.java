package utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Spliterators;

/**
 * Created by konstantin on 24.07.2017.
 */
public class MyDateUtils {
    public static String retDateInPageFormat(String date){
        //LocalDate.parse(date, DateTimeFormatter.ofPattern("MM.yyyy")).format(DateTimeFormatter.ofPattern("MM/yy"));
        return YearMonth.parse(date, DateTimeFormatter.ofPattern("MM.yyyy")).format(DateTimeFormatter.ofPattern("MM/yy"));
    }
}
