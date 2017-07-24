package steps;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.AbstractBlock;
import pages.PageCompare;
import pages.pages.FirstPage;
import rest.RestCompare;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by konstantin on 30.01.2017.
 */
public class MySteps {

    FirstPage firstPage = page(new FirstPage());

    @Given("приложение запущено")
    public void start(){
        open("https://online.raiffeisen.ru/demo/#/cards");
        firstPage.pageIsLoaded();

    }

    @Then("данные контейнера (.+)")
    public void getContainer(String contName){
        ElementsContainer container = firstPage.getContainer(contName);
        System.out.println();
    }

    @Then("данные коллекции (.+) соответствуют Rest запросу")
    public void getCollection(String collectionName){
        List<List<String>> listOfPage = ((PageCompare)firstPage).getList(collectionName);
        RestCompare compare = ((PageCompare)firstPage).getRestCompare(collectionName);
        List<List<String>> listOfRest = compare.listValues();
        System.out.println();
    }

    @Then("найти элемент (\\w+)")
    public void getElement(String elName){
        firstPage.get(elName);
    }
}
