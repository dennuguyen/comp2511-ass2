package unsw.gloriaromanus.component;

public class HighTax implements TaxLevel {

    @Override
    public String getName() {
        return "High";    
    }

    @Override
    public int getTaxRate() {
        return 20;
    }

    @Override
    public int getWealthGrowth() {
        return -10;
    }
}
