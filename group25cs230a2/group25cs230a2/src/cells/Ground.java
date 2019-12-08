package cells;

/**
 * A class that defines Ground.
 * @author alan
 * @version 1.4
 */

public class Ground extends Cell {
	
	/**
 	 * Sets the image and passable of Ground.
 	 */
	public Ground() {
		super("ground.png", true);
	}

	/**
 	* Overrides the method getCellName.
 	* @return The cell name: ground.
 	*/
	@Override
	public String getCellName() {
		return "ground";
	}
}
