/**
 * Implementation of Moveable interface
 */

package unsw.gloriaromanus.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import unsw.gloriaromanus.World;
import unsw.gloriaromanus.util.Message;
import unsw.gloriaromanus.util.PubSub;
import unsw.gloriaromanus.util.PubSubable;
import unsw.gloriaromanus.util.Observer;
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
     * Returns the number of move points appropriate to spend. Concurrently used to access the path
     * array.
     * 
     * @param exhaust Number of move points to spend
     * 
     * @return Move points spent and path array accessor
     */
    private int exhaustMoves(int exhaust) {
        this.movesLeft -= exhaust;
        if (this.movesLeft < 0)
            this.movesLeft = 0;
        return exhaust - this.movesLeft;
    }

    public ArrayList<String> shortestPath(String start, String end) {

        Map<String, Map<String, Boolean>> matrix = World.getInstance().getMap();
        Map<String, String> breadcrumbs = new HashMap<String, String>();

        for (String province : matrix.keySet())
            breadcrumbs.put(province, null);

        // Visit starting province
        Queue<String> pqueue = new PriorityQueue<>();
        Queue<String> nqueue = new PriorityQueue<>();
        Queue<Integer> wqueue = new PriorityQueue<>();
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
                    return new ArrayList<String>(Arrays.asList(path));
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
        ArrayList<String> path = this.shortestPath(start, destination);
        return path.get(this.exhaustMoves(path.size() - 1));
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
