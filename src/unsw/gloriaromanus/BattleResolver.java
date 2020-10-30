package unsw.gloriaromanus;

import java.lang.Math;

import unsw.gloriaromanus.component.Stats;

public class BattleResolver {

    private int numEngagements;
    private Army attacker;
    private Army defender;

    public BattleResolver(Army attacker, Army defender) {
        this.attacker = attacker;
        this.defender = defender;
        numEngagements = 0;
    }

    public Unit chooseAttackUnit() {
        return attacker.getRandomUnit();
    }

    public Unit chooseDefenceUnit() {
        return defender.getRandomUnit();
    }

    public EngagementType determineMixedEngagement(Unit missile, Unit melee) {
        int chanceMelee = 50 + 10 * (melee.getStat(Stats.Type.TACTICS) - missile.getStat(Stats.Type.TACTICS));
        if (chanceMelee < 5) chanceMelee = 5;
        else if (chanceMelee > 95) chanceMelee = 95;
        var d = Math.random() * 100;
        if (d < chanceMelee)
            return new MeleeEngagement();
        else
            return new MissileEngagement();
    }

    public EngagementType determineEngagementType(Unit attackUnit, Unit defenceUnit) {
        if (attackUnit is melee && defenceUnit is melee) 
            return new MeleeEngagement();
        if (attackUnit is missile && defenceUnit is missile)
            return new MissileEngagement();
        if (attackUnit is missile && defenceUnit is melee)
            return determineMixedEngagement();
        if (attackUnit is melee && defenceUnit is missile)
            return determineMixedEngagement();
    }

    public void doBattle() {
        Unit attackUnit = chooseAttackUnit();
        Unit defenceUnit = chooseDefenceUnit();
        EngagementType type = determineEngagementType(attackUnit, defenceUnit);
        
    }



    
}
