package cells;

import javafx.scene.image.Image;

/** 
 * (Incomplete)
 * @author 
 * @version 1.0
 */

public class Cell {

	private Image sprite;
	
	public Cell(String imageName) {
		sprite = new Image(imageName);
	}
	
	public Image getSprite() {
		return sprite;
	}
	

	
}
