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
        amount = 0;
        rate = 0;
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

    private int limit(int value) {
        if (value < 0)
            value = 0;
        return value;
    }

    @Override
    public int getWealth() {
        return amount;
    }

    @Override
    public int getWealthGrowth() {
        return rate;
    }

    @Override
    public void subtractWealth(int amount) {
        this.amount -= amount;
        this.amount = limit(this.amount);
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
        this.amount += this.rate;
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Turn)
            this.nextTurn();
    }
}
