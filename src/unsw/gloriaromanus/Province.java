package unsw.gloriaromanus;

import java.util.List;
import java.util.ArrayList;

import unsw.gloriaromanus.component.Locable;
import unsw.gloriaromanus.component.Locale;
import unsw.gloriaromanus.component.Tax;
import unsw.gloriaromanus.component.TaxLevel;
import unsw.gloriaromanus.component.Taxable;
import unsw.gloriaromanus.component.Wealthable;
import unsw.gloriaromanus.util.ObserverTax;
import unsw.gloriaromanus.util.SubjectTax;
import unsw.gloriaromanus.component.Wealth;

public class Province implements Locable, Taxable, Wealthable, ObserverTax {

    private final Locale locale;
    private final Tax tax;
    private final Wealth wealth;
    private final List<Unit> units;

    public Province(String name) {
        this.locale = new Locale(name);
        this.tax = new Tax();
        this.wealth = new Wealth();
        this.units = new ArrayList<Unit>();
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
    public float getTaxRate() {
        return this.tax.getTaxRate();
    }

    @Override
    public void setTaxLevel(TaxLevel taxLevel) {
        tax.setTaxLevel(taxLevel);
    }

    @Override
    public void collectTax() {
        int taxRevenue = tax.collectTaxImple(wealth.getWealth());
        subtractWealth(taxRevenue);
        // notify faction to add tax to treasury
    }

    @Override
    public int getWealth() {
        return this.wealth.getWealth();
    }

    @Override
    public int getWealthGrowth() {
        return this.wealth.getWealthGrowth();
    }

    @Override
    public int getWealthGrowthChange() {
        return this.tax.getWealthGrowthChange();
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

    @Override
    public void update(SubjectTax obj) {
        int rate = ((Tax)obj).getWealthGrowthChange();
        addWealthGrowth(rate);
    }

    @Override
    public void update(new turn) {
        collectTax();
        addWealth(getWealthGrowth());     
    }
}
