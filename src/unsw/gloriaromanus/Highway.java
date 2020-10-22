package unsw.gloriaromanus;

public class Highway implements RoadType {

    private int moves;

    public Highway() {
        this.moves = 1;
    }

    public int getMoves() {
        return this.moves;
    }
}
