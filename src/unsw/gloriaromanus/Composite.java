package unsw.gloriaromanus;

import java.util.ArrayList;

public abstract class Composite implements Expression {

    public ArrayList<Expression> children;

    public Composite(){ 
        this.children = new ArrayList<Expression>(); 
    }

    @Override
    public void add(Expression e){
        children.add(e); 
    }

    @Override
    public abstract Boolean evaluate();
    
    public Boolean hasChildren() {
        return !children.isEmpty(); 
    }
}