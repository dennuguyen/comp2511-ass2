package unsw.gloriaromanus.component;

import java.util.List;

import unsw.gloriaromanus.Faction;
import unsw.gloriaromanus.World;

public class TreasuryLeaf extends VictoryLeaf {

    @Override
    public String nameString() {
        return "[TREASURY GOAL]";
    }
    
    @Override
    public Boolean evaluate(Faction player, World world) {
        if (player.getTreasury() >= 100000)
            return true;
        return false;
    }

    @Override 
    public List<VictoryCondition> getChildren() {
        return null;
    }
}
