package pages.blocks;

import annotations.NameOfElement;
import annotations.TypeOfBlock;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractBlock;

/**
 * Created by konstantin on 24.07.2017.
 */
public class BankCard extends AbstractBlock{
//    String[] compareFields = new String[]{"Название", "Тип", "Номер карты", "Даты действия", "Описание"};

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


//    @Override
//    public RestCompare getRestCompare() {
//        return new RestCards();
//    }
//
//    @Override
//    public List<List<String>> getList(String elementName) {
//        return elementsFactory.getList(this, elementName, compareFields);
//    }


}
