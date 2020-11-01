package unsw.gloriaromanus.component;

public interface EngagementType {

    /**
     * Calculates casualties caused by engagement
     * 
     * @param attacker            current attacking unit
     * @param defender            current defending unit
     * @param initialDefenderSize intial size of defender
     * 
     * @return number of casualties
     */
    public int calculateCasualties(Engageable attacker, Engageable defender,
            int initialDefenderSize);
}
