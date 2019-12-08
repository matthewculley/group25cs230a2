package collectibles;

/**
 * A subclass that defines Flippers.
 * @author Evans, Ethan F
 * @version 1.1
 */

public class Flippers extends Collectible {

	/**
 	 * Constructor method that spawns the flippers in the origin (0,0).
 	 * @author 
	 */
	public Flippers() {
		super(0, 0, "flippers.png");
	}
	
	/**
 	 * Spawns in the specific coordinate.
 	 */
	public Flippers(int x, int y) {
		super(x, y, "flippers.png");
	}
	
	/**
 	 * Get the collectible name.
 	 * @return The collectible name: flippers.
 	 */
	@Override
	public String getCollectibleName() {
		return "flippers";
	}
}
