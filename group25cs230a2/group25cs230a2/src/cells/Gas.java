package cells;


import java.util.Random;
import game.Map;

public class Gas extends Cell{


	private int x;
	private int y;
	
	public Gas(int x,int y) {
		super("gas.png", true);

		setX(x);
		setY(y);
	}
	

	
	public Gas() {
		super("gas.png", true);
	}


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
	
//	   System.out.println("returning null");
	}
	

	private void setX(int x) {
		this.x = x;		
	}

	private void setY(int y) {
		this.y = y;		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String getCellName() {
		return "gas";
	}
	
}
