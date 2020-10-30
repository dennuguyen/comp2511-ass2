package unsw.gloriaromanus;

import java.util.Random;
import unsw.gloriaromanus.component.Stats;

public class MissileEngagement implements EngagementType{

    public int calculateCasualties(Unit attacker, Unit defender, int initialDefenderSize){
        Random rand = new Random();
        double N = rand.nextGaussian();
        int divisor = defender.getStat(Stats.Type.ARMOUR) + defender.getStat(Stats.Type.SHIELD);
        float factor;
        if (divisor == 0) factor = 10;
        else factor = attacker.getStat(Stats.Type.FIRE)/divisor;
        int casualties = (int) (0.1 * initialDefenderSize * factor * (N + 1));
        return casualties;
    }

}
