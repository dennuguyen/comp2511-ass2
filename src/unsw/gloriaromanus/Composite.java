package unsw.gloriaromanus;

import java.util.ArrayList;

public abstract class Composite implements Expression {

    private Faction faction;

    public ArrayList<Expression> children;

    public Composite(Faction faction){ 
        this.children = new ArrayList<Expression>(); 
        this.faction = faction;
    }

    @Override
    public void add(Expression e){
        children.add(e); 
    }

    @Override
    public abstract Boolean evaluate();

}