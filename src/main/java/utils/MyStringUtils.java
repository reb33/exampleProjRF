package utils;

/**
 * Created by konstantin on 24.07.2017.
 */
public class MyStringUtils {

    public static String splittedNumberCard(String str){
        if (str.length()<16)
            return "";
        return new StringBuilder(str).insert(4," ").insert(9," ").insert(14," ").toString();
    }
}
