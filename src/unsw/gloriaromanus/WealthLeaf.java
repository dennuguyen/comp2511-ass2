package unsw.gloriaromanus;

public class WealthLeaf extends Leaf  {

    public WealthLeaf(Faction f){
        super(f);
    }

    @Override
    public Boolean evaluate(){
        // check Accumulating faction wealth of 400,000 gold (WEALTH goal)
        return true;
    }
}