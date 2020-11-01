package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;

public class PubSubTest {

    private class TestClass1 implements PubSubable {

        int val = 2;

        @Override
        public void publish(String topic, Message<Object> message) {
            PubSub.getInstance().publish(topic, message);
        }

        @Override
        public void listen(String topic, Message<Object> message) {
        }

        @Override
        public void subscribe(String topic) {
            PubSub.getInstance().subscribe(this, topic);
        }

        @Override
        public void unsubscribe(String topic) {
            PubSub.getInstance().unsubscribe(this, topic);
        }
    }

    private class TestClass2 implements PubSubable {

        int val = 0;
        String str = "";

        private int getVal() {
            return this.val;
        }

        private String getStr() {
            return this.str;
        }

        private void setStr(String str) {
            this.str = str;
        }

        @Override
        public void publish(String topic, Message<Object> message) {
            PubSub.getInstance().publish(topic, message);
        }

        @Override
        public void listen(String topic, Message<Object> message) {
            if (topic == "topic")
                this.val = (Integer) message.getMessage();

            else if (topic == "set string")
                this.str = (String) message.getMessage();
        }

        @Override
        public void subscribe(String topic) {
            PubSub.getInstance().subscribe(this, topic);
        }

        @Override
        public void unsubscribe(String topic) {
            PubSub.getInstance().unsubscribe(this, topic);
        }
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void subscribingShouldCreateNewTopics() {
        TestClass1 testClass1 = new TestClass1();
        testClass1.subscribe("new topic");
        testClass1.subscribe("another new topic");
        assertEquals(true, PubSub.getTopics().contains("new topic"));
        assertEquals(true, PubSub.getTopics().contains("another new topic"));
    }

    @Test
    public void publishShouldSendMessage() {
        TestClass1 testClass1 = new TestClass1();
        TestClass2 testClass2 = new TestClass2();

        testClass2.setStr("");
        assertEquals("", testClass2.getStr());

        testClass2.subscribe("set string");
        testClass1.publish("set string", Message.of("A secret message"));
        assertEquals("A secret message", testClass2.getStr());

        testClass2.subscribe("topic");
        testClass1.publish("topic", Message.of(100));
        assertEquals(100, testClass2.getVal());
    }

    @Test
    public void unsubscribeShouldNotGetMessage() {
        TestClass1 testClass1 = new TestClass1();
        TestClass2 testClass2 = new TestClass2();

        testClass2.setStr("");
        assertEquals("", testClass2.getStr());

        testClass2.subscribe("set string");
        testClass1.publish("set string", Message.of("A secret message"));
        assertEquals("A secret message", testClass2.getStr());

        testClass2.unsubscribe("set string");
        testClass1.publish("set string", Message.of("Another secret message"));
        assertEquals("A secret message", testClass2.getStr());
    }
}
