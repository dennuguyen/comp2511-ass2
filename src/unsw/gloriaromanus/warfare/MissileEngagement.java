package unsw.gloriaromanus.warfare;

import java.util.Random;
import unsw.gloriaromanus.stats.Stats;

public class MissileEngagement implements EngagementType {

    public int calculateCasualties(Engageable attacker, Engageable defender,
            int initialDefenderSize) {
        Random rand = new Random();
        double N = rand.nextGaussian();
        int divisor = ((Stats) defender).getStat(Stats.Type.ARMOUR)
                + ((Stats) defender).getStat(Stats.Type.SHIELD);
        float factor;
        if (divisor == 0)
            factor = 10;
        else
            factor = ((Stats) attacker).getStat(Stats.Type.FIRE) / divisor;
        int casualties = (int) (0.1 * initialDefenderSize * factor * (N + 1));
        return casualties;
    }

}
