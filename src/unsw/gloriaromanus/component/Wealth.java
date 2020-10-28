/**
 * Wealth implementation of wealthable
 */

package unsw.gloriaromanus.component;

import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Topic;

public class Wealth implements Wealthable, PubSubable {

    int amount;
    int rate;

    /**
     * Simple constructor
     */
    public Wealth() {
        this(0);
    }

    /**
     * Constructor using given starting amount for town wealth
     * 
     * @param amount starting amount of wealth
     */
    public Wealth(int amount) {
        this.amount = amount;
        this.rate = 0;
    }

    /**
     * Limit wealth values to be non-negative
     * 
     * @param value Value to limit
     * @return Limited value
     */
    private int limit(int value) {
        if (value < 0)
            value = 0;
        return value;
    }

    @Override
    public int getWealth() {
        return this.amount;
    }

    @Override
    public int getWealthGrowth() {
        return this.rate;
    }

    @Override
    public void addWealth(int amount) {
        this.amount += amount;
        this.amount = limit(this.amount);
    }

    @Override
    public void addWealthGrowth(int rate) {
        this.rate += rate;
        this.rate = limit(this.rate);
    }

    @Override
    public void addPublisher(Topic topic) {
        PubSub.getInstance().addPublisher(this, topic);
    }

    @Override
    public void addSubscriber(Topic topic) {
        PubSub.getInstance().addSubscriber(this, topic);
    }

    @Override
    public void publish(Topic topic, Message<Object> message) {
        PubSub.getInstance().publish(topic, message);
    }

    @Override
    public void listen(Topic topic, Message<Object> message) {
        switch (topic) {
            case NEXT_TURN:
                this.addWealth(this.rate);
            case WEALTH_GROWTH_DUE_TO_TAX:
                this.addWealthGrowth((Integer) message.getMessage());
                break;
            case TAX_COLLECTION:
                this.addWealth((Integer) message.getMessage());
            default:
                break;
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
