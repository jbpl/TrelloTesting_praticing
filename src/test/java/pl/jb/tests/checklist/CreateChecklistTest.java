package pl.jb.tests.checklist;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pl.jb.requests.board.CreateBoardRequest;
import pl.jb.requests.board.DeleteBoardRequest;
import pl.jb.requests.card.CreateCardRequest;
import pl.jb.requests.checklist.CreateChecklistRequest;
import pl.jb.requests.list.CreateListRequest;

import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CreateChecklistTest {

    private static final String boardName = "Board created with Java";
    private static final String listName = "List name created with Java";
    private static final String cardName = "Card name created with Java";
    private static final String checklistName = "Checklist name created with Java";
    private static String boardId;
    private static String listId;
    private static String cardId;

    @Test
    @Order(1)
    void createBoardTest() {

        final var response = CreateBoardRequest.createBoardRequest(boardName);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(boardName);

        boardId = jsonData.getString("id");
    }

    @Test
    @Order(2)
    void createListTest() {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", listName);
        queryParams.put("idBoard", boardId);

        final var response = CreateListRequest.createListRequest(queryParams);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(listName);

        listId = jsonData.getString("id");
    }

    @Test
    @Order(3)
    void createCardTest() {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", cardName);
        queryParams.put("idList", listId);

        final var response = CreateCardRequest.createCardRequest(queryParams);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(cardName);
        Assertions.assertThat(jsonData.getString("idList")).isEqualTo(listId);

        cardId = jsonData.getString("id");
    }

    @Test
    @Order(4)
    void createChecklistTest() {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("idCard", cardId);
        queryParams.put("name", checklistName);
        queryParams.put("pos", "top");

        final var response = CreateChecklistRequest.createChecklistRequest(queryParams);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(checklistName);
        Assertions.assertThat(jsonData.getString("idBoard")).isEqualTo(boardId);
        Assertions.assertThat(jsonData.getString("idCard")).isEqualTo(cardId);
    }

    @Test
    @Order(5)
    void deleteBoardTest() {
        final var response = DeleteBoardRequest.deleteBoardRequest(boardId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
    }
}
