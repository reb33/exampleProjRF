package pages;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

/**
 * Created by Konstantin on 23.07.2017.
 */
public abstract class AbstractBlock extends ElementsContainer implements PageFunctional{
    protected ElementsFactory elementsFactory = new ElementFactoryImpl();

    @Override
    public SelenideElement get(String elementName) {
        return elementsFactory.get(this, elementName);
    }

    @Override
    public AbstractBlock getContainer(String elementName) {
        return elementsFactory.getContainer(this, elementName);
    }

    @Override
    public <T extends AbstractBlock> List<T> getCollection(String elementName) {
        return elementsFactory.getCollection(this,elementName);
    }

//    @Override
//    public ElementsContainer createContainer(Class<?> listType, SelenideElement self) {
//        return pageFunctional.createContainer(listType, self);
//    }



}
