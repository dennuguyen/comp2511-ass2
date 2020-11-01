package unsw.gloriaromanus.component;

import unsw.gloriaromanus.Faction;

public class TreasuryLeaf extends VictoryLeaf {

    /**
     * Constructs treasury victory condition
     * 
     * @param f player's faction
     */
    public TreasuryLeaf(Faction f) {
        super(f);
    }

    @Override
    public Boolean evaluate() {
        Faction f = super.getFaction();
        if (f.getTreasury() >= 100000)
            return true;
        return false;
    }
}
