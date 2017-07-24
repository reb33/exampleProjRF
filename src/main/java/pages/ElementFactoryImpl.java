package pages;

import annotations.TypeOfBlock;
import annotations.NameOfElement;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.SelenideFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by Konstantin on 23.07.2017.
 */
public class ElementFactoryImpl implements ElementsFactory{

    public SelenideElement get(PageFunctional page, String elementName){
        Field[] fields = page.getClass().getDeclaredFields();
        try {
            return (SelenideElement) Arrays.stream(fields)
                    .filter(field -> field.isAnnotationPresent(NameOfElement.class)
                            && field.getAnnotation(NameOfElement.class).value().equals(elementName)).findFirst().get().get(page);
        }catch (NoSuchElementException e1){
            Selenide.screenshot("No_container");
            throw new NoSuchElementException("ERROR: there is no such container with name " + elementName + " at page " + page.getClass().getName());
        } catch (IllegalAccessException e) {
            Selenide.screenshot("No_element");
            throw new RuntimeException("ERROR: element with name " + elementName + " at page " + page.getClass().getName() + " is not public");
        }
    }

    public AbstractBlock getContainer(PageFunctional page, String elementName){
        Field[] fields = page.getClass().getDeclaredFields();
        Field returnField;
        try {
            returnField = Arrays.stream(fields)
                    .filter(field -> field.isAnnotationPresent(NameOfElement.class)
                            && field.getAnnotation(NameOfElement.class).value().equals(elementName)).findFirst().get();
        }catch (NoSuchElementException e1){
            Selenide.screenshot("No_container");
            throw new NoSuchElementException("ERROR: there is no such container with name " + elementName + " at page " + page.getClass().getName());
        }
        Class<?> listType = returnField.getAnnotation(TypeOfBlock.class).value();

        try {
            return (AbstractBlock) createContainer(listType, (SelenideElement) returnField.get(page));
        } catch (IllegalAccessException e) {
            Selenide.screenshot("No_container");
            throw new RuntimeException(e);
        }
    }

    public <T extends AbstractBlock>List<T> getCollection(PageFunctional page, String elementName){
        Field[] fields = page.getClass().getDeclaredFields();
        Field returnField;
        try {
            returnField = Arrays.asList(fields)
                    .stream()
                    .filter(field -> field.isAnnotationPresent(NameOfElement.class)
                            && field.getAnnotation(NameOfElement.class).value().equals(elementName)).findFirst().get();
        }catch (NoSuchElementException e1){
            Selenide.screenshot("No_container");
            throw new NoSuchElementException("ERROR: there is no such container with name " + elementName + " at page " + page.getClass().getName());
        }

        List<SelenideElement> listElements;
        try {
            listElements= (List<SelenideElement>) returnField.get(page);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        Class<?> listType = returnField.getAnnotation(TypeOfBlock.class).value();

        List<T> result = new ArrayList<>();
        listElements.stream().forEach(element -> result.add((T)createContainer(listType, element)));

        return result;
    }

    private ElementsContainer createContainer (Class<?> listType, SelenideElement self){
        Constructor<?> constructor = null;
        try {
            constructor = listType.getDeclaredConstructor();
            constructor.setAccessible(true);
            ElementsContainer result = (ElementsContainer) constructor.newInstance();
            PageFactory.initElements(new SelenideFieldDecorator(self), result);
            result.setSelf(self);
            return result;
        } catch (NoSuchMethodException|IllegalAccessException|InstantiationException|InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends AbstractBlock>List<List<String>> getList(PageFunctional page, String elementName, String[] compareFields){
        Field field = getField(page, elementName);
//        Class<?> type = getTypeBlock(field);
        List<T> list = getCollection(page, elementName);
        return list.stream().map(blockElement ->
                Arrays.stream(compareFields)
                        .map(fieldName -> blockElement.get(fieldName).getText())
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private Field getField(PageFunctional page, String elementName){
        Field[] fields = page.getClass().getDeclaredFields();
        Field returnField;
        try {
            returnField = Arrays.asList(fields)
                    .stream()
                    .filter(field -> field.isAnnotationPresent(NameOfElement.class)
                            && field.getAnnotation(NameOfElement.class).value().equals(elementName)).findFirst().get();
        }catch (NoSuchElementException e1){
            Selenide.screenshot("No_container");
            throw new NoSuchElementException("ERROR: there is no such container with name " + elementName + " at page " + page.getClass().getName());
        }
        return returnField;
    }

    private Class<?> getTypeBlock(Field field){
        return field.getAnnotation(TypeOfBlock.class).value();
    }


}
