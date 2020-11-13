/**
 * Wealthable interface
 */

package unsw.gloriaromanus.economy;

public interface Wealthable {

    /**
     * Returns the town wealth of a province
     * 
     * @return town wealth
     */
    public int getWealth();

    /**
     * Set the town wealth growth rate of a province
     * 
     * @param rate New growth rate
     */
    public void setWealthGrowth(int rate);

    /**
     * Returns the town wealth growth rate of a province
     * 
     * @return town wealth growth rate
     */
    public int getWealthGrowth();

    /**
     * Add amount to town wealth
     * 
     * @param amount amount to add
     */
    public void addWealth(int amount);

    /**
     * Add wealth growth rate to town wealth
     * 
     */
    public void growWealth();

}