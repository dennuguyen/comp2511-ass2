/**
 * Troop production building
 */

package unsw.gloriaromanus;

import java.util.ArrayList;

public class Camp implements Levyable {

    ArrayList<Unit> recruits;

    public Camp() {
        this.recruits = new ArrayList<Unit>();
    }

    @Override
    public Unit enlist(Levyable.Type unitType) {
        return null;
    }

    public Unit enlist(Levyable.Type unitType, String locale) {
        Unit unit = null;
        switch (unitType) {
            case RomanLegionary:
                unit = new RomanLegionary(locale);
            default:
                break;
        }
        return unit;
    }
}
