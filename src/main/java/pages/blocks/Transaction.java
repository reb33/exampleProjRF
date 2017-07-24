package pages.blocks;

import annotations.NameOfElement;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractBlock;

/**
 * Created by konstantin on 24.07.2017.
 */
public class Transaction extends AbstractBlock {

    @NameOfElement(value = "Дата")
    @FindBy(css = "div.product-operation-value_date")
    public SelenideElement date;

    @NameOfElement(value = "Описание")
    @FindBy(css = "div.product-operation-value_description")
    public SelenideElement description;

    @NameOfElement(value = "Сумма")
    @FindBy(css = "div.product-operation-value_amount")
    public SelenideElement amount;

//    @Override
//    public String[] compareFields() {
//        return new String[]{"Дата","Описание","Сумма"};
//    }
}
