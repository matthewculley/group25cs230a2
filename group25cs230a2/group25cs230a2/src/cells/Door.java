package cells;

public class Door extends Cell{
	private String colour;
		
	public Door(String colour) {
		super("door.png", true);
	}
	
	public void checkPlayer() {
		
	}
	
	private void open() {
		//Change into ground tile
	}
}
