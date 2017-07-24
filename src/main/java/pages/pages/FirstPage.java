package pages.pages;



import annotations.TypeOfBlock;
import annotations.NameOfElement;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;
import pages.CompareValuesPair;
import pages.ComparingCollection;
import pages.blocks.BankCard;
import rest.RestCompare;
import rest.cards.RestCards;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by konstantin on 30.01.2017.
 */
public class FirstPage extends AbstractPage implements ComparingCollection {

    private static final Map<String, CompareValuesPair> comparingMap = new HashMap<>();
    static {
        comparingMap.put("Карты", new CompareValuesPair(new String[]{"Название", "Тип", "Номер карты", "Даты действия", "Описание"}, new RestCards()));
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
    public Map<String, CompareValuesPair> getComparingMap() {
        return comparingMap;
    }
}
