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

    private static Map<String, ArrayList<PubSubable>> subscribers = new HashMap<String, ArrayList<PubSubable>>();

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
    public static ArrayList<String> getTopics() {
        return new ArrayList<String>(PubSub.subscribers.keySet());
    }

    @Override
    public void publish(String topic, Message<Object> message) {
        if (PubSub.subscribers.containsKey(topic))
            for (PubSubable subscriber : PubSub.subscribers.get(topic))
                subscriber.listen(topic, message);
    }

    @Override
    public void listen(String topic, Message<Object> message) {
        // Listeners implement PubSub function
    }

    @Override
    public void subscribe(String topic) {
    }

    /**
     * Add subscriber to topic server
     * 
     * @param publisher Object implementing PubSubable
     * @param topic     Name of topic
     */
    public void subscribe(PubSubable subscriber, String topic) {
        ArrayList<PubSubable> temp = null; // temp list

        if (PubSub.subscribers.containsKey(topic))
            temp = PubSub.subscribers.get(topic);
        else
            temp = new ArrayList<PubSubable>();

        temp.add(subscriber); // add subscriber to temp list
        PubSub.subscribers.put(topic, temp);
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
        if (PubSub.subscribers.containsKey(topic)) {
            ArrayList<PubSubable> temp = PubSub.subscribers.get(topic);
            if (temp == null)
                return;
            temp.remove(subscriber);
            PubSub.subscribers.put(topic, temp);
        }
    }
}
