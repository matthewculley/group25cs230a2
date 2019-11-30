package cells;

import collectibles.*;
import player.*;


public class Water extends Cell{
	public Water () {
		super("water.png", false);
	}	
	
	public boolean isPassable(Player p) {
		System.out.print("hey fuck me im a cunt");
		if (p.getInventory().hasItem(new Flippers())) {
			return true;
		} else {
			return false;
		}
	}
}
