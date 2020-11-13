package unsw.gloriaromanus.victory;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import unsw.gloriaromanus.faction.Faction;
import unsw.gloriaromanus.system.World;

public abstract class VictoryComposite implements VictoryCondition {

    private ArrayList<VictoryCondition> children;

    /**
     * Constructs composite victory condition
     * 
     */
    public VictoryComposite() {
        this.children = new ArrayList<VictoryCondition>();
    }

    @Override
    public void add(VictoryCondition e) {
        children.add(e);
    }

    @Override
    public abstract Boolean evaluate(Faction player, World world);

    @Override 
    public List<VictoryCondition> getChildren() {
        return children;
    }

    @Override
    public abstract String nameString();

    public static VictoryCondition deserialize(JSONObject json) {
        VictoryCondition vc = null;
        switch (json.getString("type")) {
            case "AND":
                vc = new AndComposite();
                break;
            case "OR":
                vc = new OrComposite();
                break;
        }
        JSONArray array = json.getJSONArray("args");
        for (int i = 0; i < array.length(); i++) {
            JSONObject j = array.getJSONObject(i);
            switch (j.getString("type")) {
                case "AND":
                    vc.add(VictoryComposite.deserialize(j));
                    break;
                case "OR":
                    vc.add(VictoryComposite.deserialize(j));
                    break;
                case "TREASURY":
                    vc.add(VictoryLeaf.deserialize(j));
                    break;
                case "WEALTH":
                    vc.add(VictoryLeaf.deserialize(j));
                    break;
                case "CONQUEST":
                    vc.add(VictoryLeaf.deserialize(j));
                    break;
            }
        }
        return vc;
    }

}