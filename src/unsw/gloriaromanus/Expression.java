package unsw.gloriaromanus;

public interface Expression {
    void add(Expression expr);
    public Boolean evaluate();
}