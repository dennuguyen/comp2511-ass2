/**
 * Artillery have unique move type
 */

package unsw.gloriaromanus;

public class ArtilleryMoves implements MoveType {

    private int moves;

    /**
     * Artillery move capacity is 4 movement points
     */
    public ArtilleryMoves() {
        this.moves = 4;
    }

    /**
     * Gets the number of moves left
     * 
     * @return Moves left
     */
    public int getMoves() {
        return this.moves;
    }

    /**
     * Subtracts the number of moves left with move points spent
     * 
     * @param exhaust Number of move points spent
     * 
     * @return Overspent move points, negative if underspent
     */
    public int exhaustMoves(int exhaust) {
        this.moves -= exhaust;
        if (this.moves < 0)
            this.moves = 0;
        return exhaust - this.moves;
    }
}
