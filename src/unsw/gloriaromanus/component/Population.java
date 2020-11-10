package unsw.gloriaromanus.component;

public class Population implements Populable {

    private int population;
    private final int MIN;
    private final int MAX;

    public Population() {
        this(1000, 0, 10000);
    }

    public Population(int population, int MIN, int MAX) {
        this.population = population;
        this.MIN = MIN;
        this.MAX = MAX;
    }

    /**
     * Hard limiter on stat value
     * 
     * @param value Value to check
     * @return Limited value
     */
    private int limit(int value) {
        if (value < MIN)
            value = MIN;
        if (value > MAX)
            value = MAX;
        return value;
    }

    @Override
    public int getPopulation() {
        return this.population;
    }

    @Override
    public void setPopulation(int population) {
        this.population = limit(population);
    }

    @Override
    public void addPopulation(int change) {
        this.population = limit(this.population + change);
    }
}
