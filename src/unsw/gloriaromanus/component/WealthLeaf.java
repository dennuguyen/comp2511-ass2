package unsw.gloriaromanus.component;

import java.util.List;

import unsw.gloriaromanus.Faction;
import unsw.gloriaromanus.World;

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
