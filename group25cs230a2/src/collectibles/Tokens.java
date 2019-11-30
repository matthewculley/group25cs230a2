package collectibles;

public class Tokens extends Collectible {

	 protected int numTokens;

	public Tokens(int mapPosY, int mapPosX) { // dif constructor needed.
		super(mapPosY, mapPosX);

	}

	public int plusToken(int numTokens) {
		if (tokensCollected == true) {
			++numTokens;
		}
		return numTokens;

	}
}
