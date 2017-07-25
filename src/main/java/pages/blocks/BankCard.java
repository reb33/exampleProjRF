package pages.blocks;

import annotations.NameOfElement;
import annotations.TypeOfBlock;
import backData.bd.transactions.BDTransactions;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractBlock;
import pages.CompareValuesPair;
import backData.rest.cards.RestCards;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by konstantin on 24.07.2017.
 */
public class BankCard extends AbstractBlock{

    private static final Map<String, CompareValuesPair> comparingMap = new HashMap<>();
    static {
        comparingMap.put("Транзакции", new CompareValuesPair(new String[]{"Дата","Описание","Сумма"}, new BDTransactions(),
                t -> t.get("Номер карты").getText()));
    }

    @NameOfElement(value = "Название")
    @FindBy(css = "div.product-header-title__name span")
    public SelenideElement name;

    @NameOfElement(value = "Тип")
    @FindBy(css = "div.product-view-info div.product-view-info__header")
    public SelenideElement type;

    @NameOfElement(value = "Номер карты")
    @FindBy(css = "div div.product-view-info div.product-view-info__item:nth-child(2)")
    public SelenideElement maskedNumber;

    @NameOfElement(value = "Даты действия")
    @FindBy(css = "div div.product-view-info div.product-view-info__item:nth-child(3)")
    public SelenideElement cardDates;

    @NameOfElement(value = "Описание")
    @FindBy(css = "div div.product-view-info div.product-view-info__item:nth-child(4)")
    public SelenideElement cardDescription;

    @NameOfElement(value = "Транзакции")
    @TypeOfBlock(Transaction.class)
    @FindBy(css = "div.widget-product__detail_tab div.product-operations__operation-row")
    public ElementsCollection transactions;


    @Override
    public Map<String, CompareValuesPair> getComparingMap() {
        return comparingMap;
    }
}
