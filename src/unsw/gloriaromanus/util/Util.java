/**
 * Utility class with static helper functions
 */

package unsw.gloriaromanus.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;

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

    public static ArrayList<String> convert(JSONArray jArr) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            for (int i = 0, l = jArr.length(); i < l; i++) {
                list.add(jArr.getString(i));
            }
        } catch (JSONException e) {
        }

        return list;
    }

    public static JSONArray convert(Collection<Object> list) {
        return new JSONArray(list);
    }
}
