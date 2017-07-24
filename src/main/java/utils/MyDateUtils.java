package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Spliterators;

/**
 * Created by konstantin on 24.07.2017.
 */
public class MyDateUtils {
    public static String retDateInPageFormat(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("MM.YYYY")).format(DateTimeFormatter.ofPattern("MM/YY"));
    }
}
