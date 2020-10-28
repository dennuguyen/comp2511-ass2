package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unsw.gloriaromanus.RomanLegionary;
import unsw.gloriaromanus.Unit;
import unsw.gloriaromanus.component.Move;
import unsw.gloriaromanus.component.Stats;

public class TurnTest {

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void unitShouldSpawnWithCorrectStats() {
        Unit unit = new Unit("location1", Move.Type.CAVALRY, new Stats());

        assertEquals(unit.getLocation(), "Rome");
        assertEquals(unit.getStat(Stats.Type.STRENGTH), 1000);
    }

    @Test
    public void romanLegionaryShouldHaveDefaultStats() {
        RomanLegionary legionary = new RomanLegionary("Rome");

        assertEquals(legionary.getLocation(), "Rome");
        assertEquals(legionary.getStat(Stats.Type.STRENGTH), 1000);
    }
}
