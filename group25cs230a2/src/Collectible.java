package group25;

import java.util.ArrayList;

public class Collectible {
	private int mapPosX;
	private int mapPosY;
	ArrayList<Collectible> listOfCollectibles = new ArrayList<Collectible>();

	public Collectible(int mapPosY, int mapPosX) {

	}

	public void setPosX(int mapPosX) {
		this.mapPosX = mapPosX;
	}

	public int getPosX() {
		return mapPosX;
	}

	public void setPosY(int mapPosY) {
		this.mapPosY = mapPosY;
	}

	public int getPosY() {
		return mapPosY;
	}

	public void addFireBoots(FireBoots fb) {
		listOfCollectibles.add(fb);
	}

	public void addFlippers(Flippers fl) {
		listOfCollectibles.add(fl);
	}

	public void addGasMask(GasMask gm) {
		listOfCollectibles.add(gm);
	}

	public void addBlueKey(BlueKey bkey) {
		listOfCollectibles.add(bkey);
	}

	public void addRedKey(RedKey rkey) {
		listOfCollectibles.add(rkey);
	}

	public void addGreenKey(GreenKey gkey) {
		listOfCollectibles.add(gkey);
	}

	public void addYellowKey(YellowKey ykey) {
		listOfCollectibles.add(ykey);
	}

}
