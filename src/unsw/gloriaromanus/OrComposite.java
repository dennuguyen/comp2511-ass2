package unsw.gloriaromanus;

public class OrComposite extends Composite {
    
    public OrComposite(Faction f){
        super(f);
    }

    @Override
    public Boolean evaluate(){
        for(Condition expr : children)
            if(expr.evaluate()) return true;
        return false;
    }

}