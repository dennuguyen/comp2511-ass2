package unsw.gloriaromanus;

public class TreasuryLeaf extends Leaf  {

    public TreasuryLeaf(Faction f){
        super(f);
    }

    @Override
    public Boolean evaluate(){
        Faction f = super.getFaction();
        if (f.getTreasury() >= 100000) return true;
        return false;
    }
}