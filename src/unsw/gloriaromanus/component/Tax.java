/**
 * Implementation of Taxable interface
 */

package unsw.gloriaromanus.component;

import java.util.ArrayList;

import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.Subject;

public class Tax implements Taxable, Subject {

    ArrayList<Observer> observers = new ArrayList<Observer>();

    TaxLevel taxLevel;

    public Tax() {
        taxLevel = new LowTax();
    }

    @Override
    public float getTaxRate() {
        return taxLevel.getTaxRate();
    }

    @Override
    public int getWealthGrowthChange() {
        return taxLevel.getWealthGrowthChange();
    }

    @Override
    public void setTaxLevel(TaxLevel taxLevel) {
        this.taxLevel = taxLevel;
        tell();
    }

    public int collectTaxImple(int wealth) {
        float percentage = taxLevel.getTaxRate() / 100;
        return (int) (wealth * percentage);
    }

    @Override
    public void collectTax() {
        return;
    }

    @Override
    public void attach(Observer observer) {
        if (!this.observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    @Override
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void tell() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
