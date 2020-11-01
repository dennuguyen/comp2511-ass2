package unsw.gloriaromanus.component;

public interface VictoryCondition {
    /**
     * Returns string containing name of condition
     * 
     * @return condition name
     */
    public String nameString();

    /**
     * Adds a condition to condition
     * 
     * @param expr condition to be added
     */
    public void add(VictoryCondition expr);

    /**
     * Evaluates condition
     * 
     * @return if condition if true
     */
    public Boolean evaluate();
}
