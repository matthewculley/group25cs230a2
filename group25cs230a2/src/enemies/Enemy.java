package enemies;

import map.*;
import cells.*

/**
 * Enemy is a superclass containing other enemy types,
 * the superclass stores data common between them such as position and the map
 * 
 */
public class Enemy {


	private int x;
	private int y;	
	private Map map;
	
	/**
	 * Enemy is a superclass containing other enemy types,
	 * the superclass stores data common between them such as position
	 * 
	 */
	public Enemy(int x, int y, Map map) {
		setX(x);
		setY(y);
		setMap(map);
	}
	
	/**
	 * @return the map
	 */
	protected Map getMap() {
		return map;
	}

	private void setMap(Map map) {
		this.map = map;
	}

	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	private void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	private void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the y
	 */
	protected boolean canTraverse(Cell cell) {
		return cell.traversable;
	}
	
	protected void move(char direction) {
		switch (direction) {
			case 'u' : setY(getY()-1);
			case 'd' : setY(getY()+1);
			case 'l' : setX(getX()-1);
			case 'r' : setX(getX()+1);
		}
	}
}
