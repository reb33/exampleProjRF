package pages;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

/**
 * Created by konstantin on 30.01.2017.
 */
public abstract class AbstractPage implements PageFunctional{
    PageFunctional pageFunctional = new PageFunctionalImpl();
    @Override
    public SelenideElement get(String elementName) {
        return pageFunctional.get(elementName);
    }

    @Override
    public AbstractBlock getContainer(String elementName) {
        return pageFunctional.getContainer(elementName);
    }

    @Override
    public <T extends AbstractBlock> List<T> getCollection(String elementName) {
        return pageFunctional.getCollection(elementName);
    }

//    @Override
//    public ElementsContainer createContainer(Class<?> listType, SelenideElement self) {
//        return pageFunctional.createContainer(listType, self);
//    }
}
