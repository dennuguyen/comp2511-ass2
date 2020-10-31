package unsw.gloriaromanus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import unsw.gloriaromanus.util.Util;

public class World {

    private Map<String, Map<String, Boolean>> matrix = new HashMap<String, Map<String, Boolean>>();
    private Map<String, Province> provinces = new HashMap<String, Province>();

    private World(String JSONFile) {
        this.createWorld(JSONFile);
        this.createProvinces(this.matrix.keySet());
    }

    private static class BillPughWorld {
        private static World worldSingleton = null;
    }

    public static void init(String JSONFile) {
        BillPughWorld.worldSingleton = new World(JSONFile);
    }

    public static World getInstance() {
        return BillPughWorld.worldSingleton;
    }

    private void createWorld(String JSONFile) {
        System.out.println("Creating world...");
        JSONObject jsonObject = Util.parseJsonFile(JSONFile);
        try {
            matrix = new ObjectMapper().readValue(jsonObject.toString(), HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createProvinces(Set<String> provinceKeys) {
        System.out.println("Initialising provinces...");
        for (String provinceKey : provinceKeys)
            provinces.put(provinceKey, new Province(provinceKey));
    }

    public Province getProvince(String name) {
        return provinces.get(name);
    }

    public int numProvinces() {
        return provinces.size();
    }
}
