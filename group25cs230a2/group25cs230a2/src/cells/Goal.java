package cells;

/**
 * A class that defines Goal.
 * @author Group25
 * @version 1.3
 */

public class Goal extends Cell{
	
	/**
 	 * Sets the image and passable of Goal.
	 */
	
	public Goal() {
		super("goal.png", true);
	}
	
	/**
         * Overrides the method isGoal.
         * @return true.
         */
	
	@Override
	public boolean isGoal() {
		return true;
	}

	/**
  	 * Overrides the method getCellName.
	 * @return The cell name: goal.
 	 */
	
	@Override
	public String getCellName() {
		return "goal";
	}
}
