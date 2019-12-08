package collectibles;

import java.util.ArrayList;

import javafx.scene.image.Image;

public abstract class Collectible {
	private int x;
	private int y;
	private Image sprite;
	private boolean collected = false;

	public Collectible(int x, int y, String imageName) {
		sprite = new Image(imageName);
		setX(x);
		setY(y);
	}
	
	public abstract String getCollectibleName();

	public Collectible(String imageName) {
		sprite = new Image(imageName);
	}

	public Collectible(int x, int y) {
		setX(x);
		setY(y);
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public void setSprite(String fileName) {
		this.sprite = new Image(fileName);
	}

	public boolean isCollected() {
		return collected;
	}

	public void collect() {
		this.collected = true;
		
	}

}
