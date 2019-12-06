package player;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cells.Ground;
import collectibles.*;

public class Player {
	private Profile profile;
	private Inventory inventory;
	private Image sprite;
	private int x;
	private int y;
	private int score;
	
	public Player(Profile p, int x, int y) {
		this.profile = p;
		this.inventory = new Inventory();
		this.x = x;
		this.y = y;
		setSprite("player.png");
//		if (p.getAvatar() != null) {
//			sprite = p.getAvatar();
//		} else {
//			sprite = new Image("flippers.png");
//		}
	}


	public Player(Profile profile, String fileName) throws FileNotFoundException {
		
		File playerFile = new File(fileName + "player" + ".txt");
		Scanner in = new Scanner(playerFile);
		this.profile = profile;
		this.inventory = new Inventory();
		//set coordinates
		System.out.println("filename: " + fileName + "player" + ".txt");
		setX(Integer.parseInt(in.nextLine()));
		setY(Integer.parseInt(in.nextLine()));
		inventory.setTokens(Integer.parseInt(in.nextLine()));
		setSprite("player.png");

		while (in.hasNext()) {
			switch (in.nextLine()) { 
				case "flippers":
					inventory.addItem(new Flippers(0, 0));
					break;
				case "fireBoots":
					inventory.addItem(new FireBoots(0, 0));
					break;
				default:
					break;
			}
		}
		System.out.println(inventory.toString());
		in.close();
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

	public void setSprite(String imageName) {
		this.sprite = new Image(imageName);
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