package unsw.gloriaromanus;

import java.util.ArrayList;

public abstract class Composite implements Condition {

    private Faction faction;

    public ArrayList<Condition> children;

    public Composite(Faction faction){ 
        this.children = new ArrayList<Condition>(); 
        this.faction = faction;
    }

    @Override
    public void add(Condition e){
        children.add(e); 
    }

    @Override
    public abstract Boolean evaluate();

}