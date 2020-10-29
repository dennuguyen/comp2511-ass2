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
        super(spawn, Move.Type.INFANTRY, new Stats(4, 8, 10, 6, 50, 10, 1000, 5));
    }
}
