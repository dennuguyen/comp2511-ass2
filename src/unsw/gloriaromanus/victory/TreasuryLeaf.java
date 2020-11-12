package unsw.gloriaromanus.victory;

import java.util.List;
import unsw.gloriaromanus.faction.Faction;
import unsw.gloriaromanus.system.World;

public class TreasuryLeaf extends VictoryLeaf {

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
