/**
 * Wealth implementation of wealthable
 */

package unsw.gloriaromanus.component;

import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.Subject;

public class Wealth implements Wealthable, Turnable, Observer {

    int amount;
    int rate;

    /**
     * Simple constructor
     */
    public Wealth() {
        this.amount = 0;
        this.rate = 0;
    }

    /**
     * Constructor using given starting amount for town wealth
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
    public void addWealth(int amount) {
        this.amount += amount;
        this.amount = limit(this.amount);
    }

    @Override
    public void addWealthGrowth(int rate) {
        this.rate += rate;
        this.rate = limit(this.rate);
    }

    @Override
    public void nextTurn() {
        this.amount += this.rate; // generate wealth on a turnly basis
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Turn) // next turn notified
            this.nextTurn();
        if (subject instanceof Tax) // observed changed in tax level
            this.addWealthGrowth(((Tax) subject).getWealthGrowthChange());
    }
}
