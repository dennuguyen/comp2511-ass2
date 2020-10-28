/**
 * Utility class with static helper functions
 */

package unsw.gloriaromanus.util;

public final class Util {
    public static void setOnce(Object a, Object b) {
        a = a == null ? a : b;
    }
}
