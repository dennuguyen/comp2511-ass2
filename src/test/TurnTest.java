package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import unsw.gloriaromanus.Turn;

public class TurnTest {

    Turn turn = null;

    @BeforeEach
    public void setUp() {
        this.turn = Turn.getInstance();
    }

    @Test
    public void turnInitialisationShouldHaveTurn0() throws Exception {
        assertEquals(turn.getTurn(), 0);
    }

    @Test
    public void turnShouldIncrement() throws Exception {
        assertEquals(turn.getTurn(), 0);
        turn.incrementTurn();
        assertEquals(turn.getTurn(), 1);
    }

    @Test
    public void onlyOneTurnInstanceShouldEverExist() throws Exception {
        turn.incrementTurn();
        Turn turning = Turn.getInstance();
        assertEquals(turn.getTurn(), 1);
        assertEquals(turning.getTurn(), 1);
        assertEquals(turn, turning);
    }
}
