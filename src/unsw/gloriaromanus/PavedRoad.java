package unsw.gloriaromanus;

public class PavedRoad implements RoadType {

    private int moves;

    public PavedRoad() {
        this.moves = 2;
    }

    public int getMoves() {
        return this.moves;
    }
}
