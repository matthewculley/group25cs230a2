package cells;

import collectibles.*;
import player.*;

/**
 * A class that defines Water.
 * @author alan
 * @version 1.2
 */

public class Water extends Cell{
	
	/**
 	 * Constructs Water with the image name and passable.
 	 */
	public Water () {
		super("water.png", true);
	}	
	
	/**
 	 * A method that checks if the player have Flippers in their inventory.
 	 * @param p The player
	 * @return The result if the player have Flippers or not.
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
