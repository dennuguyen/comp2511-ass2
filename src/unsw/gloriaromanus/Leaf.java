package unsw.gloriaromanus;

public abstract class Leaf implements Condition {
    
    private Faction faction;

    /**
     * Constructs leaf victory condition
     * 
     * @param faction player's faction
     */
    public Leaf(Faction faction){ 
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
    public void add(Condition expr){ 
    }

    @Override
    public abstract Boolean evaluate(); 

    @Override
    public String nameString() {
        return this.getClass().toString();
    }
}
