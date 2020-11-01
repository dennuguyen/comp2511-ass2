package unsw.gloriaromanus;

import java.util.ArrayList;

public abstract class Composite implements VictoryCondition {

    ArrayList<VictoryCondition> children;

    /**
     * Constructs composite victory condition
     * 
     */
    public Composite() {
        this.children = new ArrayList<VictoryCondition>();
    }

    @Override
    public void add(VictoryCondition e) {
        children.add(e);
    }

    @Override
    public abstract Boolean evaluate();

    @Override
    public String nameString() {
        String answer = "[" + this.getClass() + " ";
        for (VictoryCondition c : children) {
            answer = answer + " " + c.nameString();
        }
        answer = answer + "]";
        return answer;
    }

}
