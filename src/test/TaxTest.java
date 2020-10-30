package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.gloriaromanus.component.HighTax;
import unsw.gloriaromanus.component.LowTax;
import unsw.gloriaromanus.component.NormalTax;
import unsw.gloriaromanus.component.Tax;
import unsw.gloriaromanus.component.VeryHighTax;

public class TaxTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void setTax() {
        Tax tax = new Tax();
        assertEquals(tax.getTaxRate(), 10);

        tax.setTaxLevel(new NormalTax());
        assertEquals(tax.getTaxRate(), 15);

        tax.setTaxLevel(new HighTax());
        assertEquals(tax.getTaxRate(), 20);

        tax.setTaxLevel(new VeryHighTax());
        assertEquals(tax.getTaxRate(), 25);

        tax.setTaxLevel(new LowTax());
        assertEquals(tax.getTaxRate(), 10);
    }

}
