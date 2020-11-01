package unsw.gloriaromanus;

public class ConquestLeaf extends VictoryLeaf {

    private World world;

    /**
     * Constructs conquest victory condition
     * 
     * @param f     player's faction
     * @param world world in which game is set
     */
    public ConquestLeaf(Faction f, World world) {
        super(f);
        this.world = world;
    }

    @Override
    public Boolean evaluate() {
        Faction f = super.getFaction();
        if (f.numTerritories() == world.getMap().size())
            return true;
        return false;
    }
}
