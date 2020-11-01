package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.Test;

import unsw.gloriaromanus.Faction;
import unsw.gloriaromanus.Province;
import unsw.gloriaromanus.Victory;
import unsw.gloriaromanus.World;

public class VictoryTest {

    @Test
    public void victoryConditionShouldSucceed() {
        World.init("src/unsw/gloriaromanus/province_adjacency_matrix_fully_connected.json");
        World world = World.getInstance();

        Faction player = new Faction("player");
    
        Map<String, Province> provinces = world.getProvinces();
        
        Victory victory = new Victory(player, world);
        victory.generateVictoryCondition();

        player.addTreasury(200000);
        Province britannia = world.getProvince("Britannia");
        britannia.addWealth(500000);
        for (Map.Entry<String, Province> entry : provinces.entrySet())
            player.addProvince(entry.getValue());

        assertEquals(true, victory.getResult());
}