package unsw.gloriaromanus.component;

import unsw.gloriaromanus.Faction;
import unsw.gloriaromanus.World;

public class OrComposite extends VictoryComposite {

    @Override
    public String nameString() {
        String answer = "{";
        boolean firstIteration = true;
        for (VictoryCondition vc: getChildren()) {
            if (firstIteration) {
                firstIteration = false;
            } else {
                answer += " OR ";
            }
            answer += vc.nameString();
        }
        answer += "}";
        return answer;
    }

    @Override
    public Boolean evaluate(Faction player, World world) {
        for (VictoryCondition expr : getChildren())
            if (expr.evaluate(player, world))
                return true;
        return false;
    }

}
