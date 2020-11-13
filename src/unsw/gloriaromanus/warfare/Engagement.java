package unsw.gloriaromanus.warfare;

import unsw.gloriaromanus.stats.Stats;

public abstract class Engagement {

    private Engageable unitA;
    private int unitAInitialSize;
    private int unitACasualties;

    private Engageable unitB;
    private int unitBInitialSize;
    private int unitBCasualties;

    private EngagementType type;

    /**
     * Constructs an engagement between two units.
     * 
     * @param unitA a unit
     * @param unitB an opoosing unit
     * 
     * @param type  type of engagement
     */
    public Engagement(Engageable unitA, Engageable unitB) {
        this.unitA = unitA;
        unitAInitialSize = ((Stats) unitA).getStat(Stats.Type.STRENGTH);
        unitACasualties = 0;

        this.unitB = unitB;
        unitBInitialSize = ((Stats) unitB).getStat(Stats.Type.STRENGTH);
        unitBCasualties = 0;

        this.type = determineEngagementType(unitA, unitB);
    }

    /**
     * Returns unit A
     * 
     * @return unit A
     */
    public Engageable getUnitA() {
        return unitA;
    }

    /**
     * Returns unit B
     * 
     * @return unit B
     */
    public Engageable getUnitB() {
        return unitB;
    }

    /**
     * Determines the engagement type between a missile and melee unit
     * 
     * @param missile missile unit involved in engagement
     * @param melee   melee unit involved in engagement
     * 
     * @return type of engagement
     */
    private EngagementType determineMixedEngagement(Engageable missile, Engageable melee) {
        int chanceMelee = 50 + 10 * (((Stats) melee).getStat(Stats.Type.TACTICS)
                - ((Stats) missile).getStat(Stats.Type.TACTICS));
        if (chanceMelee < 5)
            chanceMelee = 5;
        else if (chanceMelee > 95)
            chanceMelee = 95;
        var d = Math.random() * 100;
        if (d < chanceMelee)
            return new MeleeEngagement();
        else
            return new MissileEngagement();
    }

    /**
     * Determines the engagement type between an attacking and defending unit
     * 
     * @param attackUnit  attack unit involved in engagement
     * @param defenceUnit defence unit involved in engagement
     * 
     * @return type of engagement
     */
    public EngagementType determineEngagementType(Engageable a, Engageable b) {

        if (a.getEngageType() == Engageable.Type.Melee
                && b.getEngageType() == Engageable.Type.Melee)
            return new MeleeEngagement();

        if (a.getEngageType() == Engageable.Type.Missile
                && b.getEngageType() == Engageable.Type.Missile)
            return new MissileEngagement();

        if (a.getEngageType() == Engageable.Type.Missile
                && b.getEngageType() == Engageable.Type.Melee)
            return determineMixedEngagement(a, b);

        if (a.getEngageType() == Engageable.Type.Melee
                && b.getEngageType() == Engageable.Type.Missile)
            return determineMixedEngagement(b, a);

        System.err.println("Invalid engagement combination");
        return null;
    }

    /**
     * Returns intial size of units before engagement
     * 
     * @return unit size before engagement
     */
    public int getInitialUnitSize(Engageable unit) {
        if (unit.equals(unitA))
            return unitAInitialSize;
        if (unit.equals(unitB))
            return unitBInitialSize;
        return 0;
    }

    /**
     * Returns casualties of unit after engagement
     * 
     * @param unit unit to be checked
     * 
     * @return casualties of unit
     */
    public int getCasualties(Engageable unit) {
        if (unit.equals(unitA))
            return unitACasualties;
        if (unit.equals(unitB))
            return unitBCasualties;
        return 0;
    }

    /**
     * Limits casualties between 0 and size of unit
     * 
     * @param casualties calculated value of casualties
     * @param intial     initial size of unit
     * 
     * @return modified casualties
     */
    private int limit(int casualties, int initial) {
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
    public void inflictDamage(Engageable current) {
        int casualties;
        if (current.equals(unitA)) {
            casualties = type.calculateCasualties(unitA, unitB, unitBInitialSize);
            casualties = limit(casualties, unitBInitialSize);
            unitBCasualties = casualties;
            ((Stats) unitB).addStat(Stats.Type.STRENGTH, -1 * casualties);
        } else {
            casualties = type.calculateCasualties(unitB, unitA, unitAInitialSize);
            casualties = limit(casualties, unitAInitialSize);
            unitACasualties = casualties;
            ((Stats) unitA).addStat(Stats.Type.STRENGTH, -1 * casualties);
        }
    }

    /**
     * Performs engagement between two units
     */
    public void doEngagement() {
    }

}
