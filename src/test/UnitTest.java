package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.gloriaromanus.RomanLegionary;
import unsw.gloriaromanus.Unit;
import unsw.gloriaromanus.component.Move;
import unsw.gloriaromanus.component.Stats;

public class UnitTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void unitShouldSpawnWithCorrectStats() {
        Unit unit = new Unit("location1", Move.Type.CAVALRY, new Stats());
        assertEquals("location1", unit.getLocation());
        assertEquals(0, unit.getStat(Stats.Type.STRENGTH));
    }

    @Test
    public void romanLegionaryShouldHaveDefaultStats() {
        RomanLegionary legionary = new RomanLegionary("Rome");
        assertEquals("Rome", legionary.getLocation());
        assertEquals(4, legionary.getStat(Stats.Type.ARMOUR));
        assertEquals(8, legionary.getStat(Stats.Type.DISCIPLINE));
        assertEquals(10, legionary.getStat(Stats.Type.FIRE));
        assertEquals(6, legionary.getStat(Stats.Type.FLANKING));
        assertEquals(1000, legionary.getStat(Stats.Type.STRENGTH));
        assertEquals(5, legionary.getStat(Stats.Type.TACTICS));
    }

    @Test
    public void unitShouldBeAbletoSetStats() {
        Unit unit = new Unit("Rome", Move.Type.ARTILLERY, new Stats());
        unit.setStat(Stats.Type.SHIELD, 2);
        assertEquals(2, unit.getStat(Stats.Type.SHIELD));
    }
}
