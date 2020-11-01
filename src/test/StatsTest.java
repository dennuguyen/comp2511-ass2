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
        stats = null;
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
        stats.setStat(Stats.Type.STRENGTH, 50);
        stats.addStat(Stats.Type.STRENGTH, 100);
        assertEquals(150, stats.getStat(Stats.Type.STRENGTH));
    }

    @Test
    public void statsShouldBeMultiplicative() {
        stats.setStat(Stats.Type.STRENGTH, 100);
        stats.multiplyStat(Stats.Type.STRENGTH, 50);
        assertEquals(150, stats.getStat(Stats.Type.STRENGTH));

        stats.multiplyStat(Stats.Type.STRENGTH, -80);
        assertEquals(30, stats.getStat(Stats.Type.STRENGTH));
    }

    @Test
    public void statsShouldNotBeAddedOverLimit() {
        stats.addStat(Stats.Type.FLANKING, 100);
        assertEquals(10, stats.getStat(Stats.Type.FLANKING));
    }

    @Test
    public void statsShouldNotBeMultipliedOverLimit() {
        stats.setStat(Stats.Type.FLANKING, 1);
        stats.multiplyStat(Stats.Type.FLANKING, 1000);
        assertEquals(10, stats.getStat(Stats.Type.FLANKING));
    }
}
