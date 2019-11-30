package cells;

import javafx.scene.image.Image;
import player.Player;

/** 
 * (Incomplete)
 * @author 
 * @version 1.0
 */

public abstract class Cell {

	private Image sprite;
	private boolean passable;
	
	public Cell(String imageName, boolean passable) {
		setSprite(imageName);
		setPassable(passable);
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public void setSprite(String imageName) {
		sprite = new Image(imageName);
	}

	public boolean isPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}



	
}
