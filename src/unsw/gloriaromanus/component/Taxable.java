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
    public int getTaxRate();


    /**
     * Collects the tax and notifies the relevant subjects of tax returns
     */
    public void collectTax();
}
