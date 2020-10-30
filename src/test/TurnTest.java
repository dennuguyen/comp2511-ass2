package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unsw.gloriaromanus.Turn;

public class TurnTest {

    Turn turn;

    @Before
    public void setUp() {
        this.turn = Turn.getInstance();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void turnInitialisationShouldHaveTurn0() throws Exception {
        assertNotEquals(null, turn);
        assertEquals(0, turn.getTurn());
    }

    @Test
    public void turnShouldIncrement() throws Exception {
        assertEquals(0, turn.getTurn());
        turn.incrementTurn();
        assertEquals(1, turn.getTurn());
    }

    @Test
    public void onlyOneTurnInstanceShouldEverExist() throws Exception {
        turn.incrementTurn();
        Turn turning = Turn.getInstance();
        assertEquals(2, turn.getTurn());
        assertEquals(2, turn.getTurn());
        assertEquals(turn, turning);
    }
}
