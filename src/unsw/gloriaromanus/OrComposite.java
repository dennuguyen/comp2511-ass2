package unsw.gloriaromanus;

public class OrComposite extends Composite {
    
    public OrComposite(Faction f){
        super(f);
    }

    @Override
    public Boolean evaluate(){
        for(Expression expr : children)
            if(expr.evaluate()) return true;
        return false;
    }

}