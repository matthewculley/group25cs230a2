package cells;

import javafx.scene.image.Image;
import player.Player;

/** 
 * A class that holds basic features of all cell type.
 * @author Group 25
 * @version 1.0
 */

public abstract class Cell {

	private Image sprite;
	private boolean passable;
	
	public Cell(String imageName, boolean passable) {
		setSprite(imageName);
		setPassable(passable);
	}
	
	public Cell(boolean passable) {
		setPassable(passable);
	}

	/**
	* 
	* @return false
	*/
	
	public boolean Cell() {
		return false;
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
	* @param 
	*/
	
	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	public abstract String getCellName();

	/**
	* Get the goal.
	* @return false
	*/
	
	public boolean isGoal() {
		return false;
	}
}
