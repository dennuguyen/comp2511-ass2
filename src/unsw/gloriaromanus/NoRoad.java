package unsw.gloriaromanus;

public class NoRoad implements RoadType {

    private int moves;

    public NoRoad() {
        this.moves = 4;
    }

    public int getMoves() {
        return this.moves;
    }
}
