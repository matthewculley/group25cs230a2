package cells;
import game.PlayGame;

public class Goal extends Cell{

	private int trigger = 0;

	public Goal() {
		super("goal.png",true);
	}
	
	
	public void checkIfTriggered() {
		if (trigger == 0) {
			trigger = 1;
			endLevel();
		}
	}
	
	private void endLevel() {
		PlayGame.nextLevel();
	}
}
