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

	public boolean isCollected() {
		return collected;
	}

	public void collect() {
		this.collected = true;
		
	}

//	public void addFireBoots(FireBoots fb) {
//		listOfCollectibles.add(fb);
//	}
//
//	public void addFlippers(Flippers fl) {
//		listOfCollectibles.add(fl);
//	}
//
//	public void addGasMask(GasMask gm) {
//		listOfCollectibles.add(gm);
//	}
//
//	public void addBlueKey(BlueKey bkey) {
//		listOfCollectibles.add(bkey);
//	}
//
//	public void addRedKey(RedKey rkey) {
//		listOfCollectibles.add(rkey);
//	}
//
//	public void addGreenKey(GreenKey gkey) {
//		listOfCollectibles.add(gkey);
//	}
//
//	public void addYellowKey(YellowKey ykey) {
//		listOfCollectibles.add(ykey);
//	}

}
