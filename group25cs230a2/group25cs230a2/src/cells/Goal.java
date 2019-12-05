package cells;

public class Goal extends Cell{
	
	public Goal() {
		super("teleporter.png", true);
	}
	
	@Override
	public boolean isGoal() {
		return true;
	}

	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		return "goal";
	}

	
//	public void checkIfTriggered() {
//		if (trigger == 0) {
//			trigger = 1;
//			endLevel();
//		}
//	}
//	
//	private void endLevel() {
//		lvl.nextLevel();
//	}
}
