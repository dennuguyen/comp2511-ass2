package unsw.gloriaromanus.economy;

public enum TaxLevel {

    LOW_TAX(10), NORMAL_TAX(15), HIGH_TAX(20), VERY_HIGH_TAX(25);

    private int taxRate;

    private TaxLevel(int taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * Returns tax rate of corresponding tax level
     *
     * @return tax rate
     */
    public int getTaxRate() {
        return this.taxRate;
    }

    /**
     * Returns wealth growth rate change of corresponding tax level
     *
     * @return wealth growth rate change
     */
    public int getWealthGrowth() {
        if (this.taxRate >= 10)
            return (int) (-0.0133 * Math.pow(taxRate, 3) + 0.6 * Math.pow(taxRate, 2)
                    - 10.6667 * taxRate + 70);

        return (int) (0.5 * taxRate + 15); // this.taxRate < 10
    }
}
