package cells;

public class Goal extends Cell{

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
