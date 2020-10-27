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

    /**
     * Returns the change in wealth growth rate related to tax level
     * @return wealth growth rate chagne
     */
    public int getWealthGrowthChange();
}
