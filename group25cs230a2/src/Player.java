import javafx.scene.image.Image;

public class Player {
	private Profile profile;
	private Inventory inventory;
	private Image sprite;
	private int posX;
	private int posY;
	
	public Player(Profile p, int x, int y) {
		this.profile = p;
		this.inventory = new Inventory();
		this.posX = x;
		this.posY = y;
		if (p.getAvatar() != null) {
			sprite = p.getAvatar();
		} else {
			sprite = new Image("test.png");
		}
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

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	
}
