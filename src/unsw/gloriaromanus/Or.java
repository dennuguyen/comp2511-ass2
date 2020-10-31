package unsw.gloriaromanus;

public class Or extends Composite {

    @Override
    public Boolean evaluate(){
        for(Expression expr : children)
            if(expr.evaluate()) return true;
        return false;
    }

}