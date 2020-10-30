package unsw.gloriaromanus;

public interface UnitFactory {

    public enum Type {
        RomanLegionary(RomanLegionary.class);

        private Class<? extends Unit> type;

        private Type(Class<? extends Unit> type) {
            this.type = type;
        }

        public Class<? extends Unit> getUnitClass() {
            return this.type;
        }
    }

    public Unit recruit(UnitFactory.Type unitType);
}
