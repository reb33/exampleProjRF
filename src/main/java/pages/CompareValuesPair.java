package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import rest.RestCompare;

/**
 * Created by Konstantin on 24.07.2017.
 */
@Getter
@AllArgsConstructor
public class CompareValuesPair {
    String[] compareFields;
    RestCompare restCompare;
}
