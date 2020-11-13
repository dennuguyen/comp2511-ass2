package unsw.gloriaromanus.victory;

import unsw.gloriaromanus.faction.Faction;
import unsw.gloriaromanus.system.World;

public class AndComposite extends VictoryComposite {

    @Override
    public String nameString() {
        String answer = "{";
        boolean firstIteration = true;
        for (VictoryCondition vc: getChildren()) {
            if (firstIteration) {
                firstIteration = false;
            } else {
                answer += " AND ";
            }
            answer += vc.nameString();
        }
        answer += "}";
        return answer;
    }
    
    @Override
    public Boolean evaluate(Faction player, World world) {
        for (VictoryCondition expr : getChildren())
            if (!expr.evaluate(player, world))
                return false;
        return true;
    }
}