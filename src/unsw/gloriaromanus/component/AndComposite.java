package unsw.gloriaromanus.component;

public class AndComposite extends VictoryComposite {

    @Override
    public Boolean evaluate() {
        for (VictoryCondition expr : children)
            if (!expr.evaluate())
                return false;
        return true;
    }
}
