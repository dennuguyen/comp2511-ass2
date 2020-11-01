package unsw.gloriaromanus;

import java.util.ArrayList;
import java.util.List;

import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Topics;

public class Faction implements Entity, PubSubable {

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

    @Override
    public void publish(String topic, Message<Object> message) {
        PubSub.getInstance().publish(topic, message);
    }

    @Override
    public void listen(String topic, Message<Object> message) {
        if (topic.contains("TAX_COLLECT"))
            this.addTreasury((Integer) message.getMessage());
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
