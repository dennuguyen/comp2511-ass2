package unsw.gloriaromanus.warfare;

import unsw.gloriaromanus.stats.Stats;
import unsw.gloriaromanus.unit.UnitLeaf;

public class Breaking {

    /**
     * Limits number between 5 and 95
     * 
     * @param num value to be modified
     * 
     * @return modified number
     */
    private int limit(int num) {
        if (num > 95)
            return 95;
        if (num < 5)
            return 5;
        return num;
    }

    /**
     * Calculates chance of unit breaking after an engagement
     * 
     * @param e       engagement between breaker and enemy unit
     * @param breaker unit attempting to break
     * @param enemy   unit opposing breaker
     * 
     * @return chance of unit breaking
     */
    public int calculateBreakChance(Engagement e, UnitLeaf breaker, UnitLeaf enemy) {
        if (e.getCasualties(breaker) == 0)
            return 0;

        int base = 100 - (breaker.getStat(Stats.Type.MORALE) * 10);

        int x = e.getCasualties(breaker) / e.getInitialUnitSize(breaker);

        int y = e.getCasualties(enemy) / e.getInitialUnitSize(enemy);

        return limit(base + x * 10 / y);
    }

    /**
     * Determines if breaker unit will break
     * 
     * @param e       engagement between breaker and enemy unit
     * @param breaker unit attempting to break
     * @param enemy   unit opposing breaker
     * 
     * @return if breaker unit successfully breaks
     */
    public boolean isBroken(Engagement e, UnitLeaf breaker, UnitLeaf enemy) {
        var d = Math.random() * 100;
        if ((int) d < calculateBreakChance(e, breaker, enemy))
            return true;
        return false;
    }
}
