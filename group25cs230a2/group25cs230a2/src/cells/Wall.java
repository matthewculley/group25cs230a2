package cells;

/**
 * A class that defines Wall.
 * @author Group25
 * @version 1.1
 */

public class Wall extends Cell{
	
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
