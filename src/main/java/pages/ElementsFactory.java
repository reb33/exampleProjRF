package pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

/**
 * Created by konstantin on 24.07.2017.
 */
public interface ElementsFactory {

    SelenideElement get(PageFunctional page, String elementName);
    AbstractBlock getContainer(PageFunctional page, String elementName);
    <T extends AbstractBlock>List<T> getCollection(PageFunctional page, String elementName);
    <T extends AbstractBlock>List<List<String>> getList(PageFunctional page, String elementName, String[] compareFields);
}
