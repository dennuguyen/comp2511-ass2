package unsw.gloriaromanus;

import java.util.Objects;

import unsw.gloriaromanus.component.Locable;
import unsw.gloriaromanus.component.Locale;
import unsw.gloriaromanus.component.Populable;
import unsw.gloriaromanus.component.Population;
import unsw.gloriaromanus.component.Tax;
import unsw.gloriaromanus.component.TaxLevel;
import unsw.gloriaromanus.component.Taxable;
import unsw.gloriaromanus.component.Wealthable;
import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Subject;
import unsw.gloriaromanus.util.Topics;
import unsw.gloriaromanus.component.Wealth;

public class Province
        implements Entity, Locable, Levyable, Taxable, Wealthable, PubSubable, Observer, Populable {

    private static final long serialVersionUID = 6055239351776908023L;
    private final String WEALTH_GROWTH_DUE_TO_TAX;
    private final String MORALE_DUE_TO_TAX;
    private final String COLLECT_TAX_FROM_WEALTH;
    private final String CAMPED_UNITS;

    private final Locale locale;
    private final Tax tax;
    private final Wealth wealth;
    private final Camp camp;
    private final Population population;

    /**
     * Base constructor for province
     * 
     * @param name Name of province
     */
    public Province(String name) {
        this.locale = new Locale(name);
        this.wealth = new Wealth();
        this.tax = new Tax();
        this.camp = new Camp();
        this.population = new Population();

        // Prepare topics
        this.WEALTH_GROWTH_DUE_TO_TAX = name + Topics.WEALTH_GROWTH;
        this.MORALE_DUE_TO_TAX = name + Topics.MORALE;
        this.COLLECT_TAX_FROM_WEALTH = name + Topics.TAX_COLLECT;
        this.CAMPED_UNITS = name + Topics.CAMP;

        // Publish-subscribe wealth growth change event due to tax changes
        this.subscribe(this.WEALTH_GROWTH_DUE_TO_TAX);
        this.subscribe(this.COLLECT_TAX_FROM_WEALTH);

        // Attach to turn events
        Turn.getInstance().attach(this);

        // Set tax level
        this.setTaxLevel(TaxLevel.LOW_TAX);
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
    public void setTaxLevel(TaxLevel taxLevel) {

        if (Objects.equals(this.tax.getTaxLevel(), taxLevel))
            return;

        // Change to very high tax
        if (taxLevel == TaxLevel.VERY_HIGH_TAX)
            this.publish(this.MORALE_DUE_TO_TAX, Message.of(true)); // -1 morale change

        // Change from very high tax
        else if ((this.tax.getTaxLevel() == TaxLevel.VERY_HIGH_TAX)
                && !(taxLevel == TaxLevel.VERY_HIGH_TAX))
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
            this.addWealthGrowth((Integer) message.getMessage());
        }

        else if (topic.equals(this.COLLECT_TAX_FROM_WEALTH)) {
            System.out.println("MESSAGE: " + (Integer) message.getMessage());
            this.addWealth(-1 * (Integer) (message.getMessage()));
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

    @Override
    public void update(Subject subject) {
        this.addWealth(this.getWealthGrowth()); // Wealth naturally grows
        this.camp.recruit(); // Recruit some troops
        this.addPopulation(100); // Population naturally grows
    }

    private int calculateTax() {
        return getWealth() * getTaxRate() / 100;
    }

    public void collectTax() {
        this.publish(this.COLLECT_TAX_FROM_WEALTH, Message.of(this.calculateTax()));
    }

    @Override
    public int getPopulation() {
        return this.population.getPopulation();
    }

    @Override
    public void setPopulation(int population) {
        this.population.setPopulation(population);
    }

    @Override
    public void addPopulation(int change) {
        this.population.addPopulation(change);
    }
}
