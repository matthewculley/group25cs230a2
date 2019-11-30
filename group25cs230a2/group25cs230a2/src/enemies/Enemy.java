package enemies;

import javafx.scene.image.Image;
import cells.*;
import game.*;

/**
 * Enemy is a superclass containing other enemy types,
 * the superclass stores data common between them such as position and the map
 * 
 */
public abstract class Enemy {

	Image sprite;
	private int x;
	private int y;	
	
	/**
	 * Enemy is a superclass containing other enemy types,
	 * the superclass stores data common between them such as position
	 * 
	 */
	public Enemy(int x, int y, String imageName) {
		sprite = new Image(imageName);
		setX(x);
		setY(y);
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	private void setX(int xPos) {
		x = xPos;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	private void setY(int yPos) {
		y = yPos;
	}
	
	public void setPosition(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public boolean checkValidMove(Map map, int x, int y) {
		if (map.getAt(x, y).getClass() == new Ground().getClass()) {
			return true;
		} 
		return false;
	}

	public void move(Map map) {
		System.out.println("s");

	}
	
}
