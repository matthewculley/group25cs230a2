package cells;

/**
 * A class that defines TokenDoor.
 * @author Group25
 * @version 1.3
 */

public class TokenDoor extends Cell {

	private int tokenReq;
	
	/**
 	 * Constructs the TokenDoor with the image name and passable.
 	 * @param tokens The required tokens to unlock the door.
 	 */
	
	public TokenDoor(int tokens) {
		super("tokenDoor.png", false);
		tokenReq = tokens;
	}
	
	/**
 	 * Constructs the TokenDoor without the required tokens.
 	 */
	
	public TokenDoor() {
		super("tokenDoor.png", false);
	}

	/**
 	 * Get the required tokens.
 	 * @return Amount of token required.
 	 */
	
	public int getNeededTokens() {
		return tokenReq;
	}

	/**
 	 * Overrides the method getCellName.
 	 * @return The cell name: door:token and the required tokens.
 	 */
	
	@Override
	public String getCellName() {
		return "door:token:" + getNeededTokens();
	}
}
