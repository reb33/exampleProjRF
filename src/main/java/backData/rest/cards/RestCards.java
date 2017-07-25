package backData.rest.cards;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.apache.commons.io.FileUtils;
import org.mockito.Mockito;
import backData.BackDataCompare;
import utils.MyDateUtils;
import utils.MyStringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;

/**
 * Created by konstantin on 24.07.2017.
 */
public class RestCards implements BackDataCompare {
    private RestCards restCards;
    public RestCards() {
        restCards = Mockito.mock(RestCards.class);
        String json = "";
        try {
            json = FileUtils.readFileToString(FileUtils.getFile("./cardsJson.json"), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Mockito.when(restCards.getRest()).thenReturn(json);
    }


    String getRest() {
        return when().
        get("/rides").
        then().
        contentType(ContentType.JSON).  // check that the content type return from the API is JSON
        extract().response().toString();
    }

    @Override
    public List<List<String>> listValues(String... args) {
        String response = restCards.getRest();Cards cards = new Gson().fromJson(response, Cards.class);

        return cards.getCards().stream()
                .map(card -> new ArrayList<>(Arrays.asList(
                        card.getName(),
                        card.getType(),
                        MyStringUtils.splittedNumberCard(card.getNumber()),
                        String.format("открыта %s до %s",
                                MyDateUtils.retDateInPageFormat(card.getStart()),
                                MyDateUtils.retDateInPageFormat(card.getEnd())),
                        card.getDescription()))
                ).collect(Collectors.toList());
    }
}
