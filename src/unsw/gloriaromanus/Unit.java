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

public class Unit implements Entity, Moveable, Locable, Statable {

    private final Locale locale;
    private final Move move;
    private final Stats stats;

    public Unit() {
        this(null);
    }

    public Unit(String spawn) {
        this.locale = new Locale(spawn);
        this.move = new Move(Move.infantryMoveCapacity);
        this.stats = new Stats();
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
    public int getArmour() {
        return this.stats.getArmour();
    }

    @Override
    public void setArmour(int armour) {
        this.stats.setArmour(armour);
    }

    @Override
    public int getDiscipline() {
        return this.stats.getDiscipline();
    }

    @Override
    public void setDiscipline(int discipline) {
        this.setDiscipline(discipline);
    }

    @Override
    public int getFire() {
        return this.stats.getFire();
    }

    @Override
    public void setFire(int fire) {
        this.setFire(fire);
    }

    @Override
    public int getMorale() {
        return this.stats.getMorale();
    }

    @Override
    public void setMorale(int morale) {
        this.stats.setMorale(morale);
    }

    @Override
    public int getStrength() {
        return this.stats.getStrength();
    }

    @Override
    public void setStrength(int strength) {
        this.stats.setStrength(strength);
    }

    @Override
    public int getFlanking() {
        return this.stats.getFlanking();
    }

    @Override
    public void setFlanking(int flanking) {
        this.stats.setFlanking(flanking);
    }

    @Override
    public int getShield() {
        return this.stats.getShield();
    }

    @Override
    public void setShield(int shield) {
        this.stats.setShield(shield);
    }

    @Override
    public int getTactics() {
        return this.stats.getTactics();
    }

    @Override
    public void setTactics(int tactics) {
        this.stats.setTactics(tactics);
    }
}
