/**
 * Troop production building
 */

package unsw.gloriaromanus.building;

import java.util.ArrayList;

import unsw.gloriaromanus.stats.Stats;
import unsw.gloriaromanus.unit.Levyable;
import unsw.gloriaromanus.unit.RomanLegionary;
import unsw.gloriaromanus.unit.UnitLeaf;

public class Camp implements Levyable {

    private ArrayList<UnitLeaf> recruits;

    public Camp() {
        this.recruits = new ArrayList<UnitLeaf>();
    }

    @Override
    public UnitLeaf enlist(Levyable.Type unitType) {
        return null;
    }

    public UnitLeaf enlist(Levyable.Type unitType, String locale) {
        UnitLeaf unit = null;
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
