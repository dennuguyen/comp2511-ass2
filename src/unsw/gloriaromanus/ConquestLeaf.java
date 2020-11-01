package unsw.gloriaromanus;

public class ConquestLeaf extends Leaf  {

    World world; 

    public ConquestLeaf(Faction f, World world){
        super(f);
        this.world = world;
    }

    @Override
    public Boolean evaluate(){
        Faction f = super.getFaction();
        if (f.numTerritories() == world.getMap().size()) return true;
        return false;
    }
}