package unsw.gloriaromanus;

import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.gloriaromanus.component.HighTax;
import unsw.gloriaromanus.component.Locable;
import unsw.gloriaromanus.component.Locale;
import unsw.gloriaromanus.component.LowTax;
import unsw.gloriaromanus.component.NormalTax;
import unsw.gloriaromanus.component.Tax;
import unsw.gloriaromanus.component.TaxLevel;
import unsw.gloriaromanus.component.Taxable;
import unsw.gloriaromanus.component.VeryHighTax;
import unsw.gloriaromanus.component.Wealthable;
import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Subject;
import unsw.gloriaromanus.util.Topics;
import unsw.gloriaromanus.component.Wealth;

public class Province
        implements Entity, Locable, Levyable, Taxable, Wealthable, PubSubable, Observer {

    private static final long serialVersionUID = 6055239351776908023L;
    private final String WEALTH_GROWTH_DUE_TO_TAX;
    private final String MORALE_DUE_TO_TAX;
    private final String COLLECT_TAX_FROM_WEALTH;
    private final String CAMPED_UNITS;

    private final Locale locale;
    private final Tax tax;
    private final Wealth wealth;
    private final Camp camp;

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
        this.setTaxLevel(new LowTax());
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

    public TaxLevel getTaxLevel() {
        return tax.getTaxLevel();
    }

    @Override
    public void setTaxLevel(TaxLevel taxLevel) {

        if (Objects.equals(this.tax.getTaxLevel(), taxLevel))
            return;

        // Change to very high tax
        if (taxLevel instanceof VeryHighTax)
            this.publish(this.MORALE_DUE_TO_TAX, Message.of(true)); // -1 morale change

        // Change from very high tax
        else if ((this.tax.getTaxLevel() instanceof VeryHighTax)
                && !(taxLevel instanceof VeryHighTax))
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
        if (subject instanceof Turn) {
            this.collectTax();
        }
    }

    private int calculateTax() {
        return getWealth() * getTaxRate() / 100;
    }

    public void collectTax() {
        this.publish(this.COLLECT_TAX_FROM_WEALTH, Message.of(this.calculateTax()));
    }

    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("name", locale.getLocation());
        if (tax.getTaxLevel() instanceof LowTax) {
            json.put("taxLevel", "low");
        }
        else if (tax.getTaxLevel() instanceof NormalTax) {
            json.put("taxLevel", "normal");
        }
        else if (tax.getTaxLevel() instanceof HighTax) {
            json.put("taxLevel", "high");
        }
        else if (tax.getTaxLevel() instanceof VeryHighTax) {
            json.put("taxLevel", "veryHigh");
        }
        json.put("wealth", getWealth());
        JSONArray units = new JSONArray();
        json.put("units", units);
        return json;
    }

    public static Province deserialize(World world, JSONObject json) {
        String name = json.getString("name");
        Province province = world.getProvince(name);
        switch(json.getString("taxLevel")) {
            case "low": province.setTaxLevel(new LowTax()); break;
            case "normal": province.setTaxLevel(new NormalTax()); break;
            case "high": province.setTaxLevel(new HighTax()); break;
            case "veryHigh": province.setTaxLevel(new VeryHighTax()); break;
        }
        int wealth = json.getInt("wealth");
        province.addWealth(wealth);
        JSONArray units = json.getJSONArray("units");
        for (int i = 0; i < units.length(); i++) {
            JSONObject j = units.getJSONObject(i);
            //province.enlist(j.deserialize(j));
        }
        return province; 
    }
}
