package unsw.gloriaromanus.component;

public class Treasury implements Treasurial {

    private int treasury;

    @Override
    public int getTreasury() {
        return this.treasury;
    }

    @Override
    public void addTreasury(int amount) {
        this.treasury += amount;
    }
}
