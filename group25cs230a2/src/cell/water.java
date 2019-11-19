package cell;

public class water extends obstacles{

	private inventory inv;
	
	public void check() {
		if(inv.hvFlip() == true) {
			// Turn obstacle into ground type
		}
	}
}
