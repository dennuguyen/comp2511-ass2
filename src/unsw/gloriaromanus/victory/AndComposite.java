package unsw.gloriaromanus.victory;

import unsw.gloriaromanus.faction.Faction;
import unsw.gloriaromanus.system.World;

public class AndComposite extends VictoryComposite {

    @Override
    public Boolean evaluate(Faction player, World world) {
        for (VictoryCondition expr : children)
            if (!expr.evaluate(player, world))
                return false;
        return true;
    }
}
