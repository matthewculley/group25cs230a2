package enemies;

import javafx.scene.image.Image;
import cells.*;
import game.*;

/**
 * Enemy is a superclass containing other enemy types,
 * the superclass stores data common between them such as position and the map
 * @author ethan J
 * @version 1.3
 */

public abstract class Enemy {

	Image sprite;
	private int x;
	private int y;	
	
	/**
	 * Constructs a enemy in the specified coordinate.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 */
	public Enemy(int x, int y) {
		setX(x);
		setY(y);
	}
	
	/**
 	 * Get the sprite.
 	 * @return The sprite.
 	 */
	public Image getSprite() {
		return sprite;
	}
	
	/**
 	 * Set the sprite.
 	 * @param sprite The sprite name.
 	 */
	protected void setSprite(String sprite) {
		this.sprite = new Image(sprite);
	}

	/**
 	 * Abstract method for getEnemyName, allows subclasses to override.
 	 */
	public abstract String getEnemyName();
	
	/**
	 * Get the x coordinate.
	 * @return The x coordinate.
	 */
	public int getX() {
		return x;
	}

	private void setX(int xPos) {
		x = xPos;
	}

	/**
	 * Get the y coordinate.
	 * @return The y coordinate.
	 */
	public int getY() {
		return y;
	}

	private void setY(int yPos) {
		y = yPos;
	}
	
	/**
 	 * Sets the positiion.
 	 * @param x The x coordinate.
	 * @param y The y coordinate.
 	 */
	public void setPosition(int x, int y) {
		setX(x);
		setY(y);
	}
	
	/**
 	 * Checks if the enemy is making a valid move.
 	 * @param map The map.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @return The result that determines the enemy is making a valid move.
 	 */
	public boolean checkValidMove(Map map, int x, int y) {
		if(!map.isValidCoords(x, y)) {
			return false;
		}
		Class<? extends Cell> cell = map.getAt(x, y).getClass();
		if (cell != null) {
			if (cell == new Ground().getClass()) {
				return true;
			}
		}
		return false;
	}

	/**
 	 * 
 	 * @param map The map
 	 */
	public void move(Map map) {

	}
	
}
