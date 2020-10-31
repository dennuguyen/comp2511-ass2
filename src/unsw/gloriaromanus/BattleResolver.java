package unsw.gloriaromanus;

import java.lang.Math;

import unsw.gloriaromanus.component.Stats;
import unsw.gloriaromanus.Break;

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

    /**
     * Checks if a unit was destroyed
     * 
     */
    public boolean isDestroyed(Unit a) {
        if (a.getStat(Stats.Type.STRENGTH) <= 0) return true;
        return false;
    }

    public void attemptBreak(Engagement e, Unit a, Unit b) {
        Break Break = new Break();
        boolean aBroken = Break.isBroken(e, a, b);
        boolean bBroken = Break.isBroken(e, b, a);
        
        if (aBroken && bBroken) {
            //end engagement sequence, remove units from battle
        }
        else if (aBroken) {
            attemptFlee(a, b);
        }
        else if (bBroken) {
            attemptFlee(b, a);
        }
        else {
            //do nothing, continue to next engagement
        }
    }

    public void attemptFlee(Unit router, Unit pursuer) {
        EngagementType type = determineEngagementType(router, pursuer);
        Rout r = new Rout();
        while (!r.isRouted(router, pursuer) || !isDestroyed(router)) {
            Engagement e = new RoutingEngagement(router, pursuer, type);
        }
        if(isDestroyed(router)) //remove unit from game
        else // remove unit from battle
        
    }

    public void doEngagementSequence() {
        Unit attackUnit = chooseAttackUnit();
        Unit defenceUnit = chooseDefenceUnit();
        EngagementType type = determineEngagementType(attackUnit, defenceUnit);
        while (!isDestroyed(attackUnit) && !isDestroyed(defenceUnit)) {
            Engagement e = new NormalEngagement(attackUnit, defenceUnit, type);
            attemptBreak(e, attackUnit, defenceUnit);
        }
    }
}
