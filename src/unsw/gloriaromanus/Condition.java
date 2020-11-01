package unsw.gloriaromanus;

public interface Condition {
    public String nameString();
    public void add(Condition expr);
    public Boolean evaluate();
}