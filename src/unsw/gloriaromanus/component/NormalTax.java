package unsw.gloriaromanus.component;

public class NormalTax implements TaxLevel {

    @Override
    public float getTaxRate() {
        return 15;
    }

    @Override
    public int getWealthGrowthChange() {
        return 0;
    }
}
