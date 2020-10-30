package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unsw.gloriaromanus.component.Stats;

public class StatsTest {

    Stats stats;

    @Before
    public void setUp() {
        this.stats = new Stats();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void statsShouldHaveDefaultValues() {
        assertEquals(0, stats.getStat(Stats.Type.STRENGTH));
        assertEquals(0, stats.getStat(Stats.Type.ARMOUR));
        assertEquals(0, stats.getStat(Stats.Type.DISCIPLINE));
        assertEquals(0, stats.getStat(Stats.Type.FLANKING));
    }

    @Test
    public void statsShouldBeSettable() {
        stats.setStat(Stats.Type.STRENGTH, 500);
        stats.setStat(Stats.Type.FLANKING, 3);
        assertEquals(500, stats.getStat(Stats.Type.STRENGTH));
        assertEquals(3, stats.getStat(Stats.Type.FLANKING));
    }

    @Test
    public void statsShouldHaveLimits() {
        stats.setStat(Stats.Type.STRENGTH, 2000);
        stats.setStat(Stats.Type.FLANKING, -10);
        assertEquals(1000, stats.getStat(Stats.Type.STRENGTH));
        assertEquals(0, stats.getStat(Stats.Type.FLANKING));
    }

    @Test
    public void statsShouldBeAdditive() {

    }
}
