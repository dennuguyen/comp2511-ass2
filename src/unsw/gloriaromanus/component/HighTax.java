package unsw.gloriaromanus.component;

public class HighTax implements TaxLevel {

    @Override
    public float getTaxRate() {
        return 20;
    }

    @Override
    public int getWealthGrowthChange() {
        return -10;
    }
}
