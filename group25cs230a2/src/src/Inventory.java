/**
 * Inventory.java
 * 
 * @author
 */
import java.util.ArrayList;

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
	
	public int tokenNum() {
		return this.token;	
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
		RedKey redKey = new RedKey();
		
		myInv.addItem(redKey);
		myInv.addToken();
		System.out.println(myInv.hasItem(redKey) + " " + myInv.tokenNum());
		myInv.reset();
		System.out.println(myInv.hasItem(redKey) + " " + myInv.tokenNum());
		
		
	}
	
}

