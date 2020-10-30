/**
 * Implement of turnable interface
 * 
 * Turn is a singleton
 */
package unsw.gloriaromanus;

import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Topics;

public class Turn implements PubSubable {

    private int turn = 0;

    /**
     * Turn constructor
     */
    private Turn() {
        this.publishTo(Topics.NEXT_TURN);
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
        this.publish(Topics.NEXT_TURN, Message.of(this.turn));
    }

    @Override
    public void publishTo(String topic) {
        PubSub.getInstance().publishTo(this, topic);
    }

    @Override
    public void subscribeTo(String topic) {
        PubSub.getInstance().subscribeTo(this, topic);
    }

    @Override
    public void publish(String topic, Message<Object> message) {
        PubSub.getInstance().publish(topic, message);
    }

    @Override
    public void listen(String topic, Message<Object> message) {
    }

    @Override
    public void unpublish(String topic) {
        PubSub.getInstance().unpublish(this, topic);
    }

    @Override
    public void unsubscribe(String topic) {
        PubSub.getInstance().unsubscribe(this, topic);
    }
}
