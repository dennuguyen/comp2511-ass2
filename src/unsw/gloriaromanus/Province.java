package unsw.gloriaromanus;

import unsw.gloriaromanus.component.Locable;
import unsw.gloriaromanus.component.Locale;
import unsw.gloriaromanus.component.Tax;
import unsw.gloriaromanus.component.TaxLevel;
import unsw.gloriaromanus.component.Taxable;
import unsw.gloriaromanus.component.VeryHighTax;
import unsw.gloriaromanus.component.Wealthable;
import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Topics;
import unsw.gloriaromanus.component.Wealth;

public class Province implements Locable, Levyable, Taxable, Wealthable, PubSubable {

    private final String WEALTH_GROWTH_DUE_TO_TAX;
    private final String MORALE_DUE_TO_TAX;
    private final String COLLECT_TAX_FROM_WEALTH;
    private final String CAMPED_UNITS;

    private final Locale locale;
    private final Tax tax;
    private final Wealth wealth;
    private final Camp camp;

    private @interface SetTaxEvent {
    }

    /**
     * Base constructor for province
     * 
     * @param name Name of province
     */
    public Province(String name) {
        this.locale = new Locale(name);
        this.tax = new Tax();
        this.wealth = new Wealth();
        this.camp = new Camp();

        // Prepare topics
        this.WEALTH_GROWTH_DUE_TO_TAX = name + Topics.WEALTH_GROWTH;
        this.MORALE_DUE_TO_TAX = name + Topics.MORALE;
        this.COLLECT_TAX_FROM_WEALTH = name + Topics.TAX_COLLECT;
        this.CAMPED_UNITS = name + Topics.CAMP;

        // Subscribe to turn events
        this.subscribe(Topics.NEXT_TURN);

        // Publish-subscribe wealth growth change event due to tax changes
        this.subscribe(this.WEALTH_GROWTH_DUE_TO_TAX);
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
    public Unit enlist(Levyable.Type unitType) {
        return this.camp.enlist(unitType, this.getLocation());
    }

    @Override
    public int getTaxRate() {
        return this.tax.getTaxRate();
    }

    @Override
    @SetTaxEvent
    public void setTaxLevel(TaxLevel taxLevel) {

        if (this.tax.getTaxLevel().equals(taxLevel))
            return;

        // Change to very high tax
        if (taxLevel instanceof VeryHighTax)
            this.publish(this.MORALE_DUE_TO_TAX, Message.of(true)); // -1 morale change

        // Change from very high tax
        else if ((this.tax.getTaxLevel() instanceof VeryHighTax) && !(taxLevel instanceof VeryHighTax))
            this.publish(this.MORALE_DUE_TO_TAX, Message.of(false)); // 0 morale change

        // Wealth growth event
        this.publish(this.WEALTH_GROWTH_DUE_TO_TAX, Message.of(taxLevel.getWealthGrowthChange()));

        // Set the tax level
        tax.setTaxLevel(taxLevel);
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
    public void publish(String topic, Message<Object> message) {
        PubSub.getInstance().publish(topic, message);
    }

    @Override
    public void listen(String topic, Message<Object> message) {

        if (topic.equals(this.WEALTH_GROWTH_DUE_TO_TAX)) {
            this.setWealthGrowth((Integer) message.getMessage());
        }

        else if (topic.equals(Topics.NEXT_TURN)) {
            this.addWealth(this.getWealthGrowth());
            this.publish(this.CAMPED_UNITS, null); // Camped units recruit some troops
        }

        else if (topic.equals(this.COLLECT_TAX_FROM_WEALTH)) {
            this.addWealth((Integer) message.getMessage());
        }
    }

    @Override
    public void subscribe(String topic) {
        PubSub.getInstance().subscribe(this, topic);
    }

    @Override
    public void unsubscribe(String topic) {
        PubSub.getInstance().unsubscribe(this, topic);
    }
}
