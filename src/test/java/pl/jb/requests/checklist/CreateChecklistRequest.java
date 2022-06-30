package pl.jb.requests.checklist;

import io.restassured.response.Response;
import pl.jb.requests.BaseRequest;
import pl.jb.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateChecklistRequest {
    public static Response createChecklistRequest(Map<String, String> queryParams) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .queryParams(queryParams)
                .when()
                .post(TrelloUrl.getChecklistsUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}