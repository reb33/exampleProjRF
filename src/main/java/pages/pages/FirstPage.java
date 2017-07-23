package pages.pages;



import annotations.NameOfBlock;
import annotations.NameOfElement;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;

import java.util.List;


/**
 * Created by konstantin on 30.01.2017.
 */
public class FirstPage extends AbstractPage {

//    @NameOfElement(value = "список популярных уток")
//    List<SelenideElement> popularDuckList = $$("#box-most-popular ul li");

    @NameOfElement(value = "список популярных уток")
    @FindBy(css = "#box-most-popular ul li")
    List<SelenideElement> popularDuckList;

    @NameOfElement(value = "фронтовая картинка")
    @FindBy(id="rslides1_s0")
    SelenideElement frontPic;

//    @NameOfElement(value = "популярная утка")
//    @FindBy(css="#box-most-popular ul li")
//    Duck popularDuck;


    @NameOfElement(value = "популярные утки")
    @FindBy(css="#box-most-popular ul li")
//    @NameOfBlock(Duck.class)
    ElementsCollection popularDucks;


}
