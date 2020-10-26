/**
 * Taxable interface
 */

package unsw.gloriaromanus.component;

public interface Taxable {

    /**
     * Gets the current tax rate
     * 
     * @return Tax rate
     */
    public int getTaxRate();

    /**
     * Set the tax rate
     * 
     * @param rate New tax rate
     */
    public void setTaxRate(int rate);

    /**
     * Collects the tax and notifies the province subject of tax returns
     */
    public void collectTax();
}
