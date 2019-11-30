/**
 * Inventory.java
 * 
 * @author
 */
import java.util.ArrayList;

public class Inventory {

	private static int token = 0;
	private ArrayList<Collectible> collectibles = new ArrayList<Collectible>();

	/*
	 * Player's inventory has a token counter and checks if the keys or other
	 * collectibles have been picked up.
	 */
	
	public void addToken() {
		token++;
	}
	
	public int tokenNum() {
		return Inventory.token;	
	}
	
	public void addItem(Collectible c) {
		collectibles.add(c);
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
	
	public static void main(String[] args) {
		Inventory myInv = new Inventory();
		RedKey redKey = new RedKey(2, 2);
		GreenKey greenKey = new GreenKey (1, 2);
		
		myInv.addItem(redKey);
		myInv.addToken();
		System.out.println("r key yes " +myInv.hasItem(redKey) + " " + myInv.tokenNum());
		myInv.reset();
		System.out.println("r key no " + myInv.hasItem(redKey) + " " + myInv.tokenNum());
		myInv.addItem(greenKey);
		System.out.println("g key " + myInv.hasItem(greenKey) + " " + myInv.tokenNum());
		
	}
	
}

