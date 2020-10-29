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
        TACTICS; // ability to disengage
    };

    private Map<Stats.Type, Stat> stats;

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
     * @param flanking   base flanking
     * @param morale     base morale
     * @param shield     base shield
     * @param strength   base strength
     * @param tactics    base tactics
     */
    public Stats(int armour, int discipline, int fire, int flanking, int morale, int shield,
            int strength, int tactics) {
        this.stats = new HashMap<Stats.Type, Stat>();
        this.stats.put(Stats.Type.ARMOUR, new Armour(armour));
        this.stats.put(Stats.Type.DISCIPLINE, new Discipline(discipline));
        this.stats.put(Stats.Type.FIRE, new Fire(fire));
        this.stats.put(Stats.Type.FLANKING, new Flanking(flanking));
        this.stats.put(Stats.Type.MORALE, new Morale(morale));
        this.stats.put(Stats.Type.SHIELD, new Shield(shield));
        this.stats.put(Stats.Type.STRENGTH, new Strength(strength));
        this.stats.put(Stats.Type.TACTICS, new Tactics(tactics));
    }

    /**
     * Stats copy constructor
     * 
     * @param stats
     */
    public Stats(Stats stats) {
        this.stats = new HashMap<Stats.Type, Stat>(stats.stats);
    }

    @Override
    public int getStat(Stats.Type type) {
        return this.stats.get(type).getStat();
    }

    @Override
    public void setStat(Stats.Type type, int value) {
        this.stats.get(type).setStat(value);
    }
}
