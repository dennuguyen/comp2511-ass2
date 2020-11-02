package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.gloriaromanus.Faction;
import unsw.gloriaromanus.component.OrComposite;
import unsw.gloriaromanus.Province;
import unsw.gloriaromanus.component.TreasuryLeaf;
import unsw.gloriaromanus.Victory;
import unsw.gloriaromanus.component.WealthLeaf;
import unsw.gloriaromanus.World;
import unsw.gloriaromanus.component.AndComposite;
import unsw.gloriaromanus.component.VictoryCondition;
import unsw.gloriaromanus.component.ConquestLeaf;

public class VictoryTest {
    World world;

    @Before
    public void setUp() {
        World.init("src/unsw/gloriaromanus/province_adjacency_matrix_fully_connected.json");
        world = World.getInstance();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void victoryConditionShouldGenerate() {
        Faction player = new Faction("player");
        Victory victory = new Victory(player, world);
        victory.generateVictoryCondition();
        assertEquals(false, victory.getResult());
        player.addTreasury(100000);
        Province britannia = world.getProvince("Britannia");
        britannia.addWealth(100000);
        for (String key : world.getMap().keySet()) {
            player.addProvince(world.getProvince(key));
        }
        assertEquals(true, victory.getResult());
    }

    @Test
    public void victoryConditionShouldSucceed2() {
        Faction player = new Faction("player");
        VictoryCondition CONQUEST = new ConquestLeaf(player, world);
        VictoryCondition TREASURY = new TreasuryLeaf(player);
        VictoryCondition WEALTH = new WealthLeaf(player);
        VictoryCondition AND = new AndComposite();
        VictoryCondition OR = new OrComposite();
        AND.add(TREASURY);
        AND.add(CONQUEST);
        OR.add(AND);
        OR.add(WEALTH);
        player.addTreasury(100000);
        Province britannia = world.getProvince("Britannia");
        britannia.addWealth(100000);
        for (String key : world.getMap().keySet()) {
            player.addProvince(world.getProvince(key));
        }
        assertEquals(true, OR.evaluate());
        player.addTreasury(-1);
        assertEquals(false, OR.evaluate());
        britannia.addWealth(300000);
        assertEquals(true, OR.evaluate());
    }

    @Test
    public void victoryConditionShouldSucceed3() {
        Faction player = new Faction("player");
        VictoryCondition CONQUEST = new ConquestLeaf(player, world);
        VictoryCondition TREASURY = new TreasuryLeaf(player);
        VictoryCondition WEALTH = new WealthLeaf(player);
        VictoryCondition AND = new AndComposite();
        VictoryCondition OR = new OrComposite();
        OR.add(WEALTH);
        OR.add(TREASURY);
        AND.add(OR);
        AND.add(CONQUEST);
        assertEquals(false, AND.evaluate());
        player.addTreasury(100001);
        assertEquals(false, AND.evaluate());
        Province britannia = world.getProvince("Britannia");
        assertEquals(0, britannia.getWealth());
        britannia.addWealth(100000);
        assertEquals(false, AND.evaluate());
        for (String key : world.getMap().keySet()) {
            player.addProvince(world.getProvince(key));
        }
        assertEquals(true, AND.evaluate());
        player.addTreasury(-100001);
        assertEquals(false, AND.evaluate());
        britannia.addWealth(300001);
        assertEquals(true, AND.evaluate());
    }


}
