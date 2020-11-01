package unsw.gloriaromanus.component;

public class Routing {

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
     * @param router  unit attempting to rout
     * @param pursuer unit pursuing router
     * 
     * @return chance of unit routing
     */
    public int calculateRoutChance(Engageable router, Engageable pursuer) {
        return limit(50 + 10 * (((Stats) router).getStat(Stats.Type.TACTICS)
                - ((Stats) pursuer).getStat(Stats.Type.TACTICS)));
    }

    /**
     * Determines if unit will successfully rout
     * 
     * @param router  unit attempting to rout
     * @param pursuer unit pursuing router
     * 
     * @return if router unit will rout
     */
    public boolean isRouted(Engageable router, Engageable pursuer) {
        if ((int) (Math.random() * 100) < calculateRoutChance(router, pursuer))
            return true;
        return false;
    }
}
