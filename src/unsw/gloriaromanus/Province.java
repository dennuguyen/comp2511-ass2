package unsw.gloriaromanus;

import java.util.ArrayList;

import unsw.gloriaromanus.component.Locable;
import unsw.gloriaromanus.component.Locale;
import unsw.gloriaromanus.component.Tax;
import unsw.gloriaromanus.component.TaxLevel;
import unsw.gloriaromanus.component.Taxable;
import unsw.gloriaromanus.component.Turn;
import unsw.gloriaromanus.component.Turnable;
import unsw.gloriaromanus.component.Wealthable;
import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.Subject;
import unsw.gloriaromanus.component.Wealth;

public class Province implements Locable, Taxable, Wealthable, Turnable, Subject, Observer {

    private final Locale locale;
    private final Tax tax;
    private final Wealth wealth;

    private ArrayList<Observer> observers; // unit observers

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
    public void setTaxLevel(TaxLevel taxLevel) {
        tax.setTaxLevel(taxLevel);
        tell(); // -1 morale for all soldiers in province
    }

    @Override
    public void collectTax() {
        // Notify faction to add tax to treasury
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
    public void addWealth(int amount) {
        this.wealth.addWealth(amount);
    }

    @Override
    public void addWealthGrowth(int rate) {
        this.wealth.addWealthGrowth(rate);
    }

    @Override
    public void nextTurn() {
        this.collectTax();
    }

    @Override
    public void attach(Observer observer) {
        if (!this.observers.contains(observer))
            this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void tell() {
        for (Observer observer : observers)
            observer.update(this);
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Turn) // next turn notified
            this.nextTurn();
    }
}
