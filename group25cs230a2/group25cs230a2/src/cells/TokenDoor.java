package cells;

/**
 * A class that defines TokenDoor.
 * @author Group25
 * version 1.1
 */

public class TokenDoor extends Cell{

	private int tokenReq;
	
	public TokenDoor(int tokens) {
		super("tokenDoor.png", false);
		tokenReq = tokens;
	}
	
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
