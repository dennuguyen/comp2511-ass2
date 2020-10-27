/**
 * Taxable interface
 */

package unsw.gloriaromanus.component;

public interface Taxable {

    public void setTaxLevel(TaxLevel taxLevel);

    public float getTaxRate();

    public int collectTax();

    public int getWealthGrowthChange();
}
