/**
 * Implementation of Taxable interface
 */

package unsw.gloriaromanus.component;

public class Tax implements Taxable {

    private TaxLevel taxLevel;

    public Tax() {
    }

    /**
     * Gets the tax level
     * 
     * @return Tax level
     */
    public TaxLevel getTaxLevel() {
        return this.taxLevel;
    }

    @Override
    public int getTaxRate() {
        return this.taxLevel.getTaxRate();
    }

    @Override
    public void setTaxLevel(TaxLevel taxLevel) {
        this.taxLevel = taxLevel;
    }

    @Override
    public int calculateTax(int amount) {
        return amount*getTaxRate()/100;
    }
}
