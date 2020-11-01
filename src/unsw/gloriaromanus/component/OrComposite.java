package unsw.gloriaromanus.component;

public class OrComposite extends VictoryComposite {

    @Override
    public Boolean evaluate() {
        for (VictoryCondition expr : children)
            if (expr.evaluate())
                return true;
        return false;
    }

}
