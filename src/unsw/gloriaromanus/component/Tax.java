/**
 * Implementation of Taxable interface
 */

package unsw.gloriaromanus.component;

import java.util.ArrayList;

import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.Subject;

public class Tax implements Taxable, Subject {

    ArrayList<Observer> listObservers = new ArrayList<Observer>();

    TaxLevel taxLevel;

    public Tax() {
        taxLevel = new NormalTax();
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
    public int collectTax () {
        return 0;
    }

    @Override
	public void attach(Observer o) {
		if(! listObservers.contains(o)) { listObservers.add(o); }
	}

	@Override
	public void detach(Observer o) {
		listObservers.remove(o);
	}

    @Override
    public void tell() {
        for( Observer obs : listObservers) {
			obs.update(this);
		}
    }
}
