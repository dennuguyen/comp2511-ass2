package unsw.gloriaromanus;

public class OrComposite extends Composite {

    @Override
    public Boolean evaluate(){
        for(Condition expr : children)
            if(expr.evaluate()) return true;
        return false;
    }

}