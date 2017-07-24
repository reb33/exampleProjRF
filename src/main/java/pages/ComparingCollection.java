package pages;

import rest.RestCompare;

import java.util.List;

/**
 * Created by konstantin on 24.07.2017.
 */
public interface ComparingCollection {
    List<List<String>> getList(String elementName);
    RestCompare getRestCompare(String elementName);
}
