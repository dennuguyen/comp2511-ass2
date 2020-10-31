package unsw.gloriaromanus;

public class TreasuryLeaf extends Leaf  {

    public TreasuryLeaf(Faction f){
        super(f);
    }

    @Override
    public Boolean evaluate(){
        // check Accumulating a treasury balance of 100,000 gold
        return true;
    }
}