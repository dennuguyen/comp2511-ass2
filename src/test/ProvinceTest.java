package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.gloriaromanus.Province;
import unsw.gloriaromanus.RomanLegionary;
import unsw.gloriaromanus.Turn;
import unsw.gloriaromanus.World;
import unsw.gloriaromanus.component.Stats;
import unsw.gloriaromanus.component.VeryHighTax;
import unsw.gloriaromanus.Levyable;

public class ProvinceTest {

    Turn turn;
    World world;

    @Before
    public void setUp() {
        this.turn = Turn.getInstance();
        this.world = new World("src/unsw/gloriaromanus/province_adjacency_matrix.json");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void campShouldSpawnUnit() {
        Province noricum = world.getProvince("Noricum");
        RomanLegionary roman1 = (RomanLegionary) noricum.recruit(Levyable.Type.RomanLegionary);
        assertEquals(roman1.getLocation(), noricum.getLocation());
        assertEquals(0, roman1.getStat(Stats.Type.STRENGTH));
    }

    @Test
    public void unitShouldRecoverManpower() {
        Province achaia = world.getProvince("Achaia");
        RomanLegionary roman2 = (RomanLegionary) achaia.recruit(Levyable.Type.RomanLegionary);
        assertEquals(roman2.getLocation(), achaia.getLocation());
        assertEquals(0, roman2.getStat(Stats.Type.STRENGTH));

        this.turn.incrementTurn();
        assertEquals(500, roman2.getStat(Stats.Type.STRENGTH));

        // this.turn.incrementTurn();
        // assertEquals(1000, roman.getStat(Stats.Type.STRENGTH));
    }

    @Test
    public void provinceTaxSettingShouldAffectWealthGrowthAndMorale() {
        // Province macedonia = world.getProvince("Macedonia");
        // RomanLegionary anotherRoman = (RomanLegionary)
        // macedonia.recruit(Levyable.Type.RomanLegionary);

        // int initialMorale = anotherRoman.getStat(Stats.Type.MORALE);
        // int initialWealthGrowth = macedonia.getWealthGrowth();

        // macedonia.setTaxLevel(new VeryHighTax());

        // assertNotEquals(initialMorale, anotherRoman.getStat(Stats.Type.MORALE));
        // assertNotEquals(initialWealthGrowth, macedonia.getWealthGrowth());
    }
}
