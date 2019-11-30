package player;

import javafx.scene.image.Image;
import collectibles.*;

public class Player {
	private Profile profile;
	private Inventory inventory;
	private Image sprite;
	private int x;
	private int y;
	
	public Player(Profile p, int x, int y) {
		this.profile = p;
		this.inventory = new Inventory();
		this.x = x;
		this.y = y;
		if (p.getAvatar() != null) {
			sprite = p.getAvatar();
		} else {
			sprite = new Image("flippers.png");
		}
	}
	
	public void collect(Collectible c) {
		inventory.addItem(c);
		c.collect();
	}

	public Profile getProfile() {
		return profile;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}