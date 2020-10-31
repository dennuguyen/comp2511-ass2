package unsw.gloriaromanus;

import unsw.gloriaromanus.Faction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import unsw.gloriaromanus.Condition;

public class Victory {

    private Faction player;
    private Condition victoryCondition;

    public Victory(Faction player) {
        this.player = player;
    }

    public void generateVictoryCondition() {
        List<Condition> leaves = new ArrayList<Condition>();
        leaves.add(new ConquestLeaf(player));
        leaves.add(new TreasuryLeaf(player));
        leaves.add(new WealthLeaf(player));

        Condition composite1 = chooseComposite();
        Random rand = new Random();
        int index = rand.nextInt(3);
        composite1.add(leaves.get(index));
        leaves.remove(index);
        index = rand.nextInt(2);
        composite1.add(leaves.get(index));
        leaves.remove(index);
        Condition composite2 = chooseComposite();
        composite2.add(leaves.get(0));
        composite2.add(composite1);

        victoryCondition = composite2;
    }

    public Condition chooseComposite() {
        Random rand = new Random();
        int i = rand.nextInt(2);
        if (i == 0) return new AndComposite(player);
        else return new OrComposite(player);
    }


}