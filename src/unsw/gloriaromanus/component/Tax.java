/**
 * Implementation of Taxable interface
 */

package unsw.gloriaromanus.component;

import unsw.gloriaromanus.util.Topic;
import unsw.gloriaromanus.util.Util;
import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.PubSub;

public class Tax implements Taxable, PubSubable {

    private Topic WEALTH_GROWTH_DUE_TO_TAX = null;
    private Topic MORALE_DUE_TO_TAX = null;
    private Topic collectTaxTopic = null;

    private TaxLevel taxLevel;

    public Tax() {
        this.taxLevel = new LowTax();
    }

    /**
     * Set topics once and only once
     * 
     * @param wealthGrowthTopic Wealth growth topic
     * @param moraleTopic       Morale topic
     */
    public void setTopics(Topic wealthGrowthTopic, Topic moraleTopic) {
        Util.setOnce(this.WEALTH_GROWTH_DUE_TO_TAX, wealthGrowthTopic);
        Util.setOnce(this.MORALE_DUE_TO_TAX, moraleTopic);
    }

    @Override
    public int getTaxRate() {
        return this.taxLevel.getTaxRate();
    }

    @Override
    public void setTaxLevel(TaxLevel taxLevel) {
        this.taxLevel = taxLevel;

        // Wealth growth event
        this.publish(this.WEALTH_GROWTH_DUE_TO_TAX,
                Message.of(this.taxLevel.getWealthGrowthChange()));

        // Morale change event
        if (taxLevel instanceof VeryHighTax)
            this.publish(this.MORALE_DUE_TO_TAX, Message.of(true)); // -1 morale change
        else
            this.publish(this.MORALE_DUE_TO_TAX, Message.of(false)); // 0 morale change
    }

    /**
     * 
     * @param wealth
     * @return
     */
    public int collectTaxImple(int wealth) {
        return wealth * (taxLevel.getTaxRate() / 100); // tax rate formula
    }

    @Override
    public void collectTax() {
        return;
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
