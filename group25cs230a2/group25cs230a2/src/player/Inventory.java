package player;
import java.util.ArrayList;
import cells.*;
import collectibles.*;
import game.*;

/**
 * Inventory stores all information regarding what the player is carrying (eg tokens, keys, flippers)
 * and checks what should therefore be passable in the map.
 * @author Jack, Ethan F
 */

public class Inventory {

	private int token = 0;		//Number of tokens collected by the player in the current level playthrough
	private ArrayList<Collectible> collectibles = new ArrayList<Collectible>();	//Contains every item collected
																			   //by the player so far in the level

	/**
	 * Method for adding a collectible item to the player's inventory.
	 * @param c - collectible item to be added to inventory
	 * @param map - map of the current level playthrough in its current state
	 */
	public void addItem(Collectible c, Map map) {
		if (c.getClass() != new Token().getClass()) {
			collectibles.add(c);
		} else {
			addToken();
		}
		unlockDoors(map);
		c.collect();
	}

	//Adds collectible item to player's inventory (except token).
	public void addItem(Collectible c) {
		collectibles.add(c);
	}

       /**
	* Method checks which doors in the map should be unlocked (based on what items the player has collected)
	* and unlocks them.
	* @param map - the map of the current level playthrough
	*/
	public void unlockDoors(Map map) {
		for (int i = 0; i < map.getMap().length - 2; i++) {
			int x = map.indexToCoords(i)[0];
			int y = map.indexToCoords(i)[1];
			if (map.getAt(x,y).getClass() == (new TokenDoor().getClass())) {
				if (getTokens() >= ((TokenDoor) map.getAt(x,y)).getNeededTokens()) {
					map.getAt(x, y).setPassable(true);
				}
			}

			if (map.getAt(x,y).getClass() == (new Door().getClass())) {
				switch (((Door)map.getAt(x,y)).getColour()) {
					case "red":
							for (Collectible ele : collectibles) {
								if (ele.getClass() == (new Key("red").getClass())) {
									if (((Key)ele).getColour().equals("red")) {
										map.getAt(x, y).setPassable(true);
									}
								}
							}
						break;
					case "blue":
						for (Collectible ele : collectibles) {
							if (ele.getClass() == (new Key("blue").getClass())) {
								if (((Key)ele).getColour().equals("blue")) {
									map.getAt(x, y).setPassable(true);
								}
							}
						}
					break;
					case "green":
						for (Collectible ele : collectibles) {
							if (ele.getClass() == (new Key("green").getClass())) {
								if (((Key)ele).getColour().equals("green")) {
									map.getAt(x, y).setPassable(true);
								}
							}
						}
					break;
					case "yellow":
						for (Collectible ele : collectibles) {
							if (ele.getClass() == (new Key("yellow").getClass())) {
								if (((Key)ele).getColour().equals("yellow")) {
									map.getAt(x, y).setPassable(true);
								}
							}
						}
					break;
					case "token":
							System.out.println("Player Tokens: " + getTokens());
							if (getTokens() >= ((TokenDoor) map.getAt(x,y)).getNeededTokens()) {
								map.getAt(x, y).setPassable(true);
							}
							break;
					default:
						break;
				}

			}
		}
	}

	/**
	 * Method to check whether the player has a given collectible item in their inventory
	 * @param c - the collectible item whose presence in inventory is being checked
	 * @return true - if player has collectible c, false - if player does not have it
	 */
	public boolean hasItem(Collectible c) {
		for (Collectible elem : collectibles) {
			if (elem.getClass() == c.getClass()) {
				return true;
			}
		}
		return false;
	}

	//Sets the player's inventory to empty.
	public void reset() {
		token = 0;
		collectibles = new ArrayList<Collectible>();
	}

	public ArrayList<Collectible> getInventory() {
		return collectibles;
	}

	public void addToken() {
		token++;
	}

	public int getTokens() {
		return token;
	}

	public void setTokens(int amount) {
		token = amount;
	}

	public String toString() {
		String returnString = "";
		for (Collectible elem : collectibles) {
			returnString += elem.toString();
		}
		return returnString += " tokens: " + getTokens();
	}

}
