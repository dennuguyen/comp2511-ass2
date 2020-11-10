package unsw.gloriaromanus.component;

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
}
