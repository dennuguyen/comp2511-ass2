package unsw.gloriaromanus;

public class NormalEngagement extends Engagement{


    /**
     * Constructs a normal engagement
     * 
     * @param unitA
     * @param unitB
     * 
     * @param type
     */
    public NormalEngagement(Unit unitA, Unit unitB) {
        super(unitA, unitB);
    }

    /**
     * Performs a normal engagement, i.e. both units attack one another
     */
    @Override
    public void doEngagement() {
        inflictDamage(super.getUnitA());
        inflictDamage(super.getUnitB());
    }

}
