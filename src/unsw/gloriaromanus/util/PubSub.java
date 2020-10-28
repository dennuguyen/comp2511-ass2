/**
 * Naive topic server and publisher-subscriber pattern handler.
 * 
 * Topics are mapped to a single publisher and can be mapped to many subscribers.
 * 
 * Topics once created are created forever...
 */

package unsw.gloriaromanus.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PubSub implements PubSubable {

    private Map<Topic, PubSubable> publishers = new HashMap<Topic, PubSubable>();
    private Map<Topic, ArrayList<PubSubable>> subscribers =
            new HashMap<Topic, ArrayList<PubSubable>>();

    /**
     * Topic server constructor
     */
    private PubSub() {
    }

    /**
     * Bill Pugh singleton
     */
    private static class BillPughTopicServer {
        private static final PubSub pubSubSingleton = new PubSub();
    }

    /**
     * Gets the instance of the topic server
     * 
     * @return Topic server singleton
     */
    public static PubSub getInstance() {
        return BillPughTopicServer.pubSubSingleton;
    }

    /**
     * Get all topics
     * 
     * @return List of topics
     */
    public ArrayList<Topic> getTopics() {
        ArrayList<Topic> topics = new ArrayList<Topic>(this.publishers.keySet());
        topics.addAll(this.subscribers.keySet());
        return topics;
    }

    @Override
    public void addPublisher(PubSubable publisher, Topic topic) {

        // if topic does not exist, create a new entry for publishers
        if (!this.publishers.containsKey(topic))
            this.publishers.put(topic, publisher);
    }

    @Override
    public void addSubscriber(PubSubable subscriber, Topic topic) {

        ArrayList<PubSubable> temp = null; // temp list

        if (this.subscribers.containsKey(topic))
            temp = this.subscribers.get(topic);
        else
            temp = new ArrayList<PubSubable>();

        temp.add(subscriber); // add subscriber to temp list
        this.subscribers.put(topic, temp);
    }

    @Override
    public void publish(Topic topic, Message<Object> message) {
        for (PubSubable subscriber : this.subscribers.get(topic))
            subscriber.listen(topic, message);
    }

    @Override
    public void listen(Topic topic, Message<Object> message) {
        // Listeners implement this function
    }

    @Override
    public void unpublish(PubSubable publisher, Topic topic) {
        if (this.publishers.containsKey(topic))
            this.publishers.put(topic, null);
    }

    @Override
    public void unsubscribe(PubSubable subscriber, Topic topic) {
        if (this.subscribers.containsKey(topic)) {
            ArrayList<PubSubable> temp = this.subscribers.get(topic);
            if (temp == null)
                return;
            temp.remove(subscriber);
            this.subscribers.put(topic, temp);
        }
    }
}
