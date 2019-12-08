package cells;

/**
 * A class that defines Wall.
 * @author Group25
 * @version 1.3
 */

public class Wall extends Cell {
	
	/**
 	 * Constructs the Wall with the image name and passable.
 	 */
	
	public Wall () {
		super("wall.png", false);
	}
	
	/**
 	 * Overrides the method getCellName.
	 * @return The cell name: wall.
 	 */

	@Override
	public String getCellName() {
		return "wall";
	}
}
