/**
 * Taxable interface
 */

package unsw.gloriaromanus.component;

public interface Taxable {

    public int getTaxRate();

    public void setTaxRate(Taxable rate);

    public void collectTax();
}
