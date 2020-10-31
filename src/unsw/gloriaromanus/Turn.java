/**
 * Implement of turnable interface
 * 
 * Turn is a singleton
 */
package unsw.gloriaromanus;

import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.Subject;

import java.util.ArrayList;

public class Turn implements Subject {

    private int turn = 0;
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    /**
     * Turn constructor
     */
    private Turn() {
    }

    /**
     * Bill Pugh singleton
     */
    private static class BillPughTurn {
        private static final Turn turnSingleton = new Turn();
    }

    /**
     * Gets the instance of turn
     * 
     * @return Turn singleton
     */
    public static Turn getInstance() {
        return BillPughTurn.turnSingleton;
    }

    /**
     * Gets the current turn
     * 
     * @return Current turn
     */
    public int getTurn() {
        return this.turn;
    }

    /**
     * Increment the turn
     */
    public void incrementTurn() {
        this.turn++;
        System.out.println("Turn: " + this.turn);
        tell();
    }

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Detach an observer from the subject's observer list
     * 
     * @param observer Observer object to detach
     */
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    /**
     * Notify all of the subject's observers of an event
     */
    public void tell() {
        for (Observer observer : observers)
            observer.update(this);
    }
}
