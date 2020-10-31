package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.gloriaromanus.Province;
import unsw.gloriaromanus.RomanLegionary;
import unsw.gloriaromanus.Turn;
import unsw.gloriaromanus.component.Stats;
import unsw.gloriaromanus.component.VeryHighTax;
import unsw.gloriaromanus.Levyable;

public class ProvinceTest {

    Turn turn;

    @Before
    public void setUp() {
        this.turn = Turn.getInstance();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void campShouldSpawnUnit() {
        Province noricum = new Province("Noricum");
        RomanLegionary roman1 = (RomanLegionary) noricum.recruit(Levyable.Type.RomanLegionary);
        assertEquals(roman1.getLocation(), noricum.getLocation());
        assertEquals(0, roman1.getStat(Stats.Type.STRENGTH));

        int initialMorale = roman1.getStat(Stats.Type.MORALE);
        int initialWealthGrowth = noricum.getWealthGrowth();

        noricum.setTaxLevel(new VeryHighTax());

        assertNotEquals(initialMorale, roman1.getStat(Stats.Type.MORALE));
        assertNotEquals(initialWealthGrowth, noricum.getWealthGrowth());
    }

    @Test
    public void unitShouldRecoverManpower() {
        Province achaia = new Province("Achaia");
        RomanLegionary roman2 = (RomanLegionary) achaia.recruit(Levyable.Type.RomanLegionary);
        assertEquals(roman2.getLocation(), achaia.getLocation());
        assertEquals(0, roman2.getStat(Stats.Type.STRENGTH));

        this.turn.incrementTurn();
        assertEquals(500, roman2.getStat(Stats.Type.STRENGTH));

        this.turn.incrementTurn();
        assertEquals(1000, roman2.getStat(Stats.Type.STRENGTH));
    }

    @Test
    public void multipleUnitsShouldRecoverManpower() {
        Province raetia = new Province("Raetia");
        RomanLegionary roman3 = (RomanLegionary) raetia.recruit(Levyable.Type.RomanLegionary);
        RomanLegionary roman4 = (RomanLegionary) raetia.recruit(Levyable.Type.RomanLegionary);

        assertEquals(roman3.getLocation(), raetia.getLocation());
        assertEquals(roman4.getLocation(), raetia.getLocation());
        assertEquals(0, roman3.getStat(Stats.Type.STRENGTH));
        assertEquals(0, roman4.getStat(Stats.Type.STRENGTH));

        this.turn.incrementTurn();
        assertEquals(500, roman3.getStat(Stats.Type.STRENGTH));
        assertEquals(0, roman4.getStat(Stats.Type.STRENGTH));

        this.turn.incrementTurn();
        assertEquals(1000, roman3.getStat(Stats.Type.STRENGTH));
        assertEquals(0, roman4.getStat(Stats.Type.STRENGTH));

        this.turn.incrementTurn();
        assertEquals(1000, roman3.getStat(Stats.Type.STRENGTH));
        assertEquals(500, roman4.getStat(Stats.Type.STRENGTH));

        this.turn.incrementTurn();
        assertEquals(1000, roman3.getStat(Stats.Type.STRENGTH));
        assertEquals(1000, roman4.getStat(Stats.Type.STRENGTH));
    }
}
