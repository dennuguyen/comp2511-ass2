/**
 * Implementation of Moveable interface
 */

package unsw.gloriaromanus.component;

import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Topic;

public class Move implements Moveable, PubSubable {

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

    @Override
    public String moveTo(String destination) {
        return null;
    }

    /**
     * 
     * @param start
     * @param destination
     * @return
     */
    public String moveTo(String start, String destination) {
        return null;
    }

    @Override
    public void publishTo(Topic topic) {
        PubSub.getInstance().publishTo(this, topic);
    }

    @Override
    public void subscribeTo(Topic topic) {
        PubSub.getInstance().subscribeTo(this, topic);
    }

    @Override
    public void publish(Topic topic, Message<Object> message) {
        PubSub.getInstance().publish(topic, message);
    }

    @Override
    public void listen(Topic topic, Message<Object> message) {

        if (topic.equals(Topic.NEXT_TURN)) {
            this.movesLeft = moveType.moveCapacity;
        }
    }

    @Override
    public void unpublish(Topic topic) {
        PubSub.getInstance().unpublish(this, topic);
    }

    @Override
    public void unsubscribe(Topic topic) {
        PubSub.getInstance().unsubscribe(this, topic);
    }
}
