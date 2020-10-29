/**
 * Statable interface
 */

package unsw.gloriaromanus.component;

public interface Statable {

    /**
     * Gets the stat specified by type
     * 
     * @param type Type of stat
     * @return Value of stat
     */
    public int getStat(Stats.Type type);

    /**
     * Set a stat to its new value
     * 
     * @param type  Type of stat
     * @param value New value of stat
     */
    public void setStat(Stats.Type type, int value);

    /**
     * Change a stat by a specific value
     * 
     * @param type  Type of stat
     * @param change value of stat change
     */
    public void addToStat(Stats.Type type, int change);

    /**
     * Multiply a stat by a specific value
     * 
     * @param type  Type of stat
     * @param change value of stat multiplier
     */
    public void multiplyStat(Stats.Type type, int change);
}
