package collectibles;

import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * A class that defines collectible.
 * @author Evans, Ethan F
 * @version 1.4
 */

public abstract class Collectible {
	
	private int x;
	private int y;
	private Image sprite;
	private boolean collected = false;

	/**
 	 * Constructor that spawns the collectible in the map.
 	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param imageName The image file name.
 	 */
	public Collectible(int x, int y, String imageName) {
		sprite = new Image(imageName);
		setX(x);
		setY(y);
	}
	
	/**
 	 * Abstract method for getCoolectibleName, can be override by subclass.
	 */
	public abstract String getCollectibleName();

	public Collectible(String imageName) {
		sprite = new Image(imageName);
	}

	/**
 	 * Set both x and y coordinate.
 	 * @param x The x coordinate.
	 * @param y The y coordinate.
 	 */
	public Collectible(int x, int y) {
		setX(x);
		setY(y);
	}

	/**
 	 * Set the x coordinate.
 	 * @param x The x coordinate. 
 	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
 	 * Get the x coordinate.
 	 * @return The x coordinate.
 	 */
	public int getX() {
		return x;
	}

	/**
 	 * Set the y coordinate.
 	 * @param y The y coordinate.
 	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
 	 * Get the y coordinate.
 	 * @return The y coordinate.
 	 */
	public int getY() {
		return y;
	}
	
	/**
 	 * Get the sprite of the collectible.
 	 * @return The sprite of the collectible.
 	 */
	public Image getSprite() {
		return sprite;
	}
	
	/**
 	 * Set the sprite of the collectible.
 	 * @param fileName The sprite file name.
 	 */
	public void setSprite(String fileName) {
		this.sprite = new Image(fileName);
	}

	/**
 	 * Checks if the collectible is collected.
 	 * @return True if collected, false otherwise.
	 */
	public boolean isCollected() {
		return collected;
	}

	/**
 	 * Set "collected" to true if collected.
 	 */
	public void collect() {
		this.collected = true;
	}
}
