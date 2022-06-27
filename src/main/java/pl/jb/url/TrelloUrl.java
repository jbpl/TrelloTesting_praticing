package pl.jb.url;

public class TrelloUrl {

    private static final String BASE_URL = "https://api.trello.com/1";
    private static final String BOARDS = "/boards";
    private static final String LISTS = "/lists";
    private static final String CARDS = "/cards";


    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getBoardsUrl() {
        return BOARDS;
    }

    public static String getBoardUrl(String boardId) {
        return getBoardsUrl() + "/" + boardId;
    }

    public static String getListsUrl() {
        return LISTS;
    }

    public static String getCardsUrl() {
        return CARDS;
    }

    public static String getCardUrl(String cardId) {
        return getCardsUrl() + "/" + cardId;
    }
}
