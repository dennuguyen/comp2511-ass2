package unsw.gloriaromanus;

import unsw.gloriaromanus.Faction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import unsw.gloriaromanus.Expression;

public class Victory {

    private Faction player;
    private Expression victoryCondition;

    public Victory(Faction player) {
        this.player = player;
    }

    public void generateVictoryCondition() {
        List<Expression> leaves = new ArrayList<Expression>();
        leaves.add(new ConquestLeaf(player));
        leaves.add(new TreasuryLeaf(player));
        leaves.add(new WealthLeaf(player));

        Expression composite1 = chooseComposite();
        Random rand = new Random();
        int index = rand.nextInt(3);
        composite1.add(leaves.get(index));
        leaves.remove(index);
        index = rand.nextInt(2);
        composite1.add(leaves.get(index));
        leaves.remove(index);
        Expression composite2 = chooseComposite();
        composite2.add(leaves.get(0));
        composite2.add(composite1);

        victoryCondition = composite2;
    }

    public Expression chooseComposite() {
        Random rand = new Random();
        int i = rand.nextInt(2);
        if (i == 0) return new AndComposite(player);
        else return new OrComposite(player);
    }


}