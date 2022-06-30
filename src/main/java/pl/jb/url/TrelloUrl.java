package pl.jb.url;

public class TrelloUrl {

    private static final String BASE_URL = "https://api.trello.com/1";
    private static final String BOARDS = "/boards";
    private static final String LISTS = "/lists";
    private static final String CARDS = "/cards";
    private static final String LABELS = "/labels";
    private static final String CHECKLISTS = "/checklists";
    private static final String CHECKITEMS = "/checkitems";


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

    public static String getAddExistingItemToCardUrl(String cardId, String itemName) {
        return getCardUrl(cardId) + "/" + itemName;
    }

    public static String getLabelsUrl() {
        return LABELS;
    }

    public static String getChecklistsUrl() {
        return CHECKLISTS;
    }

    public static String getChecklistUrl(String checklistId) {
        return getChecklistsUrl() + "/" + checklistId;
    }

    public static String getCheckitemsUrl(String checklistId) {
        return getChecklistUrl(checklistId) + CHECKITEMS;
    }
}