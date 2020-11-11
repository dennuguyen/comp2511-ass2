/**
 * Unit factory
 */

package unsw.gloriaromanus.unit;

public interface Levyable {

    public enum Type {
        RomanLegionary(RomanLegionary.class);

        private Class<? extends UnitLeaf> type;

        private Type(Class<? extends UnitLeaf> type) {
            this.type = type;
        }

        public Class<? extends UnitLeaf> getUnitClass() {
            return this.type;
        }
    }

    public UnitLeaf enlist(Levyable.Type unitType);
}
