package utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;
import java.util.Spliterators;

/**
 * Created by konstantin on 24.07.2017.
 */
public class MyDateUtils {
    public static String retDateInPageFormat(String date){
        //LocalDate.parse(date, DateTimeFormatter.ofPattern("MM.yyyy")).format(DateTimeFormatter.ofPattern("MM/yy"));
        return YearMonth.parse(date, DateTimeFormatter.ofPattern("MM.yyyy")).format(DateTimeFormatter.ofPattern("MM/yy"));
    }

    public static String retDateInPageFormat2(String date){
        DateTimeFormatter d = new DateTimeFormatterBuilder()
//                .appendLiteral("z dnia ")
                .appendValue(ChronoField.DAY_OF_MONTH)
                .appendLiteral(" ")
                .appendText(ChronoField.MONTH_OF_YEAR, MONTH_OF_YEARS)
//                .appendLiteral(" ")
//                .appendValue(ChronoField.YEAR_OF_ERA)
                .toFormatter();
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .format(d);
    }

    private static final Map<Long, String> MONTH_OF_YEARS = new HashMap<>();
    static {
        MONTH_OF_YEARS.put(1l, "Янв");
        MONTH_OF_YEARS.put(2l, "Фев");
        MONTH_OF_YEARS.put(3l, "Мар");
        MONTH_OF_YEARS.put(4l, "Апр");
        MONTH_OF_YEARS.put(5l, "Мая");
        MONTH_OF_YEARS.put(6l, "Июн");
        MONTH_OF_YEARS.put(7l, "Июл");
        MONTH_OF_YEARS.put(8l, "Авг");
        MONTH_OF_YEARS.put(9l, "Сен");
        MONTH_OF_YEARS.put(10l, "Окт");
        MONTH_OF_YEARS.put(11l, "Ноя");
        MONTH_OF_YEARS.put(12l, "Дек");
    }
}
