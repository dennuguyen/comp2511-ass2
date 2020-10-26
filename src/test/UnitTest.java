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
    public void testSetWealth() {
        Province p = new Province("Britannia");
        p.setTaxRate(Tax.lowTax);
        assertEquals(p.getTaxRate(), 10);
    }
}

