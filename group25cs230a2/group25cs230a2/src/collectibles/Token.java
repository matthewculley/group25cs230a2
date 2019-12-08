package collectibles;

/**
 * A subclass that defines Token.
 * @author Lloyd, Ethan F
 * @version 1.2
 */

public class Token extends Collectible {

	protected int numTokens;

	/**
	 * Constructor method that spawns the token in specific coordinate.
 	 * @param x The x coordinate.
	 * @param y The y coordinate.
 	 */
	public Token(int x, int y) { // dif constructor needed.
		super(x, y, "token.png");

	}

	/**
 	 * Update the image.
 	 */
	public Token() {
		super("token.png");
	}

	/**
 	 * Overrides the method getCollectibleName.
 	 * @return The collectible name: token.
 	 */
	@Override
	public String getCollectibleName() {
		return "token";
	}
}
