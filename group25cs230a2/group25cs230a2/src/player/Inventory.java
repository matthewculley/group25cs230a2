package player;
/**
 * Inventory.java
 * 
 * @author
 */
import java.util.ArrayList;
import collectibles.*;

public class Inventory {

	private int token = 0;
	private ArrayList<Collectible> collectibles = new ArrayList<Collectible>();

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
	
	public void addItem(Collectible c) {
		collectibles.add(c);
		c.collect();
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
	
	public String toString() {
		String returnString = "";
		for (Collectible elem : collectibles) {
			returnString += elem.toString();
		}
		return returnString;
	}
	
}
