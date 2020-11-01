package unsw.gloriaromanus.component;

import unsw.gloriaromanus.Faction;

public class WealthLeaf extends VictoryLeaf {

    /**
     * Constructs wealth victory condition
     * 
     * @param f player's faction
     */
    public WealthLeaf(Faction f) {
        super(f);
    }

    @Override
    public Boolean evaluate() {
        Faction f = super.getFaction();
        if (f.calculateWealth() >= 400000)
            return true;
        return false;
    }
}
