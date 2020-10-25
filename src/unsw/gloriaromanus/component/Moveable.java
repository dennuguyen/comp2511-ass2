/**
 * Moveable interface
 */

package unsw.gloriaromanus.component;

public interface Moveable {

    /**
     * Gets furthest reachable province towards the destination, returns the destination if it is
     * reachable.
     * 
     * @param destination Destination
     * @return Furthest reachable province
     */
    public String moveTo(String destination);
}
