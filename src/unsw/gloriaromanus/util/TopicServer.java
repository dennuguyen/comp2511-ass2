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

public class TopicServer implements PubSubable {

    private Map<String, PubSubable> publishers = new HashMap<String, PubSubable>();
    private Map<String, ArrayList<PubSubable>> subscribers =
            new HashMap<String, ArrayList<PubSubable>>();

    /**
     * Topic server constructor
     */
    private TopicServer() {
    }

    /**
     * Bill Pugh singleton
     */
    private static class BillPughTopicServer {
        private static final TopicServer topicServerSingleton = new TopicServer();
    }

    /**
     * Gets the instance of the topic server
     * 
     * @return Topic server singleton
     */
    public static TopicServer getInstance() {
        return BillPughTopicServer.topicServerSingleton;
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
    public void addPublisher(PubSubable publisher, String topic) {

        // if topic does not exist, create a new entry for publishers
        if (!this.publishers.containsKey(topic))
            this.publishers.put(topic, publisher);
    }

    @Override
    public void addSubscriber(PubSubable subscriber, String topic) {

        ArrayList<PubSubable> temp = null; // temp list

        if (this.subscribers.containsKey(topic))
            temp = this.subscribers.get(topic);
        else
            temp = new ArrayList<PubSubable>();

        temp.add(subscriber); // add subscriber to temp list
        this.subscribers.put(topic, temp);
    }

    @Override
    public void publish(String topic, Message message) {
        for (PubSubable subscriber : this.subscribers.get(topic))
            subscriber.listen(topic, message);
    }

    @Override
    public void listen(String topic, Message message) {
        // Listeners implement this function
    }

    @Override
    public void unpublish(PubSubable publisher, String topic) {
        if (this.publishers.containsKey(topic))
            this.publishers.put(topic, null);
    }

    @Override
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
