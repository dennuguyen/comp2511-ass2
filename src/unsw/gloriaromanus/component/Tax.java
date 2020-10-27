/**
 * Implementation of Taxable interface
 */

package unsw.gloriaromanus.component;

import java.util.ArrayList;
import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.Subject;

public class Tax implements Taxable, Subject {

    private ArrayList<Observer> observers; // wealth observer

    TaxLevel taxLevel;

    public Tax() {
        this.observers = new ArrayList<Observer>();
        this.taxLevel = new LowTax();
    }

    /**
     * Gets the wealth growth change from the tax level
     * 
     * @return Wealth growth change
     */
    public int getWealthGrowthChange() {
        return this.taxLevel.getWealthGrowthChange();
    }

    @Override
    public int getTaxRate() {
        return this.taxLevel.getTaxRate();
    }

    @Override
    public void setTaxLevel(TaxLevel taxLevel) {
        this.taxLevel = taxLevel;
        tell(); // affect town wealth growth
    }

    /**
     * 
     * @param wealth
     * @return
     */
    public int collectTaxImple(int wealth) {
        return wealth * (taxLevel.getTaxRate() / 100); // tax rate formula
    }

    @Override
    public void collectTax() {
        return;
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
}
