/**
 * Implementation of Moveable interface
 */

package unsw.gloriaromanus.component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import unsw.gloriaromanus.Province;
import unsw.gloriaromanus.World;
import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.Pair;
import unsw.gloriaromanus.util.Subject;

public class Move implements Moveable, PubSubable, Observer {

    public static enum Type {
        ARTILLERY(4), CAVALRY(15), INFANTRY(10);

        private int moveCapacity;

        /**
         * Move type constructor
         * 
         * @param moveCapacity Move capacity
         */
        private Type(int moveCapacity) {
            this.moveCapacity = moveCapacity;
        }
    };

    private Move.Type moveType;
    private int movesLeft;

    /**
     * 
     * @param moveType
     */
    public Move(Move.Type moveType) {
        this.moveType = moveType;
        this.movesLeft = moveType.moveCapacity;
    }

    /**
     * Subtracts the number of moves left with move points spent
     * 
     * @param exhaust Number of move points spent
     * 
     * @return Overspent move points, negative if underspent
     */
    private int exhaustMoves(int exhaust) {
        this.movesLeft -= exhaust;
        if (this.movesLeft < 0)
            this.movesLeft = 0;
        return exhaust - this.movesLeft;
    }

    public ArrayList<String> shortestPath(String start, String end) {
        Map<String, Map<String, Boolean>> matrix = World.getInstance().getMap();

        Map<String, Boolean> visited = new HashMap<String, Boolean>();
        Map<String, String> breadcrumbs = new HashMap<String, String>();

        for (String province : matrix.keySet()) {
            visited.put(province, false);
            breadcrumbs.put(province, null);
        }

        // Visit starting province
        Queue<String> pqueue = new PriorityQueue<>();
        Queue<String> nqueue = new PriorityQueue<>();
        Queue<Integer> wqueue = new PriorityQueue<>();
        visited.put(start, true);
        pqueue.add(start);
        nqueue.add(start);
        wqueue.add(1);

        while (!nqueue.isEmpty()) {

            String pnode = pqueue.remove();
            String nnode = nqueue.remove();
            Integer wnode = wqueue.remove();

            // Province not in breadcrumbs
            if (breadcrumbs.get(nnode) == null) {

                breadcrumbs.put(nnode, pnode);

                // Found destination
                if (nnode.equals(end)) {
                    String[] path = new String[wnode];
                    path[wnode - 1] = end;
                    String b = end;
                    for (int i = wnode - 2; i >= 0; i--)
                        path[i] = b = breadcrumbs.get(b);
                    return new ArrayList(Arrays.asList(path));
                }

                // Add connected children provinces
                for (String p : matrix.get(nnode).keySet()) {
                    if (matrix.get(nnode).get(p) == true) {
                        pqueue.add(nnode);
                        nqueue.add(p);
                        wqueue.add(wnode + 1);
                    }
                }
            }
        }

        return null;
    }

    @Override
    public String moveTo(String destination) {
        return null;
    }

    /**
     * 
     * @param start
     * @param destination
     * @return
     */
    public String moveTo(String start, String destination) {
        return null;
    }

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

    @Override
    public void update(Subject subject) {
        this.movesLeft = moveType.moveCapacity;
    }
}
