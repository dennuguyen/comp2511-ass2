/**
 * Implement of turnable interface
 * 
 * Turn is a singleton
 */
package unsw.gloriaromanus;

import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Topic;

public class Turn implements PubSubable {

    private int turn = 0;

    /**
     * Turn constructor
     */
    private Turn() {
        this.publishTo(Topic.NEXT_TURN);
    }

    /**
     * Bill Pugh singleton
     */
    private static class BillPughTurn {
        private static final Turn turnSingleton = new Turn();
    }

    /**
     * Gets the instance of turn
     * 
     * @return Turn singleton
     */
    public static Turn getInstance() {
        return BillPughTurn.turnSingleton;
    }

    /**
     * Gets the current turn
     * 
     * @return Current turn
     */
    public int getTurn() {
        return this.turn;
    }

    /**
     * Increment the turn
     */
    public void incrementTurn() {
        this.turn++;
        this.publish(Topic.NEXT_TURN, Message.of(this.turn));
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
