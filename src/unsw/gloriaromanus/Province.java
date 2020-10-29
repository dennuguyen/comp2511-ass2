package unsw.gloriaromanus;

import unsw.gloriaromanus.component.Locable;
import unsw.gloriaromanus.component.Locale;
import unsw.gloriaromanus.component.Tax;
import unsw.gloriaromanus.component.TaxLevel;
import unsw.gloriaromanus.component.Taxable;
import unsw.gloriaromanus.component.Wealthable;
import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Topic;
import unsw.gloriaromanus.component.Wealth;

public class Province implements Locable, Taxable, Wealthable, PubSubable {

    private final Topic WEALTH_GROWTH_DUE_TO_TAX;
    private final Topic MORALE_DUE_TO_TAX;
    private final Topic COLLECT_TAX_FROM_WEALTH;

    private final Locale locale;
    private final Tax tax;
    private final Wealth wealth;

    /**
     * Base constructor for province
     * 
     * @param name Name of province
     */
    public Province(String name) {
        this.locale = new Locale(name);
        this.tax = new Tax();
        this.wealth = new Wealth();

        // Prepare topics
        this.WEALTH_GROWTH_DUE_TO_TAX = Topic.of(name + "/WEALTH_GROWTH");
        this.MORALE_DUE_TO_TAX = Topic.of(name + "/MORALE");
        this.COLLECT_TAX_FROM_WEALTH = Topic.of(name + "/TAX_COLLECT");

        // Set topics for delegated objects
        this.tax.setTopics(this.WEALTH_GROWTH_DUE_TO_TAX, this.MORALE_DUE_TO_TAX);
        this.wealth.setTopics(this.WEALTH_GROWTH_DUE_TO_TAX, this.COLLECT_TAX_FROM_WEALTH);

        // Subscribe to turn events
        this.subscribeTo(Topic.NEXT_TURN);

        // Publish-subscribe wealth growth change event due to tax changes
        this.tax.publishTo(this.WEALTH_GROWTH_DUE_TO_TAX);
        this.wealth.subscribeTo(this.WEALTH_GROWTH_DUE_TO_TAX);

        // Publish-subscribe morale change event due to tax changes
        this.tax.publishTo(this.MORALE_DUE_TO_TAX);
        // this.wealth.subscribeTo(Topic.of(name + Topic.MORALE_DUE_TO_TAX));
    }

    @Override
    public String getLocation() {
        return this.locale.getLocation();
    }

    @Override
    public String setLocation(String location) {
        return this.locale.setLocation(location);
    }

    @Override
    public int getTaxRate() {
        return this.tax.getTaxRate();
    }

    @Override
    public void setTaxLevel(TaxLevel taxLevel) {
        tax.setTaxLevel(taxLevel);
    }

    @Override
    public void collectTax() {
        // Notify faction to add tax to treasury
    }

    @Override
    public int getWealth() {
        return this.wealth.getWealth();
    }

    @Override
    public int getWealthGrowth() {
        return this.wealth.getWealthGrowth();
    }

    @Override
    public void setWealthGrowth(int rate) {
        this.wealth.setWealthGrowth(rate);
    }

    @Override
    public void addWealth(int amount) {
        this.wealth.addWealth(amount);
    }

    @Override
    public void addWealthGrowth(int rate) {
        this.wealth.addWealthGrowth(rate);
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
