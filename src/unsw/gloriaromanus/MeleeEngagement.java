package unsw.gloriaromanus;

import java.util.Random;
import unsw.gloriaromanus.component.Stats;

public class MeleeEngagement implements EngagementType {

    public int calculateCasualties(Unit attacker, Unit defender, int initialDefenderSize){
        Random rand = new Random();
        double N = rand.nextGaussian();
        int casualties = (int) (0.1 * initialDefenderSize * attacker.getStat(Stats.Type.FIRE)
                        /(defender.getStat(Stats.Type.ARMOUR) + defender.getStat(Stats.Type.SHIELD) + defender.getStat(Stats.Type.DISCIPLINE))
                        * (N + 1));
        return casualties;
    }
}

