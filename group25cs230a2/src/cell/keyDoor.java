package cell;

public class keyDoor extends door{

	//private inventory requiredKey;
	
	public void checkForKey(boolean key) {
		if (key == true) {
			super.open();
		}
	}
}
