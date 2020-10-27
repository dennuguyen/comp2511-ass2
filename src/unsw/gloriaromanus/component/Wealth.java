package unsw.gloriaromanus.component;

import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.Subject;

public class Wealth implements Wealthable, Observer {
    
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
     * @param amount starting amount of wealth
     */
    public Wealth(int amount) {
        this.amount = amount;
        this.rate = 0;
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
    }

    @Override
    public void addWealth(int amount) {
        this.amount += amount;
    }

    @Override
    public void addWealthGrowth(int rate) {
        this.rate += rate;
    }

    @Override
    public void update(Subject obj) {
        if(obj instanceof Tax) {
            int rate = ((Tax)obj).getWealthGrowthChange();
            addWealthGrowth(rate);
        }
    }
}
