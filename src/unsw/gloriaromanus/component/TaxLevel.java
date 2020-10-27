package unsw.gloriaromanus.component;

public interface TaxLevel {
    
    /**
     * Returns tax rate of corresponding tax level
     * @return tax rate
     */
    public float getTaxRate();

    /**
     * Returns wealth growth rate change of corresponding tax level
     * @return wealth growth rate change
     */
    public int getWealthGrowthChange();
}
