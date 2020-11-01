/**
 * Troop production building
 */

package unsw.gloriaromanus;

import java.util.ArrayList;

import unsw.gloriaromanus.component.Stats;

public class Camp implements Levyable {

    private ArrayList<Unit> recruits;

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
                this.recruits.add(unit);
            default:
                break;
        }
        return unit;
    }

    public void recruit() {
        if (this.recruits.size() == 0)
            return;

        this.recruits.get(0).addStat(Stats.Type.STRENGTH, 500);

        if (this.recruits.get(0).getStat(Stats.Type.STRENGTH) == 1000)
            this.recruits.remove(0);
    }
}
