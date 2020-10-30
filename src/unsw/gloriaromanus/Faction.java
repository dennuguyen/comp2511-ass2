package unsw.gloriaromanus;

import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Topics;

public class Faction implements PubSubable {

    public Faction() {
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
