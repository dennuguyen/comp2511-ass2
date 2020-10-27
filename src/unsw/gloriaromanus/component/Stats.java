/**
 * Unit stats class
 */

package unsw.gloriaromanus.component;

import java.util.HashMap;
import java.util.Map;

public class Stats implements Statable {

    public static enum Type {
        ARMOUR, // physical armour rating
        DISCIPLINE, // ability to defend - reduces casualties
        FIRE, // damage
        MORALE, // resistance to fleeing
        STRENGTH, // number of troops
        FLANKING, // range of unit
        SHIELD, // physical shield rating
        TACTICS, // ability to disengage
    };

    private Map<Stats.Type, Integer> stats;

    /**
     * Stats default constructor
     */
    public Stats() {
        this(0, 0, 0, 0, 0, 0, 0, 0);
    }

    /**
     * Stats constructor
     * 
     * @param armour     base armour
     * @param discipline base discipline
     * @param fire       base fire
     * @param morale     base morale
     * @param strength   base strength
     * @param flanking   base flanking
     * @param shield     base shield
     * @param tactics    base tactics
     */
    public Stats(int armour, int discipline, int fire, int morale, int strength, int flanking,
            int shield, int tactics) {
        this.stats = new HashMap<Stats.Type, Integer>();
        this.stats.put(Stats.Type.ARMOUR, armour);
        this.stats.put(Stats.Type.DISCIPLINE, discipline);
        this.stats.put(Stats.Type.FIRE, fire);
        this.stats.put(Stats.Type.MORALE, morale);
        this.stats.put(Stats.Type.STRENGTH, strength);
        this.stats.put(Stats.Type.FLANKING, flanking);
        this.stats.put(Stats.Type.SHIELD, shield);
        this.stats.put(Stats.Type.TACTICS, tactics);
    }

    /**
     * Stats copy constructor
     * 
     * @param stats
     */
    public Stats(Stats stats) {
        this.stats = stats.stats;
    }

    public int getStat(Stats.Type type) {
        return this.stats.get(type);
    }

    public void setStat(Stats.Type type, int value) {
        this.stats.put(type, value);
    }
}
