package pl.jb.properties;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ResourceBundle;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrelloProperties {

    private static final String TOKEN = "trello.token";
    private static final String KEY = "trello.key";

    public static String getToken() {
        if (getProperty(TOKEN).isEmpty() || getProperty(TOKEN).startsWith("YOUR")) {
            return System.getProperty("TOKEN");
        } else {
            return getProperty(TOKEN);
        }
    }

    public static String getKey() {
        if (getProperty(KEY).isEmpty() || getProperty(KEY).startsWith("YOUR")) {
            return System.getProperty("KEY");
        } else {
            return getProperty(KEY);
        }
    }

    public static String getProperty(String key) {
        return ResourceBundle.getBundle("trello").getString(key);
    }
}
