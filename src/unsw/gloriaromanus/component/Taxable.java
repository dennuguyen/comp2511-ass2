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
     * Calculates tax
     * 
     * @param amount value to be taxed
     * 
     * @return tax revenue 
     */
    public int calculateTax(int amount);
}
