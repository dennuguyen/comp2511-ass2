/**
 * Turnable interface must be used in an observer pattern
 */

package unsw.gloriaromanus.component;

public interface Turnable {

    /**
     * Notifies observers of turnable of turn increment
     */
    public void nextTurn();
}
