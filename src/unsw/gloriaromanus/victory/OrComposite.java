package unsw.gloriaromanus.victory;

import unsw.gloriaromanus.faction.Faction;
import unsw.gloriaromanus.system.World;

public class OrComposite extends VictoryComposite {

    @Override
    public Boolean evaluate(Faction player, World world) {
        for (VictoryCondition expr : children)
            if (expr.evaluate(player, world))
                return true;
        return false;
    }

}
