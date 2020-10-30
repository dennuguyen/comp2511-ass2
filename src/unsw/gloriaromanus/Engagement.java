package unsw.gloriaromanus;

import java.util.List;
import java.util.ArrayList;
import unsw.gloriaromanus.component.Stats;

public class Engagement {

    private Unit unitA;
    private int unitAInitialSize;
    private Unit unitB;
    private int unitBInitialSize;
    private EngagementType type;

    /**
     * Constructs an engagement between two units 
     * 
     * @param unitA a unit
     * @param unitB an opoosing unit
     * 
     * @param type type of engagement
     */
    public Engagement(Unit unitA, Unit unitB, EngagementType type) {
        this.unitA = unitA;
        unitAInitialSize = unitA.getStat(Stats.Type.STRENGTH);
        this.unitB = unitB;
        unitBInitialSize = unitB.getStat(Stats.Type.STRENGTH);
        this.type = type;
    }

    /**
     * Returns units involved in engagement
     * 
     * @return units in engagement
     */
    public List<Unit> getUnits() {
        ArrayList<Unit> units = new ArrayList<Unit>();
        units.add(unitA);
        units.add(unitB);
        return units;
    }

    /**
     * Returns intial size of units before engagement
     * 
     * @return unit size before engagement
     */
    public int getInitialUnitSize(Unit unit) {
        if (unit.equals(unitA)) return unitAInitialSize;
        if (unit.equals(unitB)) return unitBInitialSize;
        return 0;
    }

    /**
     * Limit casualties between 0 and size of unit 
     * 
     * @return modified casualties
     */
    public int limitCasualties(int casualties, Unit enemy) {
        if (casualties > enemy.getStat(Stats.Type.STRENGTH)) 
            return enemy.getStat(Stats.Type.STRENGTH);
        if (casualties < 0) 
            return 0;
        return casualties;
    }

    /**
     * Performs the engagement
     * 
     */
    public void doEngagement() {
        int casualties;
        
        casualties = type.calculateCasualties(unitA, unitB, unitBInitialSize);
        casualties = limitCasualties(casualties, unitB);
        unitB.addStat(Stats.Type.STRENGTH, -1 * casualties);
        
        casualties = type.calculateCasualties(unitB, unitA, unitAInitialSize);
        casualties = limitCasualties(casualties, unitA);
        unitA.addStat(Stats.Type.STRENGTH, -1 * casualties);
    }

    /**
     * Checks if either unit was destroyed
     * 
     */
    public boolean checkDestroyed() {
        boolean destroyed = false;
        if (unitA.getStat(Stats.Type.STRENGTH) == 0) 
            //remove unit from army
            destroyed = true;
        if (unitB.getStat(Stats.Type.STRENGTH) == 0)
            //remove unit from army 
            destroyed = true;
        return destroyed;
    }
}
