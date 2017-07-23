package pages;

import annotations.NameOfBlock;
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

/**
 * Created by Konstantin on 23.07.2017.
 */
public class PageFunctionalImpl implements PageFunctional{

    public SelenideElement get(String elementName){
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            return (SelenideElement) Arrays.stream(fields)
                    .filter(field -> field.isAnnotationPresent(NameOfElement.class)
                            && field.getAnnotation(NameOfElement.class).value().equals(elementName)).findFirst().get().get(this);
        } catch (IllegalAccessException e) {
            Selenide.screenshot("No_element");
            throw new RuntimeException("ERROR: element with name " + elementName + " at page " + this.getClass().getName() + " is not public");
        }catch (NoSuchElementException e1){
            Selenide.screenshot("No_element");
            throw new NoSuchElementException("ERROR: there is no such element with name " + elementName + " at page " + this.getClass().getName());
        }
    }

    public AbstractBlock getContainer(String elementName){
        Field[] fields = this.getClass().getDeclaredFields();
        Field returnField;
        try {
            returnField = Arrays.stream(fields)
                    .filter(field -> field.isAnnotationPresent(NameOfElement.class)
                            && field.getAnnotation(NameOfElement.class).value().equals(elementName)).findFirst().get();
        }catch (NoSuchElementException e1){
            Selenide.screenshot("No_container");
            throw new NoSuchElementException("ERROR: there is no such container with name " + elementName + " at page " + this.getClass().getName());
        }
        Class<?> listType = returnField.getAnnotation(NameOfBlock.class).value();

        try {
            return (AbstractBlock) createContainer(listType, (SelenideElement) returnField.get(this));
        } catch (IllegalAccessException e) {
            Selenide.screenshot("No_container");
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public <T extends AbstractBlock>List<T> getCollection(String elementName){
        Field[] fields = this.getClass().getDeclaredFields();
        Field returnField;
        try {
            returnField = Arrays.asList(fields)
                    .stream()
                    .filter(field -> field.isAnnotationPresent(NameOfElement.class)
                            && field.getAnnotation(NameOfElement.class).value().equals(elementName)).findFirst().get();
        }catch (NoSuchElementException e1){
            Selenide.screenshot("No_container");
            throw new NoSuchElementException("ERROR: there is no such container with name " + elementName + " at page " + this.getClass().getName());
        }

        List<SelenideElement> listElements;
        try {
            listElements= (List<SelenideElement>) returnField.get(this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        Class<?> listType = returnField.getAnnotation(NameOfBlock.class).value();

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

    //    public List<SelenideElement> getList(String elementName){
//        Field[] fields = this.getClass().getDeclaredFields();
//        try {
//            return (List<SelenideElement>) Arrays.asList(fields)
//                    .stream()
//                    .filter(field -> field.isAnnotationPresent(NameOfElement.class)
//                            && field.getAnnotation(NameOfElement.class).value().equals(elementName)).findFirst().get().get(this);
//        } catch (IllegalAccessException e) {
//            Selenide.screenshot("No_container");
//            throw new RuntimeException("ERROR: container with name " + elementName + " at page " + this.getClass().getName() + " is not public");
//        }catch (NoSuchElementException e1){
//            Selenide.screenshot("No_container");
//            throw new NoSuchElementException("ERROR: there is no such container with name " + elementName + " at page " + this.getClass().getName());
//        }
//    }
}
