package unsw.gloriaromanus.component;

public class LowTax implements TaxLevel {

    @Override
    public int getTaxRate() {
        return 10;
    }

    @Override
    public int getWealthGrowthChange() {
        return 10;
    }
}
