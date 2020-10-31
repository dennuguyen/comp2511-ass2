package unsw.gloriaromanus;

public interface Condition {
    void add(Condition expr);
    public Boolean evaluate();
}