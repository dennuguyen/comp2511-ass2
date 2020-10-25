/**
 * Roman legionary is a type of unit
 */

package unsw.gloriaromanus;

public class RomanLegionary extends Unit implements Moveable, Locable {

    private final Locale locale;
    private final Move move;

    /**
     * Roman Legionary
     */
    public RomanLegionary(String spawn) {
        this.locale = new Locale(spawn);
        this.move = new Move(Move.infantryMoveCapacity);
    }

    @Override
    public String getLocation() {
        return this.locale.getLocation();
    }

    @Override
    public String setLocation(String location) {
        return this.locale.setLocation(location);
    }

    @Override
    public String moveTo(String destination) {
        return this.locale
                .setLocation(this.move.moveToImple(this.locale.getLocation(), destination));
    }
}
