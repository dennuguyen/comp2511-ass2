package unsw.gloriaromanus.component;

public class Wealth implements Wealthable {
    
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

    /**
     * Returns value of town wealth 
     * @return town wealth amount
     */
    @Override
    public int getWealth() {
        return amount;
    }

    /**
     * Adds an amount to town wealth
     * @param amount amount to add to town wealth
     */
    @Override
    public void addWealth(int amount) {
        this.amount += amount;
    }

     /**
     * Adds an amount to town wealth
     * @param amount amount to add to town wealth
     */
    @Override
    public void addWealthGrowth(int rate) {
        this.rate += rate;
    }
}
