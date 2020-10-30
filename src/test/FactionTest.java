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

    @Test
    public void wealthShouldAccumulateAllProvinces() {
        Faction f = new Faction("F");
        Province britannia  = new Province("Britannia");
        Province lugdunensis  = new Province("Lugdunensis");
        Province belgica  = new Province("Belgica");
        Province aquitania  = new Province("Aquitania");
        f.addProvince(britannia);
        f.addProvince(lugdunensis);
        f.addProvince(belgica);
        f.addProvince(aquitania);
        britannia.addWealth(100);
        lugdunensis.addWealth(100);
        belgica.addWealth(100);
        aquitania.addWealth(100);
        assertEquals(400, f.calculateWealth());
    }
}