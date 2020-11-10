package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.gloriaromanus.World;

public class WorldTest {
    World world;

    @Before
    public void setUp() {
        World.init("src/unsw/gloriaromanus/province_adjacency_matrix_fully_connected.json");
        world = World.getInstance();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void provincesShouldBeCreatedForEveryWorldStringEntry() {
    }

    @Test
    public void worldShouldHave52Provinces() {
        assertEquals(53, world.getMap().size());
    }
}
