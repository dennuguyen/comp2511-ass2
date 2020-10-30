package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.gloriaromanus.Province;
import unsw.gloriaromanus.RomanLegionary;
import unsw.gloriaromanus.World;
import unsw.gloriaromanus.UnitFactory;

public class ProvinceTest {

    World world = new World("src/unsw/gloriaromanus/province_adjacency_matrix.json");

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void unitFactoryShouldSpawnUnit() {
        Province noricum = world.getProvince("Noricum");
        RomanLegionary roman = (RomanLegionary) noricum.recruit(UnitFactory.Type.RomanLegionary);
        assertEquals(roman.getLocation(), noricum.getLocation());
    }
}
