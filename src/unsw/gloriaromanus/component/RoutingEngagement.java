package unsw.gloriaromanus.component;

public class RoutingEngagement extends Engagement {

    /**
     * Constructs a routing engagement between two units.
     * 
     * @param router  a routing unit
     * @param pursuer the pursuing unit
     * 
     * @param type    type of engagement
     */
    public RoutingEngagement(Engageable router, Engageable pursuer) {
        super(router, pursuer);
    }

    /**
     * Performs a rout engagement, i.e. router is attacked by pursuer and does not inflict
     * casualties
     */
    @Override
    public void doEngagement() {
        inflictDamage(super.getUnitA());
    }
}
