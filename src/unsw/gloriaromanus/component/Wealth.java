/**
 * Wealth implementation of wealthable
 */

package unsw.gloriaromanus.component;

import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Topic;
import unsw.gloriaromanus.util.Util;

public class Wealth implements Wealthable, PubSubable {

    private Topic WEALTH_GROWTH_DUE_TO_TAX = null;
    private Topic COLLECT_TAX_FROM_WEALTH = null;

    int amount;
    int rate;

    /**
     * Default constructor
     */
    public Wealth() {
        this(0);
    }

    /**
     * Base constructor for Wealth using given starting amount for town wealth
     * 
     * @param amount starting amount of wealth
     */
    public Wealth(int amount) {
        this.amount = amount;
        this.rate = 0;
    }

    /**
     * Set topics once and only once
     * 
     * @param wealthGrowthTopic Wealth growth topic
     */
    public void setTopics(Topic wealthGrowthTopic, Topic taxCollectionTopic) {
        Util.setOnce(this.WEALTH_GROWTH_DUE_TO_TAX, wealthGrowthTopic);
        Util.setOnce(this.COLLECT_TAX_FROM_WEALTH, taxCollectionTopic);
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
    public void setWealthGrowth(int rate) {
        this.rate = rate;
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

        if (topic.equals(this.WEALTH_GROWTH_DUE_TO_TAX)) {
            this.setWealthGrowth((Integer) message.getMessage());
        }

        else if (topic.equals(Topic.NEXT_TURN)) {
            this.addWealth(this.rate);
        }

        else if (topic.equals(this.COLLECT_TAX_FROM_WEALTH)) {
            this.addWealth((Integer) message.getMessage());
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
