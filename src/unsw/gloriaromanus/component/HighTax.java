package unsw.gloriaromanus.component;

public class HighTax implements TaxLevel {

    @Override
    public int getTaxRate() {
        return 20;
    }

    @Override
    public int getWealthGrowthChange() {
        return -10;
    }
}
