package pl.jb.requests.card;

import io.restassured.response.Response;
import pl.jb.requests.BaseRequest;
import pl.jb.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class UpdateCardRequest {

    public static Response updateCardRequest(String cardId, String listId) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .queryParam("idList", listId)
                .when()
                .put(TrelloUrl.getCardUrl(cardId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
