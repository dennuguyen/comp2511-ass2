/**
 * Stats interface
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
     * @param value New Value of stat
     */
    public void setStat(Stats.Type type, int value);
}
