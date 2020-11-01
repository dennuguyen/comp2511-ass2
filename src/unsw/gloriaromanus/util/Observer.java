/**
 * Observer interface of observer pattern
 */

package unsw.gloriaromanus.util;

public interface Observer {
    /**
     * Receive event information from subject
     * 
     * @param subject Subject object that triggered event
     */
    public void update(Subject subject);
}
