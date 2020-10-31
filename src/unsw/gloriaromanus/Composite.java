package unsw.gloriaromanus;

import java.util.ArrayList;

public abstract class Composite implements Condition {

    public ArrayList<Condition> children;

    public Composite(){ 
        this.children = new ArrayList<Condition>(); 
    }

    @Override
    public void add(Condition e){
        children.add(e); 
    }

    @Override
    public abstract Boolean evaluate();

}