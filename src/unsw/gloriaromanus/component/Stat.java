/**
 * Stat abstract class
 */

package unsw.gloriaromanus.component;

public abstract class Stat {

    protected int MIN;
    protected int MAX;
    protected int baseValue;
    // protected int modifiedValue;

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
        this.baseValue = limit(baseValue);
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
        return this.baseValue;
    }

    /**
     * Set a stat to its new value
     * 
     * @param value New stat value
     */
    public void setStat(int value) {
        this.baseValue = limit(value);
    }
}
