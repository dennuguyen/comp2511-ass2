package unsw.gloriaromanus;

import java.util.ArrayList;
import java.util.List;

import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;

public class Faction implements PubSubable {

    private final String COLLECT_TAX_FROM_WEALTH;

    String name;
    List<Province> territories;
    int treasury;
    int wealth;

    public Faction(String name) {
        this.name = name;
        territories = new ArrayList<Province>();
        treasury = 0;
        wealth = 0;

        this.COLLECT_TAX_FROM_WEALTH = name + Topics.TAX_COLLECT;
    }

    /**
     * Returns amount in treasury
     * @return amount in treasury
     */
    public int getTreasury() {
        return treasury;
    }

    /**
     * Returns total wealth across factions provinces
     * @return wealth across provinces
     */
    public int getWealth() {
        int total = 0;
        for (Province p : territories) {
            total += p.getWealth();
        }
        return total;
    }

    /**
     * Adds amount to treasury
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

        if (topic.equals(this.COLLECT_TAX_FROM_WEALTH)) {
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
}
