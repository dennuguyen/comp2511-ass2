package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.gloriaromanus.Province;
import unsw.gloriaromanus.component.LowTax;
import unsw.gloriaromanus.component.VeryHighTax;

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
        britannia.setTaxLevel(new VeryHighTax());
        assertEquals(-20, britannia.getWealthGrowth());
        britannia.setTaxLevel(new LowTax());
        assertEquals(-10, britannia.getWealthGrowth());
    }

    public static void main(String[] args) {
    }

}
