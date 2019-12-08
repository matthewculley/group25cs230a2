package collectibles;

/**
 * A subclass that defines Key.
 * @author Lloyd
 * @version 1.2
 */

public class Key extends Collectible {
	
	private String colour;
	
	/**
	  * Constructor method that spawns the key in specific colour and coordinate.
	  * @param x The x coordinate.
	  * @param y The y coorinate.
	  * @param colour The colour of the key.
	  */
	public Key(int x, int y, String colour) {
		super(x, y);
		switch (colour) {
			case "red":
				this.colour = "red";
				setSprite("redKey.png");
				break;
			case "blue":
				this.colour = "blue";
				setSprite("blueKey.png");
				break;
			case "green":
				this.colour = "green";
				setSprite("greenKey.png");
				break;
			case "yellow":
				this.colour = "yellow";
				setSprite("yellowKey.png");
				break;
			default: 
				break;
		}
	}

	/**
 	 * Update the image of the key.
 	 * @param colour The colour of the key
 	 */
	public Key(String colour) {
		super(colour + "key.png");
		this.colour = colour;
	}

	/**
 	 * Overrides the method getCollectibleName.
 	 * @return The collectible name: key and the colour 
 	 */
	@Override
	public String getCollectibleName() {
		// TODO Auto-generated method stub
		return "key:" + getColour();
	}

	/**
 	 * Get the colour of th key.
 	 * @return The colour of the key
 	 */
	public String getColour() {
		return colour;
	}
}
