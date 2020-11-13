package unsw.gloriaromanus;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

import unsw.gloriaromanus.component.HighTax;
import unsw.gloriaromanus.component.LowTax;
import unsw.gloriaromanus.component.VictoryComposite;
import unsw.gloriaromanus.component.VictoryCondition;
import unsw.gloriaromanus.util.Util;

public class PersistanceFactory {

    private static FileWriter file;

    public Victory deserializeVictory(JSONObject json) {
        JSONObject victoryCondition = json.getJSONObject("victoryConditions");
        VictoryCondition vc =  VictoryComposite.deserialize(victoryCondition);
        return new Victory(vc);
    }

    public List<Faction> deserializePlayers(World world, JSONObject json) {
        List<Faction> factions = new ArrayList<Faction>();
        JSONArray players = json.getJSONArray("players");
        for (int i = 0; i < players.length(); i++) {
            JSONObject j = players.getJSONObject(i);
            factions.add(Faction.deserialize(world, j));
        }
        return factions;
    }

    public boolean deserializeWinner(JSONObject json) {
        if (json.has("winner")) {
            return true;
        } 
        return false;
    }

    public int deserializeCurrentPlayer(JSONObject json) {
        return json.getInt("currentPlayer");
    }
    
    public static void writeToFile(int currentPlayer, Victory v, List<Faction> f, String winner) {
        try {
            file = new FileWriter("save.json");
            JSONObject json = new JSONObject();
            json.put("victoryConditions", v.serialize(v.getVictoryCondition()));
            JSONArray players = new JSONArray();
            for (Faction faction : f) {
                players.put(faction.serialize());
            }
            json.put("players", players);
            if (!winner.equals("NO")) {
                json.put("winner", winner);
            }
            json.put("currentPlayer", currentPlayer);
            file.write(json.toString(4));
            file.close();
            System.out.println("Successfully written to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public static void main(String[] args) {
    //     Victory v = new Victory();
    //     World.init("src/unsw/gloriaromanus/province_adjacency_matrix_fully_connected.json");
    //     World world = World.getInstance();
    //     List<Faction> f = new ArrayList<Faction>();
    //     Faction romans = new Faction("Rome");
    //     f.add(romans);
    //     for (Map.Entry<String, Province> entry : world.getProvinces().entrySet())  
    //         romans.addProvince(entry.getValue());
    //     PersistanceFactory pf = new PersistanceFactory();
    //     PersistanceFactory.writeToFile(v, f, "");
    //     JSONObject save = Util.parseJsonFile("save.json"); 
    //     pf.deserializeVictory(save);
    //     pf.deserializePlayers(world, save);
    // }
}