package unsw.gloriaromanus.component;

public class NormalTax implements TaxLevel {

    @Override
    public String getName() {
        return "Normal";    
    }

    @Override
    public int getTaxRate() {
        return 15;
    }

    @Override
    public int getWealthGrowth() {
        return 0;
    }
}
