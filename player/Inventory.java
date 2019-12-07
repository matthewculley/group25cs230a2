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
		for (int i = 0; i < map.getMap().length-1; i++) {
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
						if (hasItem(new Key("red"))) {
							map.getAt(x, y).setPassable(true);
						}
						break;
					case "blue":
						if (hasItem(new Key("blue"))) {
							map.getAt(x, y).setPassable(true);
						}
						break;
					case "green":
						if (hasItem(new Key("green"))) {
							map.getAt(x, y).setPassable(true);
						}
						break;
					case "yellow":
						if (hasItem(new Key("yellow"))) {
							map.getAt(x, y).setPassable(true);
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
	
//	public static void main(String[] args) {
//		Inventory myInv = new Inventory();
//		RedKey redKey = new RedKey(2, 2);
//		GreenKey greenKey = new GreenKey (1, 2);
//		
//		myInv.addItem(redKey);
//		myInv.addToken();
//		System.out.println("r key yes " +myInv.hasItem(redKey) + " " + myInv.getTokens());
//		myInv.reset();
//		System.out.println("r key no " + myInv.hasItem(redKey) + " " + myInv.getTokens());
//		myInv.addItem(greenKey);
//		
//	}
	
	
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
