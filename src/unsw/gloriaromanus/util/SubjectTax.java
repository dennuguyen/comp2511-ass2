/**
 * Subject interface of observer pattern
 */

package unsw.gloriaromanus.util;

public interface SubjectTax {
    /**
     * Attach an observer to the subject's observer list
     * 
     * @param observer Observer object to attach
     */
    public void attach(ObserverTax observer);

    /**
     * Detach an observer from the subject's observer list
     * 
     * @param observer Observer object to detach
     */
    public void detach(ObserverTax observer);

    /**
     * Notify all of the subject's observers of an event
     */
    public void tell();
}
