package cells;

/**
 * A class that holds all the basic features of doors.
 * @author Group25
 * @version 1.3
 */

public class Door extends Cell {
	
	private String colour;
	
	/**
 	 * Always false.
 	 */
	
	public Door() {
		super(false);
	}
	
	/**
 	 * Constructs the door with colour and passable.
 	 * @param passable Decides if the door is passable.
	 * @param colour The colour of the door.
 	 */
	
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
	
	/**
 	 * Prints the colour of the door.
 	 * @return The colour of the door and is it passable.
 	 */
	
	public String toString() {
		return "door:" + getColour() + "Open: " + isPassable();
	}
	
	/**
 	 * Get the colour.
 	 * @return The colour
 	 */
	
	public String getColour() {
		return colour;
	}
	
	/**
 	 * Overrides the method getCellName.
 	 * @return The door colour.
 	 */
	
	@Override
	public String getCellName() {
		return "door:" + getColour();
	}
}
