package pages.pages;



import annotations.TypeOfBlock;
import annotations.NameOfElement;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;
import pages.CompareValuesPair;
import pages.ComparingCollection;
import pages.blocks.BankCard;
import backData.rest.cards.RestCards;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by konstantin on 30.01.2017.
 */
public class FirstPage extends AbstractPage implements ComparingCollection {

    private static final Map<String, CompareValuesPair> comparingMap = new HashMap<>();
    static {
        comparingMap.put("Карты", new CompareValuesPair(new String[]{"Название", "Тип", "Номер карты", "Даты действия", "Описание"}, new RestCards()));
    }

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
