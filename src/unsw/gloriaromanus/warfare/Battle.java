/**
 * Battles consist of many skirmishes between pairs of units. Skirmishes consist of three phases:
 * Engagement , Breaking , Routing.
 */

package unsw.gloriaromanus.warfare;

import unsw.gloriaromanus.stats.Stats;
import unsw.gloriaromanus.unit.UnitComponent;
import unsw.gloriaromanus.unit.UnitComposite;
import unsw.gloriaromanus.unit.UnitLeaf;

public class Battle {

    private int numEngagements;
    private UnitComposite attacker;
    private UnitComposite defender;

    /**
     * Constructs a battle resolver
     * 
     * @param attacker army attacking the province
     * @param defender army defending the province
     */
    public Battle(UnitComposite attacker, UnitComposite defender) {
        this.numEngagements = 0;
        this.attacker = attacker;
        this.defender = defender;
    }

    /**
     * Returns a random unit from the attacking army
     * 
     * @return random attacking unit
     */
    private UnitLeaf chooseAttackUnit() {
        while (true) {
            UnitLeaf unit = (UnitLeaf) attacker.getRandomUnit();
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
    private UnitLeaf chooseDefenceUnit() {
        while (true) {
            UnitLeaf unit = (UnitLeaf) attacker.getRandomUnit();
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
    private boolean isDestroyed(UnitLeaf unit) {
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
    private void attemptBreak(Engagement e, UnitLeaf a, UnitLeaf b) {
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
    private void attemptFlee(UnitLeaf router, UnitLeaf pursuer) {

        Routing r = new Routing();

        if (isDestroyed(router)) {
            // remove router unit from game
            if (this.attacker.contains(router))
                this.attacker.removeUnit(router);
            else if (this.defender.contains(router))
                this.defender.removeUnit(router);
            else
                System.err.println("Unit not part of any army");
        } else if (!r.isRouted(router, pursuer)) {
            // remove router unit from battle

        } else {
            Engagement e = new RoutingEngagement(router, pursuer);
        }

    }

    private void doEngagementSequence() {
        UnitLeaf attackUnit = chooseAttackUnit();
        UnitLeaf defenceUnit = chooseDefenceUnit();
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
