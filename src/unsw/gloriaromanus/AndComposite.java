package unsw.gloriaromanus;

public class AndComposite extends Composite {

    public AndComposite(Faction f){
        super(f);
    }

    @Override
    public  Boolean evaluate(){
        for(Condition expr : children) 
            if(!expr.evaluate()) return false;
        return true;
    }
}