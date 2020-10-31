package unsw.gloriaromanus;

public class RoutingEngagement extends Engagement {

    /**
     * Constructs a routing engagement between two units. 
     * 
     * @param router a routing unit
     * @param pursuer the pursuing unit
     * 
     * @param type type of engagement
     */
    public RoutingEngagement(Unit router, Unit pursuer, EngagementType type) {
        super(router, pursuer, type);
    }

    @Override
    public void doEngagement() {
        inflictDamage(super.getUnitA());
    }
}
    