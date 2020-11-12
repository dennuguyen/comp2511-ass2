package unsw.gloriaromanus.victory;

import java.util.List;
import unsw.gloriaromanus.faction.Faction;
import unsw.gloriaromanus.system.World;

public interface VictoryCondition {
    public String nameString();

    /**
     * Adds a condition to condition
     * 
     * @param expr condition to be added
     */
    public void add(VictoryCondition expr);

    /**
     * Evaluates condition
     * 
     * @return if condition if true
     */
    public Boolean evaluate(Faction player, World world);

    public List<VictoryCondition> getChildren();
}
