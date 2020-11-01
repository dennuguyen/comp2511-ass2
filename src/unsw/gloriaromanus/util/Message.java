/**
 * Message implementation of Publish-subscriber pattern. Message is a generic container.
 */

package unsw.gloriaromanus.util;

public class Message<K> {

    private K message;

    /**
     * Message constructor
     * 
     * @param message Message details
     */
    public Message(K message) {
        this.message = message;
    }

    /**
     * Convenient message creator
     * 
     * @param <K>     Message type
     * @param message Message details
     * @return Message
     */
    public static <K> Message<K> of(K message) {
        return new Message<K>(message);
    }

    /**
     * Gets the generic message
     * 
     * @return Message
     */
    public K getMessage() {
        return this.message;
    }
}
