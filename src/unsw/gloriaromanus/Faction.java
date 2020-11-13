package unsw.gloriaromanus;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Subject;
import unsw.gloriaromanus.util.Topics;

public class Faction implements Entity, PubSubable, Observer {

    private static final long serialVersionUID = -2460947940289328286L;
    String name;
    List<Province> territories;
    int treasury;
    int wealth;

    /**
     * Constructs a faction given only a name
     * 
     * @param name name of faction
     */
    public Faction(String name) {
        this.name = name;
        territories = new ArrayList<Province>();
        treasury = 0;
        wealth = 0;
    }

    /**
     * Constructs a faction given name and list of factions
     * 
     * @param name      name of faction
     * @param provinces provinces belonging to faction
     */
    public Faction(String name, List<Province> provinces) {
        this.name = name;
        territories = new ArrayList<Province>();
        for (Province p : provinces) {
            territories.add(p);
        }
        treasury = 0;
        wealth = calculateWealth();
    }

    /**
     * Adds a province to faction
     * 
     * @param province province to be added
     */
    public void addProvince(Province province) {
        subscribe(province.getLocation() + Topics.TAX_COLLECT);
        territories.add(province);
    }

    /**
     * Removes a province from faction
     * 
     * @param province province to be removed
     */
    public void removeProvince(Province province) {
        unsubscribe(province.getLocation() + Topics.TAX_COLLECT);
        territories.remove(province);
    }

    /**
     * Returns number of territories owned
     * 
     * @return number of faction's territories
     */
    public int numTerritories() {
        return territories.size();
    }

    /**
     * Returns amount in treasury
     * 
     * @return amount in treasury
     */
    public int getTreasury() {
        return treasury;
    }

    /**
     * Returns total wealth across all faction's provinces
     * 
     * @return wealth across all provinces
     */
    public int calculateWealth() {
        int total = 0;
        for (Province p : territories) {
            total += p.getWealth();
        }
        return total;
    }

    /**
     * Adds amount to treasury
     * 
     * @param int amount to be added
     */
    public void addTreasury(int amount) {
        treasury += amount;
    }

    /**
     * Collects tax from every territory faction owns
     */
    public void collectTax() {
        for (Province p : territories) {
            p.collectTax();
        }
    }

    /**
     * Add wealth growth to wealth of every territory faction owns
     */
    public void growWealth() {
        for (Province p : territories) {
            p.growWealth();
        }
    }

    @Override
    public void publish(String topic, Message<Object> message) {
        PubSub.getInstance().publish(topic, message);
    }

    @Override
    public void listen(String topic, Message<Object> message) {
        if (topic.contains("TAX_COLLECT")) {
            int msg = Math.round((Float) (message.getMessage()));
            this.addTreasury(msg);
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

    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("treasury", treasury);
        JSONArray provinces = new JSONArray();
        for (Province p : territories) {
            provinces.put(p.serialize());
        }
        json.put("provinces", provinces);
        return json;
    }

    public static Faction deserialize(World world, JSONObject json) {
        String name = json.getString("name");
        Faction faction = new Faction(name);
        int treasury = json.getInt("treasury");
        faction.addTreasury(treasury);
        JSONArray provinces = json.getJSONArray("provinces");
        for (int i = 0; i < provinces.length(); i++) {
            JSONObject j = provinces.getJSONObject(i);
            faction.addProvince(Province.deserialize(world, j));
        }
        return faction;
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Turn) {
            growWealth();
            collectTax();
        }

    }
}
