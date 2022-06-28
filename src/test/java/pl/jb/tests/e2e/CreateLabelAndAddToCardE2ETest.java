package pl.jb.tests.e2e;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pl.jb.requests.board.CreateBoardRequest;
import pl.jb.requests.board.DeleteBoardRequest;
import pl.jb.requests.card.AddExistingItemToCardRequest;
import pl.jb.requests.card.CreateCardRequest;
import pl.jb.requests.label.CreateLabelRequest;
import pl.jb.requests.list.CreateListRequest;

import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CreateLabelAndAddToCardE2ETest {

    private static final String boardName = "Board created with Java";
    private static final String listName = "List name created with Java";
    private static final String cardName = "Card name created with Java";
    private static final String labelName = "Label created with Java";
    private static final String labelColor = "pink";
    private static String boardId;
    private static String listId;
    private static String labelId;
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
    void createLabelOnABoardTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", labelName);
        queryParams.put("color", labelColor);
        queryParams.put("idBoard", boardId);

        final var response = CreateLabelRequest.createLabelRequest(queryParams);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(labelName);
        Assertions.assertThat(jsonData.getString("color")).isEqualTo(labelColor);
        Assertions.assertThat(jsonData.getString("idBoard")).isEqualTo(boardId);

        labelId = jsonData.getString("id");
    }

    @Test
    @Order(5)
    void addLabelToCardTest() {

        String itemName = "idLabels";

        final var response = AddExistingItemToCardRequest.addItemToCardRequest(cardId, labelId, itemName);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        Assertions.assertThat(response.asString()).contains(labelId);
    }

    @Test
    @Order(6)
    void deleteBoardTest() {
        final var response = DeleteBoardRequest.deleteBoardRequest(boardId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
    }
}
