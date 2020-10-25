/**
 * Implementation of Moveable
 */

package unsw.gloriaromanus;

public class Move implements Moveable {

    public static final int artilleryMoveCapacity = 4;
    public static final int cavalryMoveCapacity = 15;
    public static final int infantryMoveCapacity = 10;

    private int movesLeft;
    private int moveType;

    public Move(int moveType) {
        this.movesLeft = moveType;
        this.moveType = moveType;
    }

    /**
     * Subtracts the number of moves left with move points spent
     * 
     * @param exhaust Number of move points spent
     * 
     * @return Overspent move points, negative if underspent
     */
    private int exhaustMoves(int exhaust) {
        this.movesLeft -= exhaust;
        if (this.movesLeft < 0)
            this.movesLeft = 0;
        return exhaust - this.movesLeft;
    }

    /**
     * 
     * @param start
     * @param end
     * @return
     */
    public String moveToImple(String start, String end) {
        return null;
    }

    @Override
    public String moveTo(String destination) {
        return null;
    }
}
