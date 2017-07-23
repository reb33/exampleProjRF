package pages;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

/**
 * Created by Konstantin on 23.07.2017.
 */
public interface PageFunctional {

    SelenideElement get(String elementName);
    AbstractBlock getContainer(String elementName);
    <T extends AbstractBlock>List<T> getCollection(String elementName);
//    ElementsContainer createContainer (Class<?> listType, SelenideElement self);

}
