/**
 * Roman legionary is a type of unit
 */

package unsw.gloriaromanus.unit;

import unsw.gloriaromanus.movement.Move;
import unsw.gloriaromanus.warfare.Engageable;
import unsw.gloriaromanus.stats.Stats;

public class RomanLegionary extends UnitLeaf {

    private static final long serialVersionUID = -8275752041064187854L;

    /**
     * Roman Legionary constructor
     */
    public RomanLegionary(String spawn) {
        super(spawn, Move.Type.INFANTRY, Engageable.Type.Melee,
                new Stats(4, 8, 10, 6, 50, 10, 0, 5));
    }
}