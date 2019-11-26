/**
 * Inventory.java
 * 
 * @author
 */

public class Inventory {

	private int token;
	private boolean redKey;
	private boolean blueKey;
	private boolean greenKey;
	private boolean fireBoots;
	private boolean flippers;
	private boolean gasMask;
	private boolean shield;

	/*
	 * Player's inventory has a token counter and checks if the keys or other
	 * collectibles have been picked up.
	 */
	private Inventory(int token, boolean redKey, boolean blueKey, boolean greenKey, boolean fireBoots, boolean flippers,
			boolean gasMask, boolean shield) {

	}

	public int tokenNum() {
		return this.token;
	}

	public boolean hvRed() {
		return redKey;
	}

	public boolean hvBlue() {
		return blueKey;
	}

	public boolean hvGreen() {
		return greenKey;
	}

	public boolean hvFire() {
		return fireBoots;
	}

	public boolean hvFlip() {
		return flippers;
	}

	public boolean hvGas() {
		return gasMask;
	}

	public boolean hvShield() {
		return shield;
	}

	public boolean hasItem() {

		// not sure what to put in here
		// Inventory.getItem()
	}

}
