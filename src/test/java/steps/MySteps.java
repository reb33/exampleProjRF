package steps;

import com.codeborne.selenide.ElementsContainer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.pages.FirstPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by konstantin on 30.01.2017.
 */
public class MySteps {

    FirstPage firstPage = page(new FirstPage());

    @Given("приложение запущено")
    public void start(){
        open("https://demo.litecart.net/en/");

    }

    @Then("данные контейнера (\\w+)")
    public void getContainer(String contName){
        ElementsContainer container = firstPage.getContainer(contName);
        System.out.println();
    }

    @Then("данные коллекции")
    public void getCollection(){
//        List<SelenideElement> ducks = firstPage.getList("список популярных уток");
//        ducks.get(0).get("название");
//        List<Duck> ducks = firstPage.getCollection("популярные утки");
        System.out.println();
    }

    @Then("найти элемент (\\w+)")
    public void getElement(String elName){
        firstPage.get(elName);
    }
}
