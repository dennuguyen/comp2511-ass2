/**
 * Wealth implementation of wealthable
 */

package unsw.gloriaromanus.economy;

public class Wealth implements Wealthable {

    int amount;
    int rate;

    /**
     * Default constructor
     */
    public Wealth() {
        this(0);
    }

    /**
     * Base constructor for Wealth using given starting amount for town wealth
     * 
     * @param amount starting amount of wealth
     */
    public Wealth(int amount) {
        this.amount = amount;
        this.rate = 0;
    }

    /**
     * Limit wealth values to be non-negative
     * 
     * @param value Value to limit
     * @return Limited value
     */
    private int limit(int value) {
        if (value < 0)
            value = 0;
        return value;
    }

    @Override
    public int getWealth() {
        return this.amount;
    }

    @Override
    public int getWealthGrowth() {
        return this.rate;
    }

    @Override
    public void setWealthGrowth(int rate) {
        this.rate = rate;
    }

    @Override
    public void addWealth(int amount) {
        this.amount += amount;
        this.amount = limit(this.amount);
    }

    @Override
    public void growWealth() {
        addWealth(rate);
    }

}
