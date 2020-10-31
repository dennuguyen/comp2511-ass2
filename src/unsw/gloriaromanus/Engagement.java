package unsw.gloriaromanus;

import unsw.gloriaromanus.component.Stats;

public abstract class Engagement {

    private Unit unitA;
    private int unitAInitialSize;
    private int unitACasualties;
    private Unit unitB;
    private int unitBInitialSize;
    private int unitBCasualties;
    private EngagementType type;

    /**
     * Constructs an engagement between two units. 
     * 
     * @param unitA a unit
     * @param unitB an opoosing unit
     * 
     * @param type type of engagement
     */
    public Engagement(Unit unitA, Unit unitB, EngagementType type) {
        this.unitA = unitA;
        unitAInitialSize = unitA.getStat(Stats.Type.STRENGTH);
        unitACasualties = 0;
        this.unitB = unitB;
        unitBInitialSize = unitB.getStat(Stats.Type.STRENGTH);
        unitBCasualties = 0;
        this.type = type;
    }

    /**
     * Returns unit A
     * 
     * @return unit A
     */
    public Unit getUnitA() {
        return unitA;
    }

    /**
     * Returns unit B
     * 
     * @return unit B
     */
    public Unit getUnitB() {
        return unitB;
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
     * Returns casualties of unit after engagement
     * 
     * @param unit unit to be checked
     * 
     * @return casualties of unit
     */
    public int getCasualties(Unit unit) {
        if (unit.equals(unitA)) return unitACasualties;
        if (unit.equals(unitB)) return unitBCasualties;
        return 0;
    }

    /**
     * Limits casualties between 0 and size of unit 
     * 
     * @param casualties calculated value of casualties
     * @param intial initial size of unit
     * 
     * @return modified casualties
     */
    public int limit(int casualties, int initial) {
        if (casualties > initial) 
            return initial;
        if (casualties < 0) 
            return 0;
        return casualties;
    }

    /**
     * Performs the inflicting of damage during engagement
     * 
     * @param current current attacking unit
     * 
     */
    public void inflictDamage(Unit current) {
        int casualties;
        if (current.equals(unitA)) {
            casualties = type.calculateCasualties(unitA, unitB, unitBInitialSize);
            casualties = limit(casualties, unitBInitialSize);
            unitBCasualties = casualties;
            unitB.addStat(Stats.Type.STRENGTH, -1 * casualties);
        } else {
            casualties = type.calculateCasualties(unitB, unitA, unitAInitialSize);
            casualties = limit(casualties, unitAInitialSize);
            unitACasualties = casualties;
            unitA.addStat(Stats.Type.STRENGTH, -1 * casualties);
        }
    }

    /**
     * Performs engagement between two units
     */
    public void doEngagement() {
    }

}