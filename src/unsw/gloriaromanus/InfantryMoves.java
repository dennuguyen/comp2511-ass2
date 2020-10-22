/**
 * Infantry have unique move type
 */

package unsw.gloriaromanus;

public class InfantryMoves implements MoveType {

    private int moves;

    /**
     * Cavalry move capacity is 10 movement points
     */
    public InfantryMoves() {
        this.moves = 10;
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
