/**
 * Engageable interface
 */

package unsw.gloriaromanus.warfare;

public interface Engageable {
    public enum Type {
        Melee, Missile;
    }

    public Engageable.Type getEngageType();
}
