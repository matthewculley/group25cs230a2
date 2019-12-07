package collectibles;

public class Key extends Collectible {
	private String colour;
	
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

	public Key(String colour) {
		super(colour + "key.png");
		
	}

	@Override
	public String getCollectibleName() {
		// TODO Auto-generated method stub
		return "key:" + getColour();
	}

	private String getColour() {
		return colour;
	}
}
