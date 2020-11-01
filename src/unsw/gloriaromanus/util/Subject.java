/**
 * Subject interface of observer pattern
 */

package unsw.gloriaromanus.util;

public interface Subject {
    /**
     * Attach an observer to the subject's observer list
     * 
     * @param observer Observer object to attach
     */
    public void attach(Observer observer);

    /**
     * Detach an observer from the subject's observer list
     * 
     * @param observer Observer object to detach
     */
    public void detach(Observer observer);

    /**
     * Notify all of the subject's observers of an event
     */
    public void tell();
}
