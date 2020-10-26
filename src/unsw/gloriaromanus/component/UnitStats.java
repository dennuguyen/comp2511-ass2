/**
 * Unit stats class
 */

package unsw.gloriaromanus.component;

import unsw.gloriaromanus.util.Pair;

public class UnitStats {

    // Pairs of base and modified stats
    private Pair<Integer, Integer> armour; // physical armour rating
    private Pair<Integer, Integer> discipline; // ability to defend - reduces casualties
    private Pair<Integer, Integer> fire; // damage
    private Pair<Integer, Integer> morale; // resistance to fleeing
    private Pair<Integer, Integer> strength; // number of troops
    private Pair<Integer, Integer> flanking; // range of unit
    private Pair<Integer, Integer> shield; // physical shield rating
    private Pair<Integer, Integer> tactics; // ability to disengage

    public UnitStats() {
        this.armour = Pair.of(0, 0);
        this.discipline = Pair.of(0, 0);
        this.fire = Pair.of(0, 0);
        this.morale = Pair.of(0, 0);
        this.strength = Pair.of(0, 0);
        this.flanking = Pair.of(0, 0);
        this.shield = Pair.of(0, 0);
        this.tactics = Pair.of(0, 0);
    }
}
