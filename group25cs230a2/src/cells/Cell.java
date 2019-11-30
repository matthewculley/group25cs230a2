package cells;

import javafx.scene.image.Image;

/** 
 * (Incomplete)
 * @author 
 * @version 1.0
 */

public abstract class Cell {

	private Image sprite;
	
	public Cell(String imageName) {
		setSprite(imageName);
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public void setSprite(String imageName) {
		sprite = new Image(imageName);
	}
	

	
}
