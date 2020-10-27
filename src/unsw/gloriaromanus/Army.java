package unsw.gloriaromanus;

import java.util.ArrayList;
import java.util.Random;

import unsw.gloriaromanus.util.Component;

public class Army implements Component {

    ArrayList<Component>  children = new ArrayList<Component>();

    public int getNumUnits() {
        return children.size();
    }

    public Component getRandomUnit() {
        Random rand = new Random();
        int index = rand.nextInt(getNumUnits());
        Component child = children.get(index);
        if (child instanceof Army) 
            ((Army)child).getRandomUnit();
        return child;
    }

    public void add(Component unit) {
		children.add(unit);
	}

	public void remove(Component unit) {
		children.remove(unit);
    }
}
