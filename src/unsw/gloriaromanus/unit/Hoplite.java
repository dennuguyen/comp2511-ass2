/**
 * Roman legionary is a type of unit
 */

package unsw.gloriaromanus.unit;

import unsw.gloriaromanus.movement.Move;
import unsw.gloriaromanus.warfare.Engageable;
import unsw.gloriaromanus.stats.Stats;

public class Hoplite extends UnitLeaf {

    private static final long serialVersionUID = -8275752041064187854L;

    /**
     * Roman Legionary constructor
     */
    public Hoplite(String spawn) {
        super(spawn, Move.Type.INFANTRY, Engageable.Type.Melee,
                new Stats(3, 6, 10, 1, 50, 7, 0, 4));
    }
}