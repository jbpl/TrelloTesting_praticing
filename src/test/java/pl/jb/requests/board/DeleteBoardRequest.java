package pl.jb.requests.board;

import io.restassured.response.Response;
import pl.jb.requests.BaseRequest;
import pl.jb.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class DeleteBoardRequest {

    public static Response deleteBoardRequest(String boardId) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .when()
                .delete(TrelloUrl.getBoardUrl(boardId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
