package unsw.gloriaromanus;

import unsw.gloriaromanus.component.Stats;

public class Rout {

    
    /**
     * Limits number between 5 and 95 
     * 
     * @param num value to be modified
     * 
     * @return modified number
     */
    public int limit(int num) {
        if (num > 95) 
            return 100;
        if (num < 10) 
            return 5;
        return num;
    }
  
    /**
     * Calculates chance of unit successfully routing 
     * 
     * @return chance of unit routing
     */
    public int calculateRoutChance(Unit router, Unit pursuer) {
        return limit(50 + 10 * (router.getStat(Stats.Type.TACTICS) - pursuer.getStat(Stats.Type.TACTICS)));
    }

    /**
     * Determines if unit will successfully rout
     * 
     * @return if unit will rout
     */
    public boolean isBroken(Unit router, Unit pursuer) {
        var d = Math.random() * 100;
        if ((int) d < calculateRoutChance(router, pursuer))
            return true;
        return false;
    }
}
