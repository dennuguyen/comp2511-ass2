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
    public void addPublisher(PubSubable publisher, Topic topic);

    /**
     * Add subscriber to topic server
     * 
     * @param publisher Object implementing PubSubable
     * @param topic     Name of topic
     */
    public void addSubscriber(PubSubable subscriber, Topic topic);

    /**
     * Publish message to topic
     * 
     * @param topic   Published topic
     * @param message Message to send
     */
    public void publish(Topic topic, Message<Object> message);

    /**
     * Listen to topic for message
     * 
     * @param topic   Subscribed topic
     * @param message Message to receive
     */
    public void listen(Topic topic, Message<Object> message);

    /**
     * Remove publisher from topic server
     * 
     * @param publisher Publisher object
     * @param topic     Topic to remove publisher from
     */
    public void unpublish(PubSubable publisher, Topic topic);

    /**
     * Remove subscriber from topic server
     * 
     * @param subscriber Subscriber object
     * @param topic      Topic to remove subscriber from
     */
    public void unsubscribe(PubSubable subscriber, Topic topic);
}
