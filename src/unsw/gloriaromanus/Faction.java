package unsw.gloriaromanus;

import java.util.ArrayList;
import java.util.List;
import unsw.gloriaromanus.economy.Treasurial;
import unsw.gloriaromanus.economy.Treasury;
import unsw.gloriaromanus.event.Topics;
import unsw.gloriaromanus.unit.UnitComponent;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;

public class Faction implements Entity, PubSubable, Treasurial {

    private static final long serialVersionUID = -2460947940289328286L;

    // Topics
    private String TAX_COLLECT;

    private String faction; // Name of faction
    private List<Province> territories;
    private Treasury treasury;
    private List<UnitComponent> units;

    /**
     * Constructs a faction given only a name
     * 
     * @param faction name of faction
     */
    public Faction(String faction) {
        this(faction, new ArrayList<Province>());
    }

    /**
     * Constructs a faction given name and list of factions
     * 
     * @param faction   name of faction
     * @param provinces provinces belonging to faction
     */
    public Faction(String faction, List<Province> provinces) {
        this.faction = faction;
        this.territories = new ArrayList<Province>(provinces);
        this.treasury = new Treasury();
        this.units = new ArrayList<UnitComponent>();
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

    @Override
    public int getTreasury() {
        return treasury.getTreasury();
    }

    @Override
    public void addTreasury(int amount) {
        this.treasury.addTreasury(amount);
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

    @Override
    public void publish(String topic, Message<Object> message) {
        PubSub.getInstance().publish(topic, message);
    }

    @Override
    public void listen(String topic, Message<Object> message) {
        if (topic.contains("TAX_COLLECT")) {
            this.addTreasury((Integer) message.getMessage());
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
        json.put("faction", faction);
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
}
