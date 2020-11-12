package unsw.gloriaromanus.terrain;

public class Population implements Populable {

    private final int MIN;
    private final int MAX;

    private int population;
    private int populationGrowth;

    public Population() {
        this(1000, 100, 0, 10000);
    }

    public Population(int population, int populationGrowth, int MIN, int MAX) {
        this.population = population;
        this.populationGrowth = populationGrowth;
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
    public int getPopulationGrowth() {
        return this.populationGrowth;
    }


    @Override
    public void setPopulation(int population) {
        this.population = limit(population);
    }

    @Override
    public void setPopulationGrowth(int rate) {
        this.populationGrowth = limit(rate);
    }

    @Override
    public void addPopulation(int change) {
        this.population = limit(this.population + change);
    }

    @Override
    public void addPopulationGrowth(int change) {
        this.populationGrowth = limit(this.populationGrowth + change);
    }

    @Override
    public void multiplyPopulationGrowth(int percentage) {
        this.populationGrowth =
                limit((int) (this.populationGrowth + this.populationGrowth * percentage / 100));
    }
}
