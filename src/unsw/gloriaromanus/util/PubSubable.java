/**
 * Publish-subscriber interface
 */

package unsw.gloriaromanus.util;

public interface PubSubable {

    /**
     * Add publisher to topic server
     * 
     * @param topic Name of topic
     */
    public void publishTo(Topic topic);

    /**
     * Add subscriber to topic server
     * 
     * @param topic Name of topic
     */
    public void subscribeTo(Topic topic);

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
     * @param topic Topic to remove publisher from
     */
    public void unpublish(Topic topic);

    /**
     * Remove subscriber from topic server
     * 
     * @param topic Topic to remove subscriber from
     */
    public void unsubscribe(Topic topic);
}
