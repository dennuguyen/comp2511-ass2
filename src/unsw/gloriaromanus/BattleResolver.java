package unsw.gloriaromanus;

import java.lang.Math;

import unsw.gloriaromanus.component.Stats;

public class BattleResolver {
    Army attacker;
    Army defender;
    int numEngagements;
    Unit attackUnit;
    Unit defenceUnit;

    public BattleResolver(Army attacker, Army defender) {
        this.attacker = attacker;
        this.defender = defender;
        numEngagements = 0;
    }

    public void chooseAttackUnit() {
        attackUnit = attacker.getRandomUnit();
    }

    public void chooseDefenceUnit() {
        defenceUnit = defender.getRandomUnit();
    }

    Engagement determineMixedEngagement(Unit missile, Unit melee) {
        int chanceMelee = 50 + 10 * (melee.getStat(Stats.Type.TACTICS) - missile.getStat(Stats.Type.TACTICS));
        if (chanceMelee < 5) chanceMelee = 5;
        else if (chanceMelee > 95) chanceMelee = 95;
        var d = Math.random() * 100;
        if (d < chanceMelee)
            return melee;
        else
            return missile;
    }

    Engagement determineEngagementType() {
        if (attackUnit is melee && defenceUnit is melee) 
            return melee
        if (attackUnit is missile && defenceUnit is missile)
            return missile
        if (attackUnit is missile && defenceUnit is melee)
            return determineMixedEngagement(attackUnit, defenceUnit);
        if (attackUnit is melee && defenceUnit is missile)
            return determineMixedEngagement(defenceUnit, attackUnit);
    }

    
}
