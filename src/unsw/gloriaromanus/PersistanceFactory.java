package unsw.gloriaromanus;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

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
    
    public void writeToFile(Victory v) {
        try {
            file = new FileWriter("save.json");
            JSONObject json = new JSONObject();
            json.put("victoryConditions", v.serialize(v.getVictoryCondition()));
            file.write(json.toString(4));
            file.close();
            System.out.println("Successfully written to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Victory v = new Victory();
        PersistanceFactory pf = new PersistanceFactory();
        pf.writeToFile(v);
        JSONObject save = Util.parseJsonFile("save.json"); 
        pf.deserializeVictory(save);
    }
}