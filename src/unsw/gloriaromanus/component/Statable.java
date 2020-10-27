/**
 * Stats interface
 */

package unsw.gloriaromanus.component;

public interface Statable {

    public int getStat(Stats.Type type);

    public void setStat(Stats.Type type, int value);
}
