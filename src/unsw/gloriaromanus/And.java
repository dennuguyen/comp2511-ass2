package unsw.gloriaromanus;

public class And extends Composite {

    @Override
    public  Boolean evaluate(){
        for(Expression expr : children) 
            if(!expr.evaluate()) return false;
        return true;
    }
}