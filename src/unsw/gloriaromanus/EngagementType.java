package unsw.gloriaromanus;

public interface EngagementType {

    /**
     * Calculate casualties caused by engagement
     * 
     * @return number of casualties
     */
    public int calculateCasualties(Unit attacker, Unit defender, int initialDefenderSize);

}
