package unsw.gloriaromanus;

public interface Expression {
    void add(Expression expr);
    Boolean evaluate();
}