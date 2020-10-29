/**
 * Stat abstract class
 */

package unsw.gloriaromanus.component;

import java.util.ArrayList;
import unsw.gloriaromanus.util.Pair;

public abstract class Stat {

    private enum ModType {
        SCALAR, MULTIPLY;
    }

    protected int MIN;
    protected int MAX;
    protected int baseValue;
    protected ArrayList<Pair<Integer, ModType>> modifiedValues;

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

        int value = this.baseValue;

        // Apply multipliers first
        for (Pair<Integer, ModType> modifiedValue : this.modifiedValues)
            if (modifiedValue.second == ModType.MULTIPLY)
                value = limit(value * modifiedValue.first / 100);

        // Apply scalar modifiers second
        for (Pair<Integer, ModType> modifiedValue : this.modifiedValues)
            if (modifiedValue.second == ModType.SCALAR)
                value = limit(value + modifiedValue.first);

        return value;
    }

    /**
     * Set a stat to its new value
     * 
     * @param value New stat value
     */
    public void setStat(int value) {
        this.baseValue = limit(value);
    }

    /**
     * Add a specific value to a stat
     * 
     * @param type   Type of stat
     * @param change value of stat addition
     */
    public void addStat(int change) {
        this.modifiedValues.add(Pair.of(change, ModType.SCALAR));
    }

    /**
     * Multiply a stat by a specific value
     * 
     * @param type   Type of stat
     * @param change value of stat multiplier
     */
    public void multiplyStat(int change) {
        this.modifiedValues.add(Pair.of(change, ModType.MULTIPLY));
    }
}
