package cell;

public class teleporter extends cell{

	private teleporter partner;
	private int trigger = 0;
	
	public void checkIfTriggered() {
		if(trigger == 0) {
			teleportPlayer();
			trigger += 1;
		}
	}
	
	private void teleportPlayer() {
		
	}
}
