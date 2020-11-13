package unsw.gloriaromanus.victory;

import java.util.List;
import unsw.gloriaromanus.faction.Faction;
import unsw.gloriaromanus.system.World;

public class WealthLeaf extends VictoryLeaf {

    @Override
    public String nameString() {
        return "[WEALTH GOAL]";
    }

    @Override
    public Boolean evaluate(Faction player, World world) {
        if (player.calculateWealth() >= 400000)
            return true;
        return false;
    }

    @Override
    public List<VictoryCondition> getChildren() {
        return null;
    }
}
