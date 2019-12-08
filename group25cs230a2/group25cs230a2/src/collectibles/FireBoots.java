package collectibles;

/**
 * A subclass that defines FireBoots.
 * @author Evans, Ethan F
 * @version 1.1
 */

public class FireBoots extends Collectible {

	/**
 	 * Constrcutor method that spawns the fire boots in the specific coordinate.
 	 * @param x The x coordinate.
	 * @param y The y coordinate.
 	 */
	public FireBoots(int x, int y) {
		super(x, y, "fireBoots.png");
	}
	
	/**
 	 * Spawns the fire boots in the origin (0,0).
 	 */
	public FireBoots() {
		super(0, 0, "fireBoots.png");
	}

	/**
 	 * Get the collectible name.
 	 * @return The collectible name: fireBoots.
 	 */
	@Override
	public String getCollectibleName() {
		return "fireBoots";
	}
}
