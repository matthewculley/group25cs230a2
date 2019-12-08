package collectibles;

/**
 * A subclass that defines GasMask.
 * @author Evans
 * @version 1.2
 */

public class GasMask extends Collectible {

	/**
 	 * Constructor method that spawns the gas mask in the specific coordinate.
 	 * @param x The x coordinate.
	 * @param y The y coordinate.
 	 */
	public GasMask(int x, int y) {
		super(x, y, "gasMask.png");
	}

	/**
 	 * Spawns the gas mask in the origin (0,0).
 	 */
	public GasMask() {
		super("gasMask.png");
	}

	/**
 	 * Overrides the method getCollectibleName.
 	 * @return The collectible name: gasMask.
 	 */
	@Override
	public String getCollectibleName() {
		return "gasMask";
	}
}
