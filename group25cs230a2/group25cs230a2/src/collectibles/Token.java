package collectibles;

public class Token extends Collectible {

	 protected int numTokens;

	public Token(int x, int y) { // dif constructor needed.
		super(x, y, "token.png");

	}

	public Token() {
		super("token.png");
	}

	@Override
	public String getCollectibleName() {
		return "token";
	}


}
