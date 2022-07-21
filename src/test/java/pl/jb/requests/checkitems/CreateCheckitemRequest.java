package pl.jb.requests.checkitems;

import io.restassured.response.Response;
import pl.jb.requests.BaseRequest;
import pl.jb.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateCheckitemRequest {
    public static Response createCheckitemRequest(String checklistId, Map<String, String> queryParams) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .queryParams(queryParams)
                .when()
                .post(TrelloUrl.getCheckitemsUrl(checklistId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
