/**
 * Naive publisher-subscriber pattern handler.
 * 
 * Topics are mapped to a single publisher and can be mapped to many subscribers. Topics are created
 * and kept forever.
 */

package unsw.gloriaromanus.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PubSub implements PubSubable {

    private Map<String, PubSubable> publishers = new HashMap<String, PubSubable>();
    private Map<String, ArrayList<PubSubable>> subscribers = new HashMap<String, ArrayList<PubSubable>>();

    /**
     * Topic server constructor
     */
    private PubSub() {
    }

    /**
     * Bill Pugh singleton
     */
    private static class BillPughPubSub {
        private static final PubSub pubSubSingleton = new PubSub();
    }

    /**
     * Gets the instance of the topic server
     * 
     * @return Topic server singleton
     */
    public static PubSub getInstance() {
        return BillPughPubSub.pubSubSingleton;
    }

    /**
     * Get all topics
     * 
     * @return List of topics
     */
    public ArrayList<String> getTopics() {
        ArrayList<String> topics = new ArrayList<String>(this.publishers.keySet());
        topics.addAll(this.subscribers.keySet());
        return topics;
    }

    @Override
    public void publishTo(String topic) {
    }

    /**
     * Add publisher to topic server
     * 
     * @param publisher Object implementing PubSubable
     * @param topic     Name of topic
     */
    public void publishTo(PubSubable publisher, String topic) {
        // if topic does not exist, create a new entry for publishers
        if (!this.publishers.containsKey(topic))
            this.publishers.put(topic, publisher);
    }

    @Override
    public void subscribeTo(String topic) {
    }

    /**
     * Add subscriber to topic server
     * 
     * @param publisher Object implementing PubSubable
     * @param topic     Name of topic
     */
    public void subscribeTo(PubSubable subscriber, String topic) {
        ArrayList<PubSubable> temp = null; // temp list

        if (this.subscribers.containsKey(topic))
            temp = this.subscribers.get(topic);
        else
            temp = new ArrayList<PubSubable>();

        temp.add(subscriber); // add subscriber to temp list
        this.subscribers.put(topic, temp);
    }

    @Override
    public void publish(String topic, Message<Object> message) {
        if (this.subscribers.containsKey(topic))
            for (PubSubable subscriber : this.subscribers.get(topic))
                subscriber.listen(topic, message);
    }

    @Override
    public void listen(String topic, Message<Object> message) {
        // Listeners implement this function
    }

    @Override
    public void unpublish(String topic) {
    }

    /**
     * Remove publisher from topic server
     * 
     * @param publisher Publisher object
     * @param topic     Topic to remove publisher from
     */
    public void unpublish(PubSubable publisher, String topic) {
        if (this.publishers.containsKey(topic))
            this.publishers.put(topic, null);
    }

    @Override
    public void unsubscribe(String topic) {
    }

    /**
     * Remove subscriber from topic server
     * 
     * @param subscriber Subscriber object
     * @param topic      Topic to remove subscriber from
     */
    public void unsubscribe(PubSubable subscriber, String topic) {
        if (this.subscribers.containsKey(topic)) {
            ArrayList<PubSubable> temp = this.subscribers.get(topic);
            if (temp == null)
                return;
            temp.remove(subscriber);
            this.subscribers.put(topic, temp);
        }
    }
}
