package unsw.gloriaromanus;

import unsw.gloriaromanus.component.Locable;
import unsw.gloriaromanus.component.Locale;
import unsw.gloriaromanus.component.Tax;
import unsw.gloriaromanus.component.Taxable;
import unsw.gloriaromanus.component.Wealthable;
import unsw.gloriaromanus.component.Wealth;

public class Province implements Locable, Taxable, Wealthable {

    private final Locale locale;
    private final Tax tax;
    private final Wealth wealth;

    public Province(String name) {
        this.locale = new Locale(name);
        this.tax = new Tax();
        this.wealth = new Wealth();
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
        this.tax.collectTaxImple(wealth.getWealth());
    }

    @Override
    public int getWealth() {
        return this.wealth.getWealth();
    }

    @Override
    public void subtractWealth(int amount){
        this.wealth.subtractWealth(amount);
    }

    @Override
    public void addWealth(int amount){
        this.wealth.addWealth(amount);
    }

    @Override
    public void addWealthGrowth(int rate) {
        this.wealth.addWealthGrowth(rate);
    }
}
