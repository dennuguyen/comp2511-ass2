package unsw.gloriaromanus.component;

import unsw.gloriaromanus.Faction;

public abstract class VictoryLeaf implements VictoryCondition {

    private Faction faction;

    /**
     * Constructs leaf victory condition
     * 
     * @param faction player's faction
     */
    public VictoryLeaf(Faction faction) {
        this.faction = faction;
    }

    /**
     * Returns player's faction
     * 
     * @return player's faction
     */
    public Faction getFaction() {
        return faction;
    }

    @Override
    public void add(VictoryCondition e) {
    }

    @Override
    public abstract Boolean evaluate();

    @Override
    public String nameString() {
        return this.getClass().toString();
    }
}
