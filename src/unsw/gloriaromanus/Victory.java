package unsw.gloriaromanus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import unsw.gloriaromanus.component.AndComposite;
import unsw.gloriaromanus.component.ConquestLeaf;
import unsw.gloriaromanus.component.OrComposite;
import unsw.gloriaromanus.component.TreasuryLeaf;
import unsw.gloriaromanus.component.VictoryCondition;
import unsw.gloriaromanus.component.WealthLeaf;

public class Victory implements Entity {

    private static final long serialVersionUID = -3200195314475452735L;
    private Faction player;
    private World world;
    private VictoryCondition victoryCondition;

    public Victory(Faction player, World world) {
        this.player = player;
        this.world = world;
    }

    private List<VictoryCondition> generateListLeaves() {
        List<VictoryCondition> leaves = new ArrayList<VictoryCondition>();
        leaves.add(new ConquestLeaf(player, world));
        leaves.add(new TreasuryLeaf(player));
        leaves.add(new WealthLeaf(player));
        return leaves;
    }

    private VictoryCondition chooseComposite() {
        Random rand = new Random();
        int i = rand.nextInt(2);
        if (i == 0)
            return new AndComposite();
        else
            return new OrComposite();
    }

    public VictoryCondition getVictoryCondition() {
        return victoryCondition;
    }

    public void generateVictoryCondition() {
        Random rand = new Random();
        List<VictoryCondition> leaves = generateListLeaves();
        VictoryCondition composite1 = chooseComposite();
        int index = rand.nextInt(3);
        composite1.add(leaves.get(index));
        leaves.remove(index);
        index = rand.nextInt(2);
        composite1.add(leaves.get(index));
        leaves.remove(index);
        VictoryCondition composite2 = chooseComposite();
        composite2.add(leaves.get(0));
        composite2.add(composite1);

        victoryCondition = composite2;
    }

    public boolean getResult() {
        return victoryCondition.evaluate();
    }

}
