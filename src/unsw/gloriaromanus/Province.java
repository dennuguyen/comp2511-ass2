package unsw.gloriaromanus;

import unsw.gloriaromanus.component.Locable;
import unsw.gloriaromanus.component.Locale;
import unsw.gloriaromanus.component.Tax;
import unsw.gloriaromanus.component.Taxable;

public class Province implements Locable, Taxable {

    private final Locale locale;
    private final Tax tax;

    public Province(String name) {
        this.locale = new Locale(name);
        this.tax = new Tax();
    }

    @Override
    public String getLocation() {
        return this.locale.getLocation();
    }

    @Override
    public String setLocation(String location) {
        return this.locale.setLocation(location);
    }

    @Override
    public int getTaxRate() {
        return this.tax.getTaxRate();
    }

    @Override
    public void setTaxRate(int rate) {
        this.tax.setTaxRate(rate);
    }

    @Override
    public void collectTax() {
        this.tax.collectTaxImple();
    }
}
