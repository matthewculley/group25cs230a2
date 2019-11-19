package cell;

public class goal extends cell{

	private levelController lvl;
	private int trigger = 0;
	
	public void checkIfTriggered() {
		if (trigger == 0) {
			trigger = 1;
			endLevel();
		}
	}
	
	private void endLevel() {
		lvl.nextLevel();
	}
}
