/**
 * Implement of turnable interface
 * 
 * Turn is a singleton
 */
package unsw.gloriaromanus.system;

import unsw.gloriaromanus.entity.Entity;
import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.Subject;

import java.util.ArrayList;

public class Turn implements Entity, Subject {

    private static final long serialVersionUID = 8264103827362941942L;
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
        tell();
    }

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void tell() {
        for (Observer observer : observers)
            observer.update(this);
    }
}
