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

public class Unit implements Entity, Locable, Moveable, Statable {

    private final Locale locale;
    private final Move move;
    private final Stats stats;

    public Unit(String spawn) {
        this(spawn, new Stats());
    }

    public Unit(String spawn, Stats stats) {
        this.locale = new Locale(spawn);
        this.move = new Move(Move.infantryMoveCapacity);
        this.stats = stats;
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
        return this.locale
                .setLocation(this.move.moveToImple(this.locale.getLocation(), destination));
    }

    @Override
    public int getStat(Stats.Type type) {
        return this.stats.getStat(type);
    }

    @Override
    public void setStat(Stats.Type type, int value) {
        this.stats.setStat(type, value);
    }
}
