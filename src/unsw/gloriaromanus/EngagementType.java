package unsw.gloriaromanus;

public interface EngagementType {

    /**
     * Calculates casualties caused by engagement
     * 
     * @return number of casualties
     */
    public int calculateCasualties(Unit attacker, Unit defender, int initialDefenderSize);

}
