package pages.pages;



import annotations.TypeOfBlock;
import annotations.NameOfElement;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.Selenide;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import pages.AbstractBlock;
import pages.AbstractPage;
import pages.BlockCompare;
import pages.PageCompare;
import pages.blocks.BankCard;
import rest.RestCompare;
import rest.cards.RestCards;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Created by konstantin on 30.01.2017.
 */
public class FirstPage extends AbstractPage implements PageCompare{

    @Getter
    enum ListComparing{
        CARDS("Карты", new String[]{"Название", "Тип", "Номер карты", "Даты действия", "Описание"}, new RestCards());

        String name;
        String[] fields;
        RestCompare compare;
        ListComparing(String name, String[] fields, RestCompare compare) {
            this.name=name;
            this.fields=fields;
            this.compare=compare;
        }
    }



//    @NameOfElement(value = "список популярных уток")
//    List<SelenideElement> popularDuckList = $$("#box-most-popular ul li");

//    @NameOfElement(value = "список популярных уток")
//    @FindBy(css = "#box-most-popular ul li")
//    List<SelenideElement> popularDuckList;
//
//    @NameOfElement(value = "фронтовая картинка")
//    @FindBy(id="rslides1_s0")
//    SelenideElement frontPic;

//    @NameOfElement(value = "популярная утка")
//    @FindBy(css="#box-most-popular ul li")
//    Duck popularDuck;

    private final String cardCss = "default-card-item-widget > div";

    @NameOfElement(value = "Карты")
    @FindBy(css=cardCss)
    @TypeOfBlock(BankCard.class)
    public ElementsCollection cards;

    @Override
    public void pageIsLoaded() {
        Selenide.$(cardCss).waitUntil(Condition.exist,60_000);
    }

    @Override
    public List<List<String>> getList(String elementName) {
        String[] fields = Arrays.stream(ListComparing.values())
                .filter(el -> el.getName().equals(elementName))
                .findFirst()
                .get()
                .getFields();
        return elementsFactory.getList(this, elementName, fields);
    }

    @Override
    public RestCompare getRestCompare(String elementName) {
        return Arrays.stream(ListComparing.values())
                .filter(el -> el.getName().equals(elementName))
                .findFirst()
                .get()
                .getCompare();
    }
}
