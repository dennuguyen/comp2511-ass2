package unsw.gloriaromanus;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
    
    public void writeToFile(Victory v, List<Faction> f) {
        try {
            file = new FileWriter("save.json");
            JSONObject json = new JSONObject();
            json.put("victoryConditions", v.serialize(v.getVictoryCondition()));
            JSONArray players = new JSONArray();
            for (Faction faction : f) {
                players.put(faction.serialize());
            }
            json.put("players", players);
            file.write(json.toString(4));
            file.close();
            System.out.println("Successfully written to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // //Victory v = new Victory();
        // // World.init("src/unsw/gloriaromanus/province_adjacency_matrix_fully_connected.json");
        // // World world = World.getInstance();
        // List<Faction> f = new ArrayList<Faction>();
        // Faction romans = new Faction("Romans");
        // f.add(romans);
        // Province britannia = world.getProvince("Britannia");
        // britannia.setTaxLevel(new HighTax());
        // britannia.addWealth(20000);
        // Province numidia = world.getProvince("Numidia");
        // numidia.setTaxLevel(new LowTax());
        // numidia.addWealth(50000);
        // romans.addProvince(britannia);
        // romans.addProvince(numidia);
        // romans.addTreasury(10000);
        // PersistanceFactory pf = new PersistanceFactory();
        // pf.writeToFile(v, f);
        World.init("src/unsw/gloriaromanus/province_adjacency_matrix_fully_connected.json");
        World world = World.getInstance();
        JSONObject save = Util.parseJsonFile("save.json"); 
        PersistanceFactory pf = new PersistanceFactory();
        pf.deserializeVictory(save);
        pf.deserializePlayers(world, save);
    }
}