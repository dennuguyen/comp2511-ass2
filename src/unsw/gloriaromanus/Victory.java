package unsw.gloriaromanus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.gloriaromanus.component.AndComposite;
import unsw.gloriaromanus.component.ConquestLeaf;
import unsw.gloriaromanus.component.OrComposite;
import unsw.gloriaromanus.component.TreasuryLeaf;
import unsw.gloriaromanus.component.VictoryCondition;
import unsw.gloriaromanus.component.WealthLeaf;

public class Victory implements Entity {

    private static final long serialVersionUID = -3200195314475452735L;
    private VictoryCondition victoryCondition;
    private List<VictoryCondition> unusedConditions;

    public Victory() {
        this.unusedConditions = getAllConditions();
        victoryCondition = generateVictoryCondition();
    }

    public Victory(VictoryCondition victoryCondition) {
        this.victoryCondition = victoryCondition;
    }

    private List<VictoryCondition> getAllConditions() {
        List<VictoryCondition> leaves = new ArrayList<VictoryCondition>();
        leaves.add(new ConquestLeaf());
        leaves.add(new TreasuryLeaf());
        leaves.add(new WealthLeaf());
        return leaves;
    }

    private VictoryCondition chooseLeaf(List<VictoryCondition> leaves) {
        Random rand = new Random();
        int i = rand.nextInt(leaves.size());
        return leaves.get(i);
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

    private VictoryCondition generateVictoryCondition() {
        Random rand = new Random();
        int i = rand.nextInt(2);
        return recursiveVictoryCondition((double) (i + 2));
    }

    private VictoryCondition recursiveVictoryCondition(double numNodes) {
        if (numNodes == 1.0) {
            VictoryCondition vc = chooseLeaf(unusedConditions);
            unusedConditions.remove(vc);
            return vc;
        }
        VictoryCondition child1 = recursiveVictoryCondition(Math.floor(numNodes / 2));
        VictoryCondition child2 = recursiveVictoryCondition(Math.ceil(numNodes / 2));
        VictoryCondition composite = chooseComposite();
        composite.add(child1);
        composite.add(child2);
        return composite;
    }

    public boolean getResult(Faction player, World world) {
        return victoryCondition.evaluate(player, world);
    }

    public JSONObject serialize(VictoryCondition vc) {
        JSONObject json = new JSONObject();
        if (vc instanceof AndComposite) {
            json.put("type", "AND");
            JSONArray array = new JSONArray();
            for (VictoryCondition v : vc.getChildren()) {
                array.put(serialize(v));
            }
            json.put("args", array);
        }
        else if (vc instanceof OrComposite) {
            json.put("type", "OR");
            JSONArray array = new JSONArray();
            for (VictoryCondition v : vc.getChildren()) {
                array.put(serialize(v));
            }
            json.put("args", array);
        } 
        else if (vc instanceof ConquestLeaf) {
            json.put("type", "CONQUEST");
        }
        else if (vc instanceof TreasuryLeaf) {
            json.put("type", "TREASURY");
        }
        else if (vc instanceof WealthLeaf) {
            json.put("type", "WEALTH");
        }
        return json;
    }

}
