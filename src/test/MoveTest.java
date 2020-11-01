package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.gloriaromanus.World;
import unsw.gloriaromanus.component.Move;

public class MoveTest {

    World world;

    @Before
    public void setUp() {
        World.init("src/unsw/gloriaromanus/province_adjacency_matrix_fully_connected.json");
        this.world = World.getInstance();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void simpleShortestPathShouldReturnShortestPath() {
        Move move = new Move(Move.Type.INFANTRY);
        ArrayList<String> path = move.shortestPath("Britannia", "Lugdunensis");
        assertEquals("[Britannia, Lugdunensis]", path.toString());
    }

    @Test
    public void complexShortestPathShouldReturnShortestPath() {
        Move move = new Move(Move.Type.INFANTRY);
        ArrayList<String> path = move.shortestPath("Britannia", "Alpes Cottiae");
        assertEquals("[Britannia, Lugdunensis, Aquitania, Narbonensis, Alpes Cottiae]",
                path.toString());
    }

    @Test
    public void unitMovesShouldBeResetToCapacity() {
    }
}
