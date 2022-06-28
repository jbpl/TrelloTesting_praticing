package pl.jb.requests.card;

import io.restassured.response.Response;
import pl.jb.requests.BaseRequest;
import pl.jb.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class AddExistingItemToCardRequest {

    public static Response addItemToCardRequest(String cardId, String itemId, String itemName) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .queryParam("value", itemId)
                .when()
                .post(TrelloUrl.getAddExistingItemToCardUrl(cardId, itemName))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
