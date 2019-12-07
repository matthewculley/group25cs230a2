package cells;

/**
 * A class that defines Goal.
 * @author Group25
 * @version 1.1
 */

public class Goal extends Cell{
	
	public Goal() {
		super("goal.png", true);
	}
	
	/**
         * Overwrites the method isGoal
         * @return true
         */
	
	@Override
	public boolean isGoal() {
		return true;
	}

	/**
  	 * Overwrites the method getCellName
	 * @return "goal"
 	 */
	
	@Override
	public String getCellName() {
		return "goal";
	}
}
