/**
 * Wealthable interface
 */

package unsw.gloriaromanus.component;

public interface Wealthable {

    /**
     * Returns the town wealth of a province
     * 
     * @return town wealth
     */
    public int getWealth();

    /**
     * Returns the town wealth growth rate of a province
     * 
     * @return town wealth growth rate
     */
    public int getWealthGrowth();

    /**
     * Subtract from town wealth
     * 
     * @param amount to subtract
     */
    public void subtractWealth(int amount);

    /**
     * Add amount to town wealth
     * 
     * @param amount amount to add
     */
    public void addWealth(int amount);

    /**
     * Add to wealth growth rate
     * 
     * @param rate rate to add
     */
    public void addWealthGrowth(int rate);
}
