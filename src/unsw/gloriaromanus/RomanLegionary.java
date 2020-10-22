/**
 * Roman legionary is a type of unit
 */

package unsw.gloriaromanus;

public class RomanLegionary extends Unit implements MoveType {

    private final InfantryMoves moveType;

    /**
     * Roman Legionary has: infantry moves as movement type
     */
    public RomanLegionary() {
        this.moveType = new InfantryMoves();
    }

    @Override
    public int getMoves() {
        return this.moveType.getMoves();
    }
}
