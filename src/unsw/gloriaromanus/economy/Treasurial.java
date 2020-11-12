package unsw.gloriaromanus.economy;

public interface Treasurial {

    /**
     * Gets the treasury balance
     * 
     * @return Treasury balance
     */
    public int getTreasury();

    /**
     * Adds/subtracts an amount to the treasury balance
     * 
     * @param amount
     */
    public void addTreasury(int amount);
}
