package unsw.gloriaromanus;

public abstract class Leaf implements Condition {
    
    private Faction faction;

    public Leaf(Faction faction){ 
        this.faction = faction;
    }

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
