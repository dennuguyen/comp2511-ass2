package unsw.gloriaromanus;

import java.lang.Math;

import unsw.gloriaromanus.component.Stats;
import unsw.gloriaromanus.Break;

public class BattleResolver {

    private int numEngagements;
    private Army attacker;
    private Army defender;

    /**
     * Constructs a battle resolver
     * 
     * @param attacker army attacking the province
     * @param defender army defending the province
     */
    public BattleResolver(Army attacker, Army defender) {
        this.attacker = attacker;
        this.defender = defender;
        numEngagements = 0;
    }

    /**
     * Returns a random unit from the attacking army
     * 
     * @return random attacking unit
     */
    public Unit chooseAttackUnit() {
        return attacker.getRandomUnit();
    }

    /**
     * Returns a random unit from the defending army
     * 
     * @return random defending unit
     */
    public Unit chooseDefenceUnit() {
        return defender.getRandomUnit();
    }

    /**
     * Checks if a unit was destroyed
     * 
     * @param unit unit to be checked
     * 
     * @return if unit was destroyed
     */
    public boolean isDestroyed(Unit unit) {
        if (unit.getStat(Stats.Type.STRENGTH) <= 0) return true;
        return false;
    }

    /**
     * Performs a break attempt for two units
     * 
     * @param e engagement before break attempt is made
     * @param a a unit involved in engagement
     * @param b opposing unit involved in engagement
     */
    public void attemptBreak(Engagement e, Unit a, Unit b) {
        Break break = new Break();
        boolean aBroken = break.isBroken(e, a, b);
        boolean bBroken = break.isBroken(e, b, a);
        
        if (aBroken && bBroken) {
            //end engagement sequence, remove both units from battle
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

    /**
     * Peforms repeated rout attempts until router is destroyed or routs
     * 
     * @param router routing unit
     * @param pursuer pursuing unit
     */
    public void attemptFlee(Unit router, Unit pursuer) {
        Rout r = new Rout();
        while (!r.isRouted(router, pursuer) || !isDestroyed(router)) {
            Engagement e = new RoutingEngagement(router, pursuer);
        }
        if(isDestroyed(router)) //remove router unit from game
        else // remove router unit from battle
        
    }

    public void doEngagementSequence() {
        Unit attackUnit = chooseAttackUnit();
        Unit defenceUnit = chooseDefenceUnit();
        while (!isDestroyed(attackUnit) && !isDestroyed(defenceUnit)) {
            Engagement e = new NormalEngagement(attackUnit, defenceUnit);
            attemptBreak(e, attackUnit, defenceUnit);
        }
    }

    public void doBattle() {
        while (attacker.getNumUnits() > 0 && attacker.getNumUnits() > 0) {
            doEngagementSequence();
        }
    }
}
