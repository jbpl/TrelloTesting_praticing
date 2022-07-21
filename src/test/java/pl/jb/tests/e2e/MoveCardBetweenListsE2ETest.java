package pl.jb.tests.e2e;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pl.jb.requests.board.CreateBoardRequest;
import pl.jb.requests.board.DeleteBoardRequest;
import pl.jb.requests.card.CreateCardRequest;
import pl.jb.requests.card.UpdateCardRequest;
import pl.jb.requests.list.CreateListRequest;

import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MoveCardBetweenListsE2ETest {
    private static final String BOARD_NAME = "Board created with Java";
    private static final String FIRST_LIST_NAME = "First list name created with Java";
    private static final String SECOND_LIST_NAME = "Second list name created with Java";
    private static final String CARD_NAME = "Card name created with Java";
    private static String boardId;
    private static String firstListId;
    private static String secondListId;
    private static String cardId;


    @Test
    @Order(1)
    void createBoardTest() {

        final var response = CreateBoardRequest.createBoardRequest(BOARD_NAME);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(BOARD_NAME);

        boardId = jsonData.getString("id");
    }

    @Test
    @Order(2)
    void createFirstListTest() {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", FIRST_LIST_NAME);
        queryParams.put("idBoard", boardId);

        final var response = CreateListRequest.createListRequest(queryParams);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(FIRST_LIST_NAME);

        firstListId = jsonData.getString("id");
    }

    @Test
    @Order(3)
    void createSecondListTest() {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", SECOND_LIST_NAME);
        queryParams.put("idBoard", boardId);

        final var response = CreateListRequest.createListRequest(queryParams);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(SECOND_LIST_NAME);

        secondListId = jsonData.getString("id");
    }

    @Test
    @Order(4)
    void createCardOnTheFirstListTest() {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", CARD_NAME);
        queryParams.put("idList", firstListId);

        final var response = CreateCardRequest.createCardRequest(queryParams);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(CARD_NAME);
        Assertions.assertThat(jsonData.getString("idList")).isEqualTo(firstListId);

        cardId = jsonData.getString("id");
    }

    @Test
    @Order(5)
    void moveCardBetweenListsTest() {

        final var response = UpdateCardRequest.updateCardRequest(cardId, secondListId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("idList")).isEqualTo(secondListId);

    }

    @Test
    @Order(6)
    void deleteBoardTest() {
        final var response = DeleteBoardRequest.deleteBoardRequest(boardId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
    }
}
