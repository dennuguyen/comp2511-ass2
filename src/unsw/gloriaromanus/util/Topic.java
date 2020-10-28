/**
 * Topic class encapsulating String with some common events for the publisher-subscribe pattern
 */

package unsw.gloriaromanus.util;

import java.util.Objects;

public class Topic {

    public static final Topic NEXT_TURN = Topic.of("/NEXT_TURN");

    private String topic;

    public Topic(String topic) {
        this.topic = topic;
    }

    public static Topic of(String topic) {
        return new Topic(topic);
    }

    public String getTopic() {
        return this.topic;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass())
            return false;

        Topic that = (Topic) obj;

        return Objects.equals(this.topic, that.topic);
    }
}
