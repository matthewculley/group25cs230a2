package player;
/**
 * Inventory.java
 * 
 * @author
 */
import java.util.ArrayList;

import cells.*;
import collectibles.*;
import game.*;

public class Inventory {

	private int token = 0;
	private ArrayList<Collectible> collectibles = new ArrayList<Collectible>();
	private Map map;
	
	
	/*
	 * Player's inventory has a token counter and checks if the keys or other
	 * collectibles have been picked up.
	 */
	
	public void addToken() {
		token++;
	}
	
	public int getTokens() {
		return token;	
	}
	
	public void setTokens(int amount) {
		token = amount;
	}
	
	public void addItem(Collectible c, Map map) {
		if (c.getClass() != new Token().getClass()) {
			collectibles.add(c);
		} else {
			addToken();
		}
		unlockDoors(map);
		c.collect();
	}
	
	public void addItem(Collectible c) {
		collectibles.add(c);
	}
	
	public void unlockDoors(Map map) {
		for (int i = 0; i < map.getMap().length - 1; i++) {
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
	
	public boolean hasItem(Collectible c) {
		for (Collectible elem : collectibles) {
			if (elem.getClass() == c.getClass()) {
				return true;
			}
		}
		return false;
	}
	
	public void reset() {
		token = 0;
		collectibles = new ArrayList<Collectible>();
	}
	
	public ArrayList<Collectible> getInventory() {
		return collectibles;
	}
	
	public String toString() {
		String returnString = "";
		for (Collectible elem : collectibles) {
			returnString += elem.toString();
		}
		return returnString += " tokens: " + getTokens();
	}

}
