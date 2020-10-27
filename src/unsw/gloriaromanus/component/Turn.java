/**
 * Implement of turnable interface
 * 
 * Turn is a singleton
 */
package unsw.gloriaromanus.component;

import java.util.ArrayList;
import unsw.gloriaromanus.util.Observer;
import unsw.gloriaromanus.util.Subject;

public class Turn implements Turnable, Subject {

    private static final Turn turnSingleton = new Turn();

    private int turn = 0;
    private ArrayList<Observer> observers;

    /**
     * Turn constructor
     */
    public Turn() {
    }

    /**
     * Gets the instance of turn
     * 
     * @return Turn singleton
     */
    public static Turn getInstance() {
        return Turn.turnSingleton;
    }

    /**
     * Gets the current turn
     * 
     * @return Current turn
     */
    public int getTurn() {
        return this.turn;
    }

    @Override
    public void nextTurn() {
        this.turn++;
        this.tell();
    }

    @Override
    public void attach(Observer observer) {
        if (!this.observers.contains(observer))
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
