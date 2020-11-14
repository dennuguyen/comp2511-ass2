package unsw.gloriaromanus.unit;

import java.util.ArrayList;
import java.util.Random;
import unsw.gloriaromanus.entity.Entity;

public class UnitComposite implements Entity, UnitComponent {

    private static final long serialVersionUID = 6503933741838584775L;
    private ArrayList<UnitComponent> units;

    public UnitComposite() {
        this.units = new ArrayList<UnitComponent>();
    }

    public int getNumUnits() {
        return this.units.size();
    }

    public void addUnit(UnitComponent component) {
        this.units.add(component);
        this.units.add((UnitComponent) (new RomanLegionary("s")));
    }

    public void removeUnit(UnitComponent component) {
        this.units.remove(component);
    }

    public UnitComponent getRandomUnit() {
        Random rand = new Random();
        int index = rand.nextInt(this.units.size());
        return this.units.get(index);
    }

    public boolean contains(UnitComponent component) {
        if (this.units.contains(component))
            return true;
        return false;
    }
}
