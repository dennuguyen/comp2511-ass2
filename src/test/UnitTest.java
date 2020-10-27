package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.gloriaromanus.Province;
import unsw.gloriaromanus.component.*;

public class UnitTest{
    // @Test
    // public void blahTest(){
    //     assertEquals("a", "a");
    // }
    
    // @Test
    // public void blahTest2(){
    //     Unit u = new Unit();
    //     assertEquals(u.getNumTroops(), 50);
    // }

    @Test
    public void testSetTaxRate() {
        Province p = new Province("Britannia");
        p.setTaxLevel(new LowTax());
        assertEquals(p.getTaxRate(), 10.0);
        assertEquals(p.getWealthGrowthChange(), 10);

        p.setTaxLevel(new NormalTax());
        assertEquals(p.getTaxRate(), 15.0);
        assertEquals(p.getWealthGrowthChange(), 0);
        
        p.setTaxLevel(new HighTax());
        assertEquals(p.getTaxRate(), 20.0);
        assertEquals(p.getWealthGrowthChange(), -10);
        
        p.setTaxLevel(new VeryHighTax());
        assertEquals(p.getTaxRate(), 25.0);
        assertEquals(p.getWealthGrowthChange(), -30);
    }

    @Test
    public void testAddSubtractWealth() {
        Province p = new Province("Britannia");
        p.addWealth(100);
        assertEquals(p.getWealth(), 100);
        p.subtractWealth(50);
        assertEquals(p.getWealth(), 50);
    }

    @Test
    public void testCollectTax() {
        Province p = new Province("Britannia");
        p.addWealth(100);
        p.setTaxLevel(new LowTax());
        assertEquals(p.collectTax(), 10);
        assertEquals(p.getWealth(), 90);

        p.addWealth(10);
        p.setTaxLevel(new VeryHighTax());
        assertEquals(p.collectTax(), 25);
        assertEquals(p.getWealth(), 75);
    }
}

