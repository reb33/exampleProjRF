package pages;

import backData.BackDataCompare;

import java.util.List;
import java.util.function.Function;

/**
 * Created by konstantin on 24.07.2017.
 */
public interface ComparingCollection {
    List<List<String>> getList(String elementName);
    BackDataCompare getBackDataCompare(String elementName);
    String[] getFields(String elementName);
    String[] getParams(String elementName);
    void setParams(String elementName, String[] params);
    Function<PageFunctional, String>[] getFunctions(String elementName);
}
