package cells;

import collectibles.*;
import player.*;

/**
 * A class that defines Water.
 * @author Group25
 * @version 1.1
 */

public class Water extends Cell{
	
	public Water () {
		super("water.png", true);
	}	
	
	/**
 	 * A method that checks if the player have Flippers in their inventory.
 	 * @param p The player
	 * @return True/False
 	 */
	
	public boolean isPassable(Player p) {
		if (p.getInventory().hasItem(new Flippers())) {
			return true;
		} else {
			return false;
		}
	}

	/**
 	 * Overrides the method getCellName.
 	 * @return The cell name: water
 	 */
	
	@Override
	public String getCellName() {
		return "water";
	}
}
