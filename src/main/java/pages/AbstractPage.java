package pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

/**
 * Created by konstantin on 30.01.2017.
 */
public abstract class AbstractPage implements PageFunctional{
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
        return elementsFactory.getCollection(this, elementName);
    }

    abstract public void pageIsLoaded();

//    @Override
//    public ElementsContainer createContainer(Class<?> listType, SelenideElement self) {
//        return pageFunctional.createContainer(listType, self);
//    }

}
