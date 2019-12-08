package cells;

import javafx.scene.image.Image;
import player.Player;

/** 
 * A class that holds all basic features of cell type.
 * @author alan
 * @version 1.3
 */

public abstract class Cell {

	private Image sprite;
	private boolean passable;
	
	/**
 	 * Defines the image and passable of the cell.
 	 * @param imageName The png name of the cell.
	 * @param passable Decides whether the cell is passable or not.
 	 */
	public Cell(String imageName, boolean passable) {
		setSprite(imageName);
		setPassable(passable);
	}
	
	/**
 	 * Defines if the cell is passable.
	 * @param passable Decides whether the cell is passable or not.
 	 */
	public Cell(boolean passable) {
		setPassable(passable);
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
	 * @param sprite The new sprite image.
	 */
	public void setSprite(String imageName) {
		sprite = new Image(imageName);
	}

	/**
	 * Get the passable.
	 * @return The passable.
	 */
	public boolean isPassable() {
		return passable;
	}

	/**
	 * Set the passable.
	 * @param passable
	 */
	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	/**
 	 * Abstract method for getCellName, allows subclasses to override.
 	 */
	public abstract String getCellName();

	/**
	 * The goal is always deactivate.
	 * @return false
	 */
	public boolean isGoal() {
		return false;
	}
}
