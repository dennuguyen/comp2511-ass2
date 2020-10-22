/**
 * Highway is a type of road
 */

package unsw.gloriaromanus;

public class Highway implements RoadType {

    private int moves;

    /**
     * Highway exhausts movement by 1 movement point
     */
    public Highway() {
        this.moves = 1;
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
