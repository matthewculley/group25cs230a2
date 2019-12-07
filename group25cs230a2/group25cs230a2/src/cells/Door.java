package cells;

public class Door extends Cell{
	private String colour;
		
	public Door() {
		super(false);
	}
	public Door(boolean passable, String colour) {
		super(passable);
		switch (colour) {
			case "red":
				this.colour = "red";
				setSprite("redDoor.png");
				break;
			case "blue":
				this.colour = "blue";
				setSprite("blueDoor.png");
				break;
			case "green":
				this.colour = "green";
				setSprite("greenDoor.png");
				break;
			case "yellow":
				this.colour = "yellow";
				setSprite("yellowDoor.png");
				break;
			default: 
				break;
		}
	}
	public String toString() {
		return "door:" + getColour() + "Open: " + isPassable();
	}
	public String getColour() {
		// TODO Auto-generated method stub
		return colour;
	}
	@Override
	public String getCellName() {
		return "door:" + getColour();
	}
}
