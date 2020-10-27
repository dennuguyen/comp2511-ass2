/**
 * Taxable interface
 */

package unsw.gloriaromanus.component;

public interface Taxable {

    /**
     * Set the tax level
     * 
     * @param taxLevel New tax level
     */
    public void setTaxLevel(TaxLevel taxLevel);

    /**
     * Gets the current tax rate
     * 
     * @return Tax rate
     */
    public float getTaxRate();

    /**
     * Collects the tax and notifies the province subject of tax returns
     */
    public void collectTax();

    public int getWealthGrowthChange();
}
