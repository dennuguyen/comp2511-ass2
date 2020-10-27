/**
 * Unit stats class
 */

package unsw.gloriaromanus.component;

import unsw.gloriaromanus.util.Pair;

public class Stats implements Statable {

    // Pairs of base and modified stats
    private Pair<Integer, Integer> armour; // physical armour rating
    private Pair<Integer, Integer> discipline; // ability to defend - reduces casualties
    private Pair<Integer, Integer> fire; // damage
    private Pair<Integer, Integer> morale; // resistance to fleeing
    private Pair<Integer, Integer> strength; // number of troops
    private Pair<Integer, Integer> flanking; // range of unit
    private Pair<Integer, Integer> shield; // physical shield rating
    private Pair<Integer, Integer> tactics; // ability to disengage

    public Stats() {
        this(0, 0, 0, 0, 0, 0, 0, 0);
    }

    public Stats(int armour, int discipline, int fire, int morale, int strength, int flanking,
            int shield, int tactics) {
        this.armour = Pair.of(armour, armour);
        this.discipline = Pair.of(discipline, discipline);
        this.fire = Pair.of(fire, fire);
        this.morale = Pair.of(morale, morale);
        this.strength = Pair.of(strength, strength);
        this.flanking = Pair.of(flanking, flanking);
        this.shield = Pair.of(shield, shield);
        this.tactics = Pair.of(tactics, tactics);
    }

    public int getArmour() {
        return this.armour.first;
    }

    public void setArmour(int armour) {
        this.armour.first = armour;
    }

    public int getDiscipline() {
        return this.discipline.first;
    }

    public void setDiscipline(int discipline) {
        this.discipline.first = discipline;
    }

    public int getFire() {
        return this.fire.first;
    }

    public void setFire(int fire) {
        this.fire.first = fire;
    }

    public int getMorale() {
        return this.morale.first;
    }

    public void setMorale(int morale) {
        this.morale.first = morale;
    }

    public int getStrength() {
        return this.strength.first;
    }

    public void setStrength(int strength) {
        this.strength.first = strength;
    }

    public int getFlanking() {
        return this.flanking.first;
    }

    public void setFlanking(int flanking) {
        this.flanking.first = flanking;
    }

    public int getShield() {
        return this.shield.first;
    }

    public void setShield(int shield) {
        this.shield.first = shield;
    }

    public int getTactics() {
        return this.tactics.first;
    }

    public void setTactics(int tactics) {
        this.tactics.first = tactics;
    }
}
