package unsw.gloriaromanus.component;

public class VeryHighTax implements TaxLevel {

    @Override
    public int getTaxRate() {
        return 25;
    }

    @Override
    public int getWealthGrowthChange() {
        return -30;
    }
}
