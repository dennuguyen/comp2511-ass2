/**
 * Taxable interface
 */

package unsw.gloriaromanus.component;

public interface Taxable {

    public void setTaxLevel(TaxLevel taxLevel);

    public float getTaxRate();

    public void collectTax();

    public int getWealthGrowthChange();
}
