package unsw.gloriaromanus;

public class RomanLegionary extends Unit implements MoveType {

    private final InfantryMoves moveType;

    public RomanLegionary() {
        this.moveType = new InfantryMoves();
    }

    @Override
    public int getMoves() {
        return this.moveType.getMoves();
    }
}
