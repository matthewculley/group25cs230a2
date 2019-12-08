package enemies;
import game.Map;

/**
 * This class defines the coordinate of the enemy.
 * @author Group25
 * version 1.0
 */

public class CoordinateCost {

	protected int x;
	protected int y;
	protected int distanceTravelled;
	protected int heuristic; // estimated distance left
	protected int totalCost;
	private Map map;
	private int playerX;
	private int playerY;
	protected CoordinateCost parent;
	
	/**
 	 * 
 	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param distanceTravelled Distance travelled of the enemy.
	 * @param parent 
	 * @param playerX The x coordinate of the player.
	 * @param playerY The y coordinate of the player.
	 * @param map The map.
 	 */
	
	public CoordinateCost(int x,int y,int distanceTravelled,CoordinateCost parent,int playerX, int playerY,Map map) {
		this.x = x;
		this.y = y;
		this.map = map;
		this.playerX = playerX;
		this.playerY = playerY;
		this.parent = parent;
		this.distanceTravelled = distanceTravelled;
		heuristic = map.distanceBetween(x, y, playerX, playerY);
		totalCost = distanceTravelled+heuristic;
	}
	
	/**
 	 * Checks the player is in a certain coordinate.
 	 * @return The result whether the coordinate have player or not.
 	 */
	
	public boolean isPlayer() {
		if(x==playerX && y==playerY) {
			return true;
		}
		return false;
	}
}
