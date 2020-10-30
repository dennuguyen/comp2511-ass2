package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.gloriaromanus.Faction;
import unsw.gloriaromanus.Province;

public class FactionTest {
    
    @Test
    public void taxCollectionShouldIncreaseTreasury() {
        Province britannia  = new Province("Britannia");
        Faction f = new Faction("F");
        f.addProvince(britannia);
        britannia.addWealth(100);
        britannia.collectTax();
        assertEquals(10, f.getTreasury());
    }
}