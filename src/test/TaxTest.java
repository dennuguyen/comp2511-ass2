package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.gloriaromanus.Province;
import unsw.gloriaromanus.component.TaxLevel;

public class TaxTest {

    Province britannia;

    @Before
    public void setUp() {
        britannia = new Province("Britannia");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void taxCollectionShouldReduceWealth() {
        britannia.addWealth(100);
        assertEquals(100, britannia.getWealth());
        assertEquals(10, britannia.getTaxRate());
        britannia.collectTax();
        assertEquals(90, britannia.getWealth());
    }

    @Test
    public void newTaxLevelShouldChangeWealthGrowth() {
        assertEquals(10, britannia.getWealthGrowth());
        britannia.setTaxLevel(TaxLevel.VERY_HIGH_TAX);
        assertEquals(-20, britannia.getWealthGrowth());
        britannia.setTaxLevel(TaxLevel.LOW_TAX);
        assertEquals(-10, britannia.getWealthGrowth());
    }

    public static void main(String[] args) {
    }

}
