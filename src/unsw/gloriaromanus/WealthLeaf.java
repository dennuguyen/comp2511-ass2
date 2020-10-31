package unsw.gloriaromanus;

public class WealthLeaf extends Leaf  {

    World w;

    public WealthLeaf(Faction f){
        super(f);
    }

    @Override
    public Boolean evaluate(){
        Faction f = super.getFaction();
        if (f.getWealth() >= 400000) return true;
        return false;
    }
}