package pages;

import com.codeborne.selenide.SelenideElement;
import rest.RestCompare;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by konstantin on 30.01.2017.
 */
public abstract class AbstractPage implements PageFunctional, ComparingCollection{
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


    @Override
    public List<List<String>> getList(String elementName) {
        getComparingMap().computeIfAbsent(elementName, e->{throw new RuntimeException("item "+elementName+" not found");});
        return elementsFactory.getList(this, elementName, getComparingMap().get(elementName).getCompareFields());
    }

    @Override
    public RestCompare getRestCompare(String elementName) {
        getComparingMap().computeIfAbsent(elementName, e->{throw new RuntimeException("item "+elementName+" not found");});
        return getComparingMap().get(elementName).getRestCompare();
    }

    public Map<String, CompareValuesPair> getComparingMap(){
        throw new RuntimeException("method getComparingMap() must be overridden if collection comparing execute");
    }
}
