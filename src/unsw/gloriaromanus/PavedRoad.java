/**
 * Paved road is a type of road
 */

package unsw.gloriaromanus;

public class PavedRoad implements RoadType {

    private int moves;

    /**
     * Paved road exhausts movement by 2 movement points
     */
    public PavedRoad() {
        this.moves = 2;
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
