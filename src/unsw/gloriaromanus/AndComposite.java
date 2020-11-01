package unsw.gloriaromanus;

public class AndComposite extends Composite {

    @Override
    public Boolean evaluate() {
        for (VictoryCondition expr : children)
            if (!expr.evaluate())
                return false;
        return true;
    }
}
