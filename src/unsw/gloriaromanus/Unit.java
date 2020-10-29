/**
 * Unit class
 */

package unsw.gloriaromanus;

import unsw.gloriaromanus.component.Locable;
import unsw.gloriaromanus.component.Locale;
import unsw.gloriaromanus.component.Move;
import unsw.gloriaromanus.component.Moveable;
import unsw.gloriaromanus.component.Statable;
import unsw.gloriaromanus.component.Stats;
import unsw.gloriaromanus.util.ArmyComponent;

public class Unit implements Entity, Locable, Moveable, Statable, ArmyComponent {

    private final Locale locale;
    private final Move move;
    private final Stats stats;

    /**
     * Unit constructor
     * 
     * @param spawn        Initial location
     * @param movementType Unit's movement type
     * @param stats        Base stats
     */
    public Unit(String spawn, Move.Type movementType, Stats stats) {
        this.locale = new Locale(spawn);
        this.move = new Move(movementType);
        this.stats = new Stats(stats);
    }

    @Override
    public String getLocation() {
        return this.locale.getLocation();
    }

    @Override
    public String setLocation(String location) {
        return this.locale.setLocation(location);
    }

    @Override
    public String moveTo(String destination) {
        return this.locale.setLocation(this.move.moveTo(this.locale.getLocation(), destination));
    }

    @Override
    public int getStat(Stats.Type type) {
        return this.stats.getStat(type);
    }

    @Override
    public void setStat(Stats.Type type, int value) {
        this.stats.setStat(type, value);
    }

    @Override
    public void addToStat(Stats.Type type, int change) {
        this.stats.addToStat(type, change);
    }

    @Override
    public void multiplyStat(Stats.Type type, int change) {
        this.stats.multiplyStat(type, change);
    }
}
