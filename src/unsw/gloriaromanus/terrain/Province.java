package unsw.gloriaromanus.terrain;

import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;
import unsw.gloriaromanus.building.Camp;
import unsw.gloriaromanus.component.Locable;
import unsw.gloriaromanus.component.Locale;
import unsw.gloriaromanus.economy.Tax;
import unsw.gloriaromanus.economy.TaxLevel;
import unsw.gloriaromanus.economy.Taxable;
import unsw.gloriaromanus.economy.Wealth;
import unsw.gloriaromanus.economy.Wealthable;
import unsw.gloriaromanus.entity.Entity;
import unsw.gloriaromanus.event.Topics;
import unsw.gloriaromanus.system.Turn;
import unsw.gloriaromanus.system.World;
import unsw.gloriaromanus.unit.Levyable;
import unsw.gloriaromanus.unit.UnitLeaf;
import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Subject;

public class Province
        implements Entity, Locable, Levyable, Taxable, Wealthable, PubSubable, Observer, Populable {

    private static final long serialVersionUID = 6055239351776908023L;
    private final String WEALTH_GROWTH_DUE_TO_TAX;
    private final String MORALE_DUE_TO_TAX;
    private final String COLLECT_TAX_FROM_WEALTH;
    private final String SEND_MANPOWER;
    private final String LOSE_MANPOWER;

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
        this.SEND_MANPOWER = name + Topics.SEND_MANPOWER;
        this.LOSE_MANPOWER = name + Topics.LOSE_MANPOWER;

        // Publish-subscribe wealth growth change event due to tax changes
        this.subscribe(this.WEALTH_GROWTH_DUE_TO_TAX);
        this.subscribe(this.COLLECT_TAX_FROM_WEALTH);
        this.subscribe(this.LOSE_MANPOWER);

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
    public UnitLeaf enlist(Levyable.Type unitType) {
        return (UnitLeaf) this.camp.enlist(unitType, this.getLocation());
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
        if (taxLevel.getTaxRate() >= TaxLevel.VERY_HIGH_TAX.getTaxRate())
            this.publish(this.MORALE_DUE_TO_TAX, Message.of(true)); // -1 morale change

        // Change from very high tax
        else if ((this.tax.getTaxRate() >= TaxLevel.VERY_HIGH_TAX.getTaxRate())
                && !(taxLevel.getTaxRate() >= TaxLevel.VERY_HIGH_TAX.getTaxRate()))
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
            this.addWealth(-1 * (Integer) (message.getMessage()));
        }

        else if (topic.equals(this.LOSE_MANPOWER)) {
            this.addPopulation(-1 * (Integer) (message.getMessage()));
            this.publish(this.SEND_MANPOWER, message);
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
        this.addPopulation(this.getPopulationGrowth()); // Population naturally grows
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
    public int getPopulationGrowth() {
        return this.population.getPopulationGrowth();
    }

    @Override
    public void setPopulation(int population) {
        this.population.setPopulation(population);
    }

    @Override
    public void setPopulationGrowth(int rate) {
        this.population.setPopulationGrowth(rate);
    }

    @Override
    public void addPopulation(int change) {
        this.population.addPopulation(change);
    }

    @Override
    public void addPopulationGrowth(int change) {
        this.population.addPopulation(change);
    }

    @Override
    public void multiplyPopulationGrowth(int percentage) {
        this.population.multiplyPopulationGrowth(percentage);
    }

    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("name", locale.getLocation());
        json.put("taxLevel", this.tax.getTaxRate());
        json.put("wealth", getWealth());
        JSONArray units = new JSONArray();
        json.put("units", units);
        return json;
    }

    public static Province deserialize(World world, JSONObject json) {
        // String name = json.getString("name");
        // Province province = world.getProvince(name);
        // Tax tax = json.getString("taxLevel");
        // int wealth = json.getInt("wealth");
        // province.addWealth(wealth);
        // JSONArray units = json.getJSONArray("units");
        // for (int i = 0; i < units.length(); i++) {
        // JSONObject j = units.getJSONObject(i);
        // // province.enlist(j.deserialize(j));
        // }
        // return province;
        return null;
    }
}
