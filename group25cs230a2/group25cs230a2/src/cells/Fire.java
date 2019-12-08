package cells;

/**
 * A class that defines Fire.
 * @author Group25
 * @version 1.2
 */

public class Fire extends Cell {
	
	/**
 	 * Sets the image and passable of Fire.
 	 */
	
	public Fire() {
		super("fire.png", true);
	}

	/**
 	 * Overrides the method getCellName.
 	 * @return The cell name: fire.
 	 */
	
	@Override
	public String getCellName() {
		return "fire";
	}
}
