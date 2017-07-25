package pages;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import backData.BackDataCompare;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Konstantin on 23.07.2017.
 */
public abstract class AbstractBlock extends ElementsContainer implements PageFunctional, ComparingCollection{
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

    @Override
    public List<List<String>> getList(String elementName) {
        getComparingMap().computeIfAbsent(elementName, e->{throw new RuntimeException("item "+elementName+" not found");});
        return elementsFactory.getList(this, elementName, getComparingMap().get(elementName).getCompareFields());
    }

    @Override
    public BackDataCompare getBackDataCompare(String elementName) {
        getComparingMap().computeIfAbsent(elementName, e->{throw new RuntimeException("item "+elementName+" not found");});
        return getComparingMap().get(elementName).getBackDataCompare();
    }

    @Override
    public String[] getFields(String elementName) {
        getComparingMap().computeIfAbsent(elementName, e->{throw new RuntimeException("item "+elementName+" not found");});
        return getComparingMap().get(elementName).getCompareFields();
    }

    @Override
    public String[] getParams(String elementName) {
        getComparingMap().computeIfAbsent(elementName, e->{throw new RuntimeException("item "+elementName+" not found");});
        return getComparingMap().get(elementName).getParams();
    }

    @Override
    public Function<PageFunctional, String>[] getFunctions(String elementName) {
        getComparingMap().computeIfAbsent(elementName, e->{throw new RuntimeException("item "+elementName+" not found");});
        return getComparingMap().get(elementName).getFunctions();
    }

    @Override
    public void setParams(String elementName, String[] params) {
        getComparingMap().get(elementName).setParam(params);
    }

    public Map<String, CompareValuesPair> getComparingMap(){
        throw new RuntimeException("method getComparingMap() must be overridden if collection comparing execute");
    }

}
