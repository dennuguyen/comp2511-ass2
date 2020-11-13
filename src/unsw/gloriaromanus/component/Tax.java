/**
 * Implementation of Taxable interface
 */

package unsw.gloriaromanus.component;

public class Tax implements Taxable {

    private TaxLevel taxLevel;

    /**
     * Gets the tax level
     * 
     * @return Tax level
     */
    public TaxLevel getTaxLevel() {
        return this.taxLevel;
    }

    @Override
    public void setTaxLevel(TaxLevel taxLevel) {
        this.taxLevel = taxLevel;
    }
}
