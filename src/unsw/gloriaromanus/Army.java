package unsw.gloriaromanus;

import java.util.ArrayList;
import java.util.Random;


public class Army implements Entity {

    private static final long serialVersionUID = 6503933741838584775L;
    private ArrayList<Unit> army;

    public Army() {
        army = new ArrayList<Unit>();
    }

    /**
     * Returns number of units in the army
     * 
     * @return size of army
     */
    public int getNumUnits() {
        return army.size();
    }

    /**
     * Returns a random unit from the army
     * 
     * @return random unit
     */
    public Unit getRandomUnit() {
        Random rand = new Random();
        int index = rand.nextInt(getNumUnits());
        return army.get(index);
    }

    /**
     * Checks if a unit is in the army
     */
    public boolean contains(Unit unit) {
        for (Unit u : army) {
            if (u.equals(unit))
                return true;
        }
        return false;
    }

    /**
     * Adds a unit to the army
     * 
     * @param unit unit to be added
     */
    public void add(Unit unit) {
        army.add(unit);
    }

    /**
     * Removes a unit from the army
     * 
     * @param unit unit to be removed
     */
    public void remove(Unit unit) {
        army.remove(unit);
    }

    /*
     * PSEUDOCODE public int countUnits (Class class) { int count = 0; for (Unit u : army) { if (u
     * instanceof class) count++; } return count; }
     * 
     * public void druidBonus() { int druidCount = countUnits(Druid); int bonus = (druidCount > 5) ?
     * 50 : druidCount * 10; for (Unit u : army) { u.multiplyStat(Stats.Type.MORALE, bonus); } }
     */
}
