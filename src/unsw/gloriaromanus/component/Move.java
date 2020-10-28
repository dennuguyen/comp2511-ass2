/**
 * Implementation of Moveable interface
 */

package unsw.gloriaromanus.component;

import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.Subject;

public class Move implements Moveable, Turnable, Observer {

    public static enum Type {
        ARTILLERY(4), CAVALRY(15), INFANTRY(10);

        private int moveCapacity;

        /**
         * Move type constructor
         * 
         * @param moveCapacity Move capacity
         */
        private Type(int moveCapacity) {
            this.moveCapacity = moveCapacity;
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
        this.movesLeft = moveType.moveCapacity;
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

    @Override
    public void nextTurn() {
        this.movesLeft = moveType.moveCapacity;
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Turn)
            this.nextTurn();
    }
}
