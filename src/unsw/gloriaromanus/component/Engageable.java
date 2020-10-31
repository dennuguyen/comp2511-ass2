/**
 * Engageable interface
 */

package unsw.gloriaromanus.component;

public interface Engageable {
    public enum Type {
        Melee, Missile;
    }

    public Engageable.Type getType();
}
