package unsw.gloriaromanus.component;

import java.util.List;

import unsw.gloriaromanus.Faction;
import unsw.gloriaromanus.World;

public class ConquestLeaf extends VictoryLeaf {

    @Override
    public String nameString() {
        return "[CONQUEST GOAL]";
    }

    @Override
    public Boolean evaluate(Faction player, World world) {
        if (player.numTerritories() == world.getMap().size())
            return true;
        return false;
    }

    @Override 
    public List<VictoryCondition> getChildren() {
        return null;
    }
}
