/**
 * Publish-subscriber interface
 */

package unsw.gloriaromanus.util;

public interface PubSubable {

    /**
     * Add publisher to topic server
     * 
     * @param publisher Object implementing PubSubable
     * @param topic     Name of topic
     */
    public void addPublisher(PubSubable publisher, String topic);

    /**
     * Add subscriber to topic server
     * 
     * @param publisher Object implementing PubSubable
     * @param topic     Name of topic
     */
    public void addSubscriber(PubSubable subscriber, String topic);

    /**
     * Publish message to topic
     * 
     * @param topic   Published topic
     * @param message Message to send
     */
    public void publish(String topic, Message message);

    /**
     * Listen to topic for message
     * 
     * @param topic   Subscribed topic
     * @param message Message to receive
     */
    public void listen(String topic, Message message);

    /**
     * Remove publisher from topic server
     * 
     * @param publisher Publisher object
     * @param topic     Topic to remove publisher from
     */
    public void unpublish(PubSubable publisher, String topic);

    /**
     * Remove subscriber from topic server
     * 
     * @param subscriber Subscriber object
     * @param topic      Topic to remove subscriber from
     */
    public void unsubscribe(PubSubable subscriber, String topic);
}
