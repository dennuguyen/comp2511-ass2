package unsw.gloriaromanus.component;

public class LowTax implements TaxLevel {

    @Override
    public String getName() {
        return "Low";    
    }

    public int getTaxRate() {
        return 10;
    }

    @Override
    public int getWealthGrowth() {
        return 10;
    }
}
