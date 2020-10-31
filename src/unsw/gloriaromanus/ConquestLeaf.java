package unsw.gloriaromanus;

public class ConquestLeaf extends Leaf  {

    public ConquestLeaf(Faction f){
        super(f);
    }

    @Override
    public Boolean evaluate(){
        // check Conquering all territories 
        return true;
    }
}