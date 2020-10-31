package unsw.gloriaromanus;

public abstract class Leaf implements Expression {
    
    public String name;

    public Leaf(String name){
        this.name = name;
    }

    @Override
    public void add(Expression expr){ 
    }


    @Override
    public Boolean evaluate(){ 
        if (name.equals("CONQUEST")) {
            return
        }
        if (name.equals("WEALTH")) {
            return
        }
        if (name.equals("TREASURY")) {
            return
        }
    }
}
