package unsw.gloriaromanus.victory;

import org.json.JSONObject;
import unsw.gloriaromanus.faction.Faction;
import unsw.gloriaromanus.system.World;

public abstract class VictoryLeaf implements VictoryCondition {

    @Override
    public void add(VictoryCondition e) {
        return;
    }

    @Override
    public abstract Boolean evaluate(Faction player, World world);

    @Override
    public abstract String nameString();
    public static VictoryCondition deserialize(JSONObject json) {
        switch (json.getString("type")) {
            case "WEALTH":
                return new WealthLeaf();
            case "TREASURY":
                return new TreasuryLeaf();
            case "CONQUEST":
                return new ConquestLeaf();
            default:
                return null;
        }
    }
}
