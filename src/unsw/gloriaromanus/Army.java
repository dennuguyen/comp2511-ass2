package unsw.gloriaromanus;

import java.util.ArrayList;

import unsw.gloriaromanus.util.Component;

public class Army implements Component {

    ArrayList<Component>  units = new ArrayList<Component>();

    public void add(Component unit) {
		units.add(unit);
	}

	public void remove(Component unit) {
		units.remove(unit);
	}
}
