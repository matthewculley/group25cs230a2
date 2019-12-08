package player;
import javafx.scene.image.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import collectibles.*;

/**
 * Player stores all information regarding the player in the current level
 * playthrough
 * */
public class Player {

	private Profile profile;		//Profile object for storing all the
									//info about the current user

	private Inventory inventory;	//Stores info about which items the
									//player has collected

	private Image sprite;			//What the player looks like in-game

	private int x, y;				//Player's current position on the map


	/**
	 * Constructs in instance of Player for the start of a level.
	 * @param profile - info about the user
	 * @param x - Player's (starting) x position
	 * @param y - Player's (starting) y position
	 * */
	public Player(Profile profile, int x, int y) {
		this.profile = profile;
		this.inventory = new Inventory();
		this.x = x;
		this.y = y;
		setSprite("player.png");
	}

	/**
	 * Constructs an instance of Player midway through a level from a player file.
	 * @param profile - info about the user
	 * @param fileName - name of the file containing info about the player
	 * during a playthrough of the level
	 * */
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
				case "key:red":
					inventory.addItem(new Key("red"));
					break;
				case "key:blue":
					inventory.addItem(new Key("blue"));
					break;
				case "key:green":
					inventory.addItem(new Key("green"));
					break;
				case "key:yellow":
					inventory.addItem(new Key("yellow"));
					break;
				default:
					break;
			}
		}
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
