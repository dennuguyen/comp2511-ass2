package unsw.gloriaromanus;

import unsw.gloriaromanus.component.Stats;

public class Break {

  
    /**
     * Calculates chance of unit breaking after an engagement
     * 
     * @return chance of unit breaking
     */
    public int calculateBreakChance(Engagement e, Unit breaker, Unit enemy) {
        int base = 100 - (breaker.getStat(Stats.Type.MORALE) * 10);
                    
        int x = e.getCasualties(breaker)/e.getInitialUnitSize(breaker);

        int y = e.getCasualties(enemy)/e.getInitialUnitSize(enemy);

        return base + x * 10 / y;
    }

    /**
     * Determines if unit will break
     * 
     * @return if unit will break
     */
    public boolean isBroken(Engagement e, Unit breaker, Unit enemy) {
        var d = Math.random() * 100;
        if ((int) d < calculateBreakChance(e, breaker, enemy))
            return true;
        return false;
    }
}
