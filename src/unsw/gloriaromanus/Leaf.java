package unsw.gloriaromanus;

public abstract class Leaf implements Expression {
    
    Faction faction;

    public Leaf(Faction faction){ 
        this.faction = faction;
    }

    @Override
    public void add(Expression expr){ 
    }

    @Override
    public abstract Boolean evaluate(); 
}
