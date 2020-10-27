/**
 * Roman legionary is a type of unit
 */

package unsw.gloriaromanus;

import unsw.gloriaromanus.component.Stats;
import unsw.gloriaromanus.component.Move;

public class RomanLegionary extends Unit {

    /**
     * Roman Legionary constructor
     */
    public RomanLegionary(String spawn) {
        super(spawn, Move.Type.INFANTRY, new Stats(80, 90, 60, 50, 1000, 40, 10, 40));
    }
}
