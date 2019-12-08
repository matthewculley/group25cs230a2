package cells;

/**
 * A class that defines Ground.
 * @author Group25
 * @version 1.2
 */

public class Ground extends Cell {
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
