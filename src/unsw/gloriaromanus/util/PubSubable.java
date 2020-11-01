/**
 * Publish-subscriber interface
 */

package unsw.gloriaromanus.util;

public interface PubSubable {

    /**
     * Publish message to topic
     * 
     * @param topic   Published topic
     * @param message Message to send
     */
    public void publish(String topic, Message<Object> message);

    /**
     * Listen to topic for message
     * 
     * @param topic   Subscribed topic
     * @param message Message to receive
     */
    public void listen(String topic, Message<Object> message);

    /**
     * Add subscriber to topic server
     * 
     * @param topic Name of topic
     */
    public void subscribe(String topic);

    /**
     * Remove subscriber from topic server
     * 
     * @param topic Topic to remove subscriber from
     */
    public void unsubscribe(String topic);
}
