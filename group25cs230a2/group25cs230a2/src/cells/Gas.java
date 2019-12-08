package cells;

import java.util.Random;
import game.Map;

/**
 * A class that defines Gas.
 * @author alan
 * @version 1.3
 */

public class Gas extends Cell {

	private int x;
	private int y;
	
	/**
 	 * Constructs Gas in the specific coordinate and set the image name and passable.
 	 * @param x The x coordinate.
	 * @param y The y coordinate.
 	 */
	public Gas(int x,int y) {
		super("gas.png", true);
		setX(x);
		setY(y);
	}
	
	/**
 	 * Constructs Gas without coordinate.
 	 */
	public Gas() {
		super("gas.png", true);
	}

	/**
 	 * A method that makes the gas spread.
 	 * @param map The map
 	 */
	public void infect(Map map) {
		Gas newGas;
		//add gas right
		if(map.getAt(x + 1, y).getClass() == new Ground().getClass() && !map.gasExists(x + 1, y)) {
			newGas = new Gas(getX() + 1, getY());
			map.addGas(newGas);
		}
		//left
		if(map.getAt(x - 1, y).getClass() == new Ground().getClass() && !map.gasExists(x - 1, y)) {
			newGas = new Gas(getX() - 1, getY());
			map.addGas(newGas);

		}
		//down
		if(map.getAt(x, y + 1).getClass() == new Ground().getClass() && !map.gasExists(x, y + 1)) {
			newGas = new Gas(getX(), getY() + 1);
			map.addGas(newGas);

		}
		//up
		if(map.getAt(x, y - 1).getClass() == new Ground().getClass()&&!map.gasExists(x, y - 1)) {
			newGas = new Gas(getX(), getY() - 1);
			map.addGas(newGas);

		}
	//System.out.println("returning null");
	}
	
	private void setX(int x) {
		this.x = x;		
	}

	private void setY(int y) {
		this.y = y;		
	}

	/**
 	 * Get the x coordinate.
 	 * @return The x coordinate.
 	 */
	public int getX() {
		return x;
	}

	/**
 	 * Get the y coordinate.
 	 * @return The y coordinate.
 	 */
	public int getY() {
		return y;
	}

	/**
 	 * Overrides the method getCellName.
 	 * @return The cell name: gas.
 	 */
	@Override
	public String getCellName() {
		return "gas";
	}
}