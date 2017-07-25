package utilsTests;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by konstantin on 25.07.2017.
 */
public class MyComparingUtils {

    public static void comparingLists(List<List<String>> expected, List<List<String>> actual, String[] fields){

        assertEquals("Несоответствует количество элементов", expected.size(), actual.size());
        int firstWrong = IntStream.range(0, expected.size())
                .filter(i-> !expected.get(i).equals(actual.get(i)))
                .findFirst()
                .getAsInt();
        Map <String, String> expectedMap = IntStream.range(0, fields.length)
                .boxed()
                .collect(Collectors.toMap(
                        i -> fields[i],
                        i -> expected.get(firstWrong).get(i)
                ));
        Map <String, String> actualMap = IntStream.range(0, fields.length)
                .boxed()
                .collect(Collectors.toMap(
                        i -> fields[i],
                        i -> actual.get(firstWrong).get(i)
                ));
        assertEquals("Несоответствует строка под номером "+(firstWrong+1), expectedMap, actualMap);
    }
}
