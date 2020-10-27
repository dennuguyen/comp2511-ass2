/**
 * Convenient container for grouping pairs of objects
 */

package unsw.gloriaromanus.util;

public class Pair<K, V> {

    public K first;
    public V second;

    /**
     * Pair constructor
     * 
     * @param first  First element of pair
     * @param second Second element of pair
     */
    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Static method for conveniently creating pairs
     * 
     * @param first  First element of pair
     * @param second Second element of pair
     * @return Created pair with first and second
     */
    public static <K, V> Pair<K, V> of(K first, V second) {
        return new Pair<K, V>(first, second);
    }
}
