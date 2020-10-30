/**
 * Unit class
 */

package unsw.gloriaromanus;

import unsw.gloriaromanus.component.Locable;
import unsw.gloriaromanus.component.Locale;
import unsw.gloriaromanus.component.Move;
import unsw.gloriaromanus.component.Moveable;
import unsw.gloriaromanus.component.Statable;
import unsw.gloriaromanus.component.Stats;
import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Topics;

public class Unit implements Entity, Locable, Moveable, Statable, PubSubable {

    private String CAMPED_UNITS;
    private String MORALE_DUE_TO_TAX;

    private final Locale locale;
    private final Move move;
    private final Stats stats;

    /**
     * Unit constructor
     * 
     * @param spawn        Initial location
     * @param movementType Unit's movement type
     * @param stats        Base stats
     */
    public Unit(String spawn, Move.Type movementType, Stats stats) {
        this.locale = new Locale(spawn);
        this.move = new Move(movementType);
        this.stats = new Stats(stats);

        // Subscribe to camped unit broadcast of spawnpoint
        this.subscribeTo(spawn + Topics.CAMP);
        this.subscribeTo(spawn + Topics.MORALE);
    }

    @Override
    public String getLocation() {
        return this.locale.getLocation();
    }

    @Override
    public String setLocation(String location) {

        // Set the unit's subscription to a province broadcast
        this.unsubscribe(this.CAMPED_UNITS);
        this.CAMPED_UNITS = location + Topics.CAMP;
        this.subscribeTo(this.CAMPED_UNITS);

        return this.locale.setLocation(location);
    }

    @Override
    public String moveTo(String destination) {
        return this.locale.setLocation(this.move.moveTo(this.locale.getLocation(), destination));
    }

    @Override
    public int getStat(Stats.Type type) {
        return this.stats.getStat(type);
    }

    @Override
    public void setStat(Stats.Type type, int value) {
        this.stats.setStat(type, value);
    }

    @Override
    public void addStat(Stats.Type type, int change) {
        this.stats.addStat(type, change);
    }

    @Override
    public void multiplyStat(Stats.Type type, int change) {
        this.stats.multiplyStat(type, change);
    }

    @Override
    public void publishTo(String topic) {
        PubSub.getInstance().publishTo(this, topic);
    }

    @Override
    public void subscribeTo(String topic) {
        PubSub.getInstance().subscribeTo(this, topic);
    }

    @Override
    public void publish(String topic, Message<Object> message) {
        PubSub.getInstance().publish(topic, message);
    }

    @Override
    public void listen(String topic, Message<Object> message) {

        if (topic.equals(this.MORALE_DUE_TO_TAX)) {
            if ((Boolean) message.getMessage() == true)
                this.stats.addStat(Stats.Type.MORALE, -1);
            else
                this.stats.addStat(Stats.Type.MORALE, 1);
        }
    }

    @Override
    public void unpublish(String topic) {
        PubSub.getInstance().unpublish(this, topic);
    }

    @Override
    public void unsubscribe(String topic) {
        PubSub.getInstance().unsubscribe(this, topic);
    }
}
