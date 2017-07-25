package steps;

import com.codeborne.selenide.ElementsContainer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import pages.AbstractBlock;
import pages.ComparingCollection;
import pages.PageFunctional;
import pages.blocks.BankCard;
import pages.pages.FirstPage;
import backData.BackDataCompare;
import utilsTests.MyComparingUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by konstantin on 30.01.2017.
 */
public class MySteps {

    FirstPage firstPage = page(new FirstPage());

    PageFunctional currentPage;

    @Given("приложение запущено")
    public void start(){
        open("https://online.raiffeisen.ru/demo/#/cards");
        firstPage.pageIsLoaded();
        currentPage = firstPage;

    }

    @Then("данные контейнера (.+)")
    public void getContainer(String contName){
        ElementsContainer container = firstPage.getContainer(contName);
        System.out.println();
    }


    private void compareCollection(String collectionName, String[] params){
        List<List<String>> listOfPage = ((ComparingCollection)currentPage).getList(collectionName);
        BackDataCompare backData = ((ComparingCollection)currentPage).getBackDataCompare(collectionName);
        params = ArrayUtils.isEmpty(params)?((ComparingCollection)currentPage).getParams(collectionName):params;
        List<List<String>> listOfBackData = backData.listValues(params);

        try {
            Assert.assertEquals(listOfBackData, listOfPage);
        }catch (AssertionError e){
            MyComparingUtils.comparingLists(listOfBackData, listOfPage,((ComparingCollection)currentPage).getFields(collectionName));
        }
    }

    @Then("данные коллекции <(.+?)> соответствуют запросу")
    public void compareCollection_withGeneratedParams(String collectionName){
        Function<PageFunctional, String>[] functions = ((ComparingCollection)currentPage).getFunctions(collectionName);
        if (ArrayUtils.isEmpty(functions))
            compareCollection(collectionName, ArrayUtils.EMPTY_STRING_ARRAY);
        else {
            String[] params = (String[]) Arrays.stream(functions)
                    .map(f -> f.apply(currentPage))
                    .collect(Collectors.toList())
                    .toArray();
            String[] sumParams = ArrayUtils.addAll(((ComparingCollection)currentPage).getParams(collectionName), params);
            compareCollection(collectionName, sumParams);
        }
    }

    @Then("данные подколлекции <(.+?)> в каждом элементе колекции <(.+?)> соответствуют запросу")
    public void compareSubCollection_withGeneratedParams(String subCollectionName, String parentCollectionName){
        PageFunctional previousPage = currentPage;
        List<AbstractBlock> listOfSubCollection = currentPage.getCollection(parentCollectionName);
        listOfSubCollection.stream()
                .forEach(bankCard -> {
                    currentPage = bankCard;
                    Function<PageFunctional, String>[] functions = ((ComparingCollection)currentPage).getFunctions(subCollectionName);
                    if (ArrayUtils.isEmpty(functions))
                        compareCollection(subCollectionName, ArrayUtils.EMPTY_STRING_ARRAY);
                    else {
                        String[] params = Arrays.stream(functions)
                                .map(f -> f.apply(currentPage))
                                .collect(Collectors.toList())
                                .toArray(new String[0]);
                        String[] sumParams = ArrayUtils.addAll(((ComparingCollection)currentPage).getParams(subCollectionName), params);
                        compareCollection(subCollectionName, sumParams);
                    }
                });
        currentPage = previousPage;
    }

    @Then("найти элемент (\\w+)")
    public void getElement(String elName){
        firstPage.get(elName);
    }
}
