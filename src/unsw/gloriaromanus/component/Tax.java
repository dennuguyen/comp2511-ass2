/**
 * Implementation of Taxable interface
 */

package unsw.gloriaromanus.component;

import java.util.ArrayList;

import unsw.gloriaromanus.util.ObserverTax;
import unsw.gloriaromanus.util.SubjectTax;

public class Tax implements Taxable, SubjectTax {

    ArrayList<ObserverTax> listObservers = new ArrayList<ObserverTax>();

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
    public void collectTax () {
        return;
    }

    @Override
	public void attach(ObserverTax o) {
		if(! listObservers.contains(o)) { listObservers.add(o); }
	}

	@Override
	public void detach(ObserverTax o) {
		listObservers.remove(o);
	}

    @Override
    public void tell() {
        for( ObserverTax obs : listObservers) {
			obs.update(this);
		}
    }
}
