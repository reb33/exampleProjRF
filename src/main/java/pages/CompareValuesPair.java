package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import backData.BackDataCompare;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Created by Konstantin on 24.07.2017.
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CompareValuesPair {
    @NonNull
    String[] compareFields;
    @NonNull
    BackDataCompare backDataCompare;
    String[] params = ArrayUtils.EMPTY_STRING_ARRAY;
    Function<PageFunctional,String>[] functions;

    public void setParam(String[] params) {
        this.params = params;
    }

    public CompareValuesPair(String[] compareFields, BackDataCompare backDataCompare,
                             Function<PageFunctional, String>... functions) {
        this.compareFields = compareFields;
        this.backDataCompare = backDataCompare;
        this.functions = functions;
    }
}
