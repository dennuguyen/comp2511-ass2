package unsw.gloriaromanus.terrain;

public interface Populable {

    /**
     * Gets the population
     * 
     * @return Population
     */
    public int getPopulation();

    /**
     * Gets the population growth
     * 
     * @return Population growth
     */
    public int getPopulationGrowth();

    /**
     * Sets the population
     * 
     * @param population
     */
    public void setPopulation(int population);

    /**
     * Sets the population growth rate
     * 
     * @param rate
     */
    public void setPopulationGrowth(int rate);

    /**
     * Adds to population
     * 
     * @param change
     */
    public void addPopulation(int change);

    /**
     * Adds population growth rate
     * 
     * @param change
     */
    public void addPopulationGrowth(int change);

    /**
     * Multiplies population growth rate
     * 
     * @param percentage
     */
    public void multiplyPopulationGrowth(int percentage);
}
