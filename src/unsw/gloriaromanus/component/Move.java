/**
 * Implementation of Moveable interface
 */

package unsw.gloriaromanus.component;

public class Move implements Moveable {

    public static enum Type {
        ARTILLERY(4), CAVALRY(15), INFANTRY(10);

        private int moveCapacity;

        Type(int moveCapacity) {
            this.moveCapacity = moveCapacity;
        }

        public int getMoveCapacity() {
            return this.moveCapacity;
        }
    };

    private Move.Type moveType;
    private int movesLeft;

    /**
     * 
     * @param moveType
     */
    public Move(Move.Type moveType) {
        this.moveType = moveType;
        this.movesLeft = moveType.getMoveCapacity();
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

    private int dijkstra() {
        return 0;
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
