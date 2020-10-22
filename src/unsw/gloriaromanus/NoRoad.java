/**
 * No road is a type of road
 */

package unsw.gloriaromanus;

public class NoRoad implements RoadType {

    private int moves;

    /**
     * No road exhausts movement by 4 movement points
     */
    public NoRoad() {
        this.moves = 4;
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
