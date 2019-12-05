package cells;

public class TokenDoor extends Cell{

	private int tokenReq;
	//How many tokens required to open??
	public TokenDoor(int tokens) {
		super("tokenDoor.png", false);
		tokenReq = tokens;
	}
	
	public TokenDoor() {
		super("tokenDoor.png", false);
	}

	public int getNeededTokens() {
		return tokenReq;
	}

	@Override
	public String getCellName() {
		
		return "door:token:" + getNeededTokens();
	}
	
}
