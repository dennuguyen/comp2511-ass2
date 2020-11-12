package unsw.gloriaromanus.warfare;

import java.util.Random;
import unsw.gloriaromanus.stats.Stats;

public class MeleeEngagement implements EngagementType {

    public int calculateCasualties(Engageable attacker, Engageable defender,
            int initialDefenderSize) {
        Random rand = new Random();
        double N = rand.nextGaussian();
        int casualties =
                (int) (0.1 * initialDefenderSize * ((Stats) attacker).getStat(Stats.Type.FIRE)
                        / (((Stats) defender).getStat(Stats.Type.ARMOUR)
                                + ((Stats) defender).getStat(Stats.Type.SHIELD)
                                + ((Stats) defender).getStat(Stats.Type.DISCIPLINE))
                        * (N + 1));
        return casualties;
    }
}
