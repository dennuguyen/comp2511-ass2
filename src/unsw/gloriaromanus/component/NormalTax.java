package unsw.gloriaromanus.component;

public class NormalTax implements TaxLevel {

    @Override
    public int getTaxRate() {
        return 15;
    }

    @Override
    public int getWealthGrowthChange() {
        return 0;
    }
}
