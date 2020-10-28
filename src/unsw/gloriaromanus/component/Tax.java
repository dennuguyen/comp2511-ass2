/**
 * Implementation of Taxable interface
 */

package unsw.gloriaromanus.component;

import unsw.gloriaromanus.util.Topic;
import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.PubSub;

public class Tax implements Taxable, PubSubable {

    TaxLevel taxLevel;

    public Tax() {
        this.taxLevel = new LowTax();
    }

    @Override
    public int getTaxRate() {
        return this.taxLevel.getTaxRate();
    }

    @Override
    public void setTaxLevel(TaxLevel taxLevel) {
        this.taxLevel = taxLevel;
        this.publish(Topic.WEALTH_DUE_TO_TAX, Message.of(this.taxLevel.getWealthGrowthChange())); // affect
                                                                                                  // town
                                                                                                  // wealth
                                                                                                  // growth
        if (taxLevel instanceof VeryHighTax)
            this.publish(Topic.MORALE_DUE_TO_TAX, Message.of(-1)); // -1 morale
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
            case WEALTH_DUE_TO_TAX:
                break;
            case MORALE_DUE_TO_TAX:
                break;
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
