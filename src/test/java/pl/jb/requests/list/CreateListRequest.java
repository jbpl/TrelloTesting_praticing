package pl.jb.requests.list;

import io.restassured.response.Response;
import pl.jb.requests.BaseRequest;
import pl.jb.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateListRequest {

    public static Response createListRequest(Map<String, String> queryParams) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .queryParams(queryParams)
                .when()
                .post(TrelloUrl.getListsUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
