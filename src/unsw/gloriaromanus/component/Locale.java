/**
 * Implementation of Locable interface
 */

package unsw.gloriaromanus.component;

public class Locale implements Locable {

    private String location;

    public Locale(String spawn) {
        location = new String(spawn);
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public String setLocation(String location) {
        this.location = location;
        return this.location;
    }
}
