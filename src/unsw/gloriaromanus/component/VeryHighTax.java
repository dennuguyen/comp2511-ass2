package unsw.gloriaromanus.component;

public class VeryHighTax implements TaxLevel {

    @Override
    public String getName() {
        return "Very High";    
    }

    @Override
    public int getTaxRate() {
        return 25;
    }

    @Override
    public int getWealthGrowthChange() {
        return -30;
    }
}
