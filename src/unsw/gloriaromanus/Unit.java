/**
 * Unit class
 */

package unsw.gloriaromanus;

import unsw.gloriaromanus.component.Engageable;
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

public class Unit implements Entity, Engageable, Locable, Moveable, Statable, PubSubable {

    private static final long serialVersionUID = 466913578146928049L;
    private String RECOVERY;
    private String MORALE_DUE_TO_TAX;

    private final Locale locale;
    private final Move move;
    private final Stats stats;
    private final Engageable.Type engageType;

    /**
     * Unit constructor
     * 
     * @param spawn        Initial location
     * @param movementType Unit's movement type
     * @param stats        Base stats
     */
    public Unit(String spawn, Move.Type movementType, Engageable.Type engageType, Stats stats) {
        this.locale = new Locale(spawn);
        this.move = new Move(movementType);
        this.engageType = engageType;
        this.stats = new Stats(stats);

        // Prepare topics
        this.RECOVERY = spawn + Topics.CAMP;
        this.MORALE_DUE_TO_TAX = spawn + Topics.MORALE;

        // Subscribe to camped unit broadcast of spawnpoint
        this.subscribe(this.RECOVERY);
        this.subscribe(this.MORALE_DUE_TO_TAX);
    }

    @Override
    public Engageable.Type getEngageType() {
        return this.engageType;
    }

    @Override
    public String getLocation() {
        return this.locale.getLocation();
    }

    @Override
    public String setLocation(String location) {
        this.locale.setLocation(location);
        return location;
    }

    @Override
    public String moveTo(String destination) {
        // this.uncamp();
        return this.locale.setLocation(this.move.moveTo(this.locale.getLocation(), destination));
    }

    // /**
    // * Unit makes camp at a province therefore can recover manpower
    // */
    // public void camp() {
    // this.RECOVERY = this.locale.getLocation() + Topics.CAMP;
    // this.subscribe(this.RECOVERY);
    // }

    // /**
    // * Unsubscribe from province camp topic
    // */
    // public void uncamp() {
    // this.unsubscribe(this.RECOVERY);
    // this.RECOVERY = null;
    // }

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

        else if (topic.equals(this.RECOVERY)) {
            this.addStat(Stats.Type.STRENGTH, (Integer) message.getMessage());
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
