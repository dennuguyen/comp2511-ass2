/**
 * Implementation of Taxable interface
 */

package unsw.gloriaromanus.component;

public class Tax implements Taxable {

    public static final int lowTax = 10;
    public static final int normalTax = 15;
    public static final int highTax = 20;
    public static final int veryHighTax = 25;

    private int rate; // Tax rate

    public Tax() {
        this.rate = 0;
    }

    @Override
    public int getTaxRate() {
        return this.rate;
    }

    @Override
    public void setTaxRate(int rate) {
        this.rate = rate;
    }

    public void collectTaxImple() {

    }

    @Override
    public void collectTax() {

    }
}
