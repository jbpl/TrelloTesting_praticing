package pl.jb.tests.board;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.jb.requests.board.CreateBoardRequest;
import pl.jb.requests.board.DeleteBoardRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class CreateBoardTest {

    private String boardId;

    @DisplayName("Create a board with valid data")
    @ParameterizedTest(name = "Board name: {0}")
    @MethodSource("boardNamesSamples")
    void createBoardTest(String boardName) {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        final var createBoardResponse = CreateBoardRequest.createBoardRequest(queryParams);
        Assertions.assertThat(createBoardResponse.statusCode()).isEqualTo(200);

        JsonPath json = createBoardResponse.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);

        boardId = json.getString("id");

        final var deleteBoardResponse = DeleteBoardRequest.deleteBoardRequest(boardId);
        Assertions.assertThat(deleteBoardResponse.statusCode()).isEqualTo(200);
    }

    private static Stream<Arguments> boardNamesSamples() {

        return Stream.of(
                Arguments.of("Board name created with Java"),
                Arguments.of("#"),
                Arguments.of(" ")
        );
    }
}
