package unsw.gloriaromanus;

public interface EngagementType {

    /**
     * Calculates casualties caused by engagement
     * 
     * @param attacker current attacking unit
     * @param defender current defending unit
     * @param initialDefenderSize intial size of defender
     * 
     * @return number of casualties
     */
    public int calculateCasualties(Unit attacker, Unit defender, int initialDefenderSize);

}
