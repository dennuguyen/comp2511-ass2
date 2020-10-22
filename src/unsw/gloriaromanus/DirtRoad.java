/**
 * Dirt road is a type of road
 */

package unsw.gloriaromanus;

public class DirtRoad implements RoadType {

    private int moves;

    /**
     * Dirt road exhausts movement by 3 movement points
     */
    public DirtRoad() {
        this.moves = 3;
    }

    /**
     * Gets the number of moves to exhaust
     * 
     * @return Number of moves to exhaust
     */
    public int getMoves() {
        return this.moves;
    }
}
