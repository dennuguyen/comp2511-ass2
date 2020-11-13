/**
 * Stat abstract class
 */

package unsw.gloriaromanus.stats;

public abstract class Stat {

    protected int MIN;
    protected int MAX;
    protected int baseValue;
    protected int modifiedValue;

    /**
     * Stat constructor
     * 
     * @param baseValue
     */
    public Stat(int baseValue) {
        this(baseValue, 0, 100);
    }

    /**
     * Stat constructor
     * 
     * @param baseValue Initial stat value
     * @param MIN       Min allowable value
     * @param MAX       Max allowable value
     */
    public Stat(int baseValue, int MIN, int MAX) {
        this.MIN = MIN;
        this.MAX = MAX;
        this.baseValue = this.modifiedValue = limit(baseValue);
    }

    /**
     * Reset the stat
     */
    public void resetStat() {
        this.modifiedValue = this.baseValue;
    }

    /**
     * Hard limiter on stat value
     * 
     * @param value Value to check
     * @return Limited value
     */
    private int limit(int value) {
        if (value < MIN)
            value = MIN;
        if (value > MAX)
            value = MAX;
        return value;
    }

    /**
     * Gets the stat
     * 
     * @return Value of stat
     */
    public int getStat() {
        return this.modifiedValue;
    }

    /**
     * Set a stat to its new value
     * 
     * @param value New stat value
     */
    public void setStat(int value) {
        this.modifiedValue = limit(value);
    }

    /**
     * Add a specific value to a stat
     * 
     * @param type   Type of stat
     * @param change value of stat addition
     */
    public void addStat(int change) {
        this.modifiedValue = limit(this.modifiedValue + change);
    }

    /**
     * Multiply a stat by a specific value
     * 
     * @param type       Type of stat
     * @param percentage Stat multiplier percentage
     */
    public void multiplyStat(int percentage) {
        this.modifiedValue =
                limit((int) (this.modifiedValue + this.modifiedValue * percentage / 100));
    }
}
