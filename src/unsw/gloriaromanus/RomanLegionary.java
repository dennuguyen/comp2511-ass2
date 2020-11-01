/**
 * Roman legionary is a type of unit
 */

package unsw.gloriaromanus;

import unsw.gloriaromanus.component.Stats;
import unsw.gloriaromanus.component.Engageable;
import unsw.gloriaromanus.component.Move;

public class RomanLegionary extends Unit {

    /**
     * Roman Legionary constructor
     */
    public RomanLegionary(String spawn) {
        super(spawn, Move.Type.INFANTRY, Engageable.Type.Melee,
                new Stats(4, 8, 10, 6, 50, 10, 0, 5));
    }
}
