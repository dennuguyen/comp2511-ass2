package unsw.gloriaromanus;

public abstract class Leaf implements Condition {
    
    Faction faction;

    public Leaf(Faction faction){ 
        this.faction = faction;
    }

    @Override
    public void add(Condition expr){ 
    }

    @Override
    public abstract Boolean evaluate(); 
}
