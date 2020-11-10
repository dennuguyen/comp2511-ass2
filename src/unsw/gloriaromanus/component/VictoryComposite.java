package unsw.gloriaromanus.component;

import java.util.ArrayList;
import java.util.List;

import unsw.gloriaromanus.Faction;
import unsw.gloriaromanus.World;

public abstract class VictoryComposite implements VictoryCondition {

    ArrayList<VictoryCondition> children;

    /**
     * Constructs composite victory condition
     * 
     */
    public VictoryComposite() {
        this.children = new ArrayList<VictoryCondition>();
    }

    @Override
    public void add(VictoryCondition e) {
        children.add(e);
    }

    @Override
    public abstract Boolean evaluate(Faction player, World world);

    @Override
    public String nameString() {
        String answer = "[" + this.getClass() + " ";
        for (VictoryCondition c : children) {
            answer = answer + " " + c.nameString();
        }
        answer = answer + "]";
        return answer;
    }

    @Override 
    public List<VictoryCondition> getChildren() {
        return children;
    }

}
