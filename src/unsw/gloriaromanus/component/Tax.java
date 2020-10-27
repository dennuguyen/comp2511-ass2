/**
 * Implementation of Taxable interface
 */

package unsw.gloriaromanus.component;

import java.util.ArrayList;
import unsw.gloriaromanus.util.EventKind;
import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.Subject;

public class Tax implements Taxable, Subject {

    private ArrayList<Observer> wealthObservers;
    private ArrayList<Observer> moraleObservers;

    TaxLevel taxLevel;

    public Tax() {
        this.wealthObservers = new ArrayList<Observer>();
        this.moraleObservers = new ArrayList<Observer>();
        this.taxLevel = new LowTax();
    }

    @Override
    public int getTaxRate() {
        return this.taxLevel.getTaxRate();
    }

    @Override
    public void setTaxLevel(TaxLevel taxLevel) {
        this.taxLevel = taxLevel;
        tell(EventKind.EVENT_A);
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
    public void attach(Observer observer, EventKind eventKind) {
        if (eventKind == EventKind.EVENT_A) {
            if (!this.wealthObservers.contains(observer))
                this.wealthObservers.add(observer);
        } else if (eventKind == EventKind.EVENT_B) {
            if (!this.moraleObservers.contains(observer))
                this.moraleObservers.add(observer);
        }
    }

    @Override
    public void detach(Observer observer, EventKind eventKind) {
        if (eventKind == EventKind.EVENT_A)
            this.wealthObservers.remove(observer);
        else if (eventKind == EventKind.EVENT_B)
            this.moraleObservers.remove(observer);
    }

    @Override
    public void tell(EventKind eventKind) {
        if (eventKind == EventKind.EVENT_A) {
            for (Observer observer : wealthObservers)
                observer.update(this);
        } else if (eventKind == EventKind.EVENT_B) {
            for (Observer observer : moraleObservers)
                observer.update(this);
        }
    }
}
