package pl.jb.requests.card;

import io.restassured.response.Response;
import pl.jb.requests.BaseRequest;
import pl.jb.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateCardRequest {

    public static Response createCardRequest(Map<String, String> queryParams) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .queryParams(queryParams)
                .when()
                .post(TrelloUrl.getCardsUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
