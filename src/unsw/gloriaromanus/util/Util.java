/**
 * Utility class with static helper functions
 */

package unsw.gloriaromanus.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public final class Util {
    public static void setOnce(Object a, Object b) {
        a = a == null ? b : a;
    }

    public static JSONObject parseJsonFile(String fileName) {
        try {
            return new JSONObject(Files.readString(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
