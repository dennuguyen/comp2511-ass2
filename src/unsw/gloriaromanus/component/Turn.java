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

    private int turn = 0;
    private ArrayList<Observer> observers;

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
