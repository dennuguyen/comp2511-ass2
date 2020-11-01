/**
 * Battles consist of many skirmishes between pairs of units. Skirmishes consist of three phases:
 * Engagement , Breaking , Routing.
 */

package unsw.gloriaromanus;

import unsw.gloriaromanus.component.Breaking;
import unsw.gloriaromanus.component.Engagement;
import unsw.gloriaromanus.component.NormalEngagement;
import unsw.gloriaromanus.component.Routing;
import unsw.gloriaromanus.component.RoutingEngagement;
import unsw.gloriaromanus.component.Stats;

public class Battle {

    private int numEngagements;
    private Army attacker;
    private Army defender;

    /**
     * Constructs a battle resolver
     * 
     * @param attacker army attacking the province
     * @param defender army defending the province
     */
    public Battle(Army attacker, Army defender) {
        this.numEngagements = 0;
        this.attacker = attacker;
        this.defender = defender;
    }

    /**
     * Returns a random unit from the attacking army
     * 
     * @return random attacking unit
     */
    private Unit chooseAttackUnit() {
        while (true) {
            Unit unit = attacker.getRandomUnit();
            if (unit.getStat(Stats.Type.MORALE) > 0)
                return unit;
            else
                continue;
        }
    }

    /**
     * Returns a random unit from the defending army
     * 
     * @return random defending unit
     */
    private Unit chooseDefenceUnit() {
        while (true) {
            Unit unit = attacker.getRandomUnit();
            if (unit.getStat(Stats.Type.MORALE) > 0)
                return unit;
            else
                continue;
        }
    }

    /**
     * Checks if a unit was destroyed
     * 
     * @param unit unit to be checked
     * 
     * @return if unit was destroyed
     */
    private boolean isDestroyed(Unit unit) {
        if (unit.getStat(Stats.Type.STRENGTH) <= 0)
            return true;
        return false;
    }

    /**
     * Performs a break attempt for two units
     * 
     * @param e engagement before break attempt is made
     * @param a a unit involved in engagement
     * @param b opposing unit involved in engagement
     */
    private void attemptBreak(Engagement e, Unit a, Unit b) {
        Breaking breaking = new Breaking();
        boolean aBroken = breaking.isBroken(e, a, b);
        boolean bBroken = breaking.isBroken(e, b, a);

        if (aBroken && bBroken) {
            // end engagement sequence, remove both units from battle
        } else if (aBroken) {
            attemptFlee(a, b);
        } else if (bBroken) {
            attemptFlee(b, a);
        } else {
            // do nothing, continue to next engagement
        }
    }

    /**
     * Peforms repeated rout attempts until router is destroyed or routs
     * 
     * @param router  routing unit
     * @param pursuer pursuing unit
     */
    private void attemptFlee(Unit router, Unit pursuer) {

        Routing r = new Routing();

        if (isDestroyed(router)) {
            // remove router unit from game
            if (this.attacker.contains(router))
                this.attacker.remove(router);
            else if (this.defender.contains(router))
                this.defender.remove(router);
            else
                System.err.println("Unit not part of any army");
        } else if (!r.isRouted(router, pursuer)) {
            // remove router unit from battle

        } else {
            Engagement e = new RoutingEngagement(router, pursuer);
        }

    }

    private void doEngagementSequence() {
        Unit attackUnit = chooseAttackUnit();
        Unit defenceUnit = chooseDefenceUnit();
        while (!isDestroyed(attackUnit) && !isDestroyed(defenceUnit)) {
            Engagement e = new NormalEngagement(attackUnit, defenceUnit);
            attemptBreak(e, attackUnit, defenceUnit);
        }
    }

    public void doBattle() {
        while (attacker.getNumUnits() > 0 && defender.getNumUnits() > 0) {
            doEngagementSequence();
        }
    }
}
