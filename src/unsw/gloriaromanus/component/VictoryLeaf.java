package unsw.gloriaromanus.component;

import org.json.JSONObject;

import unsw.gloriaromanus.Faction;
import unsw.gloriaromanus.World;

public abstract class VictoryLeaf implements VictoryCondition {

    @Override
    public void add(VictoryCondition e) {
        return;
    }

    @Override
    public abstract Boolean evaluate(Faction player, World world);

    @Override
    public String nameString() {
        return this.getClass().toString();
    }

    public static VictoryCondition deserialize(JSONObject json) {
        switch (json.getString("type")) {
            case "WEALTH": return new WealthLeaf();
            case "TREASURY": return new TreasuryLeaf();
            case "CONQUEST": return new ConquestLeaf();
            default: return null;
        }
    }
}
