package pl.jb.requests.label;

import io.restassured.response.Response;
import pl.jb.requests.BaseRequest;
import pl.jb.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateLabelRequest {

    public static Response createLabelRequest(Map<String, String> queryParams) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .queryParams(queryParams)
                .when()
                .post(TrelloUrl.getLabelsUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }

}
