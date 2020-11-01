package unsw.gloriaromanus.component;

public interface TaxLevel {

    /**
     * Returns tax rate of corresponding tax level
     * 
     * @return tax rate
     */
    public int getTaxRate();

    /**
     * Returns wealth growth rate change of corresponding tax level
     * 
     * @return wealth growth rate change
     */
    public int getWealthGrowthChange();
}

// package unsw.gloriaromanus.component;

// public enum TaxLevel {

// LOW_TAX(10, 10), NORMAL_TAX(15, 0), HIGH_TAX(20, -10), VERY_HIGH_TAX(25,
// -30);

// private int taxRate;
// private int growthChange;

// private TaxLevel(int taxRate, int growthChange) {
// this.taxRate = taxRate;
// this.growthChange = growthChange;
// }

// /**
// * Returns tax rate of corresponding tax level
// *
// * @return tax rate
// */
// public int getTaxRate() {
// return this.taxRate;
// }

// /**
// * Returns wealth growth rate change of corresponding tax level
// *
// * @return wealth growth rate change
// */
// public int getWealthGrowthChange() {
// return this.growthChange;
// }
// }
