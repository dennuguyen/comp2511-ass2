package unsw.gloriaromanus.component;

import unsw.gloriaromanus.Faction;
import unsw.gloriaromanus.World;

public class AndComposite extends VictoryComposite {

    @Override
    public Boolean evaluate(Faction player, World world) {
        for (VictoryCondition expr : children)
            if (!expr.evaluate(player, world))
                return false;
        return true;
    }
}
