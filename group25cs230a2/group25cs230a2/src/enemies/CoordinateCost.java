package enemies;
import game.Map;

/**
 * This class defines path finding mechanic for enemy.
 * @author Ethan J
 * version 1.31
 */
public class CoordinateCost {

	protected int x;
	protected int y;
	protected int distanceTravelled;
	protected int heuristic; // estimated distance left
	protected int totalCost;
	private int playerX;
	private int playerY;
	protected CoordinateCost parent;
	
	/**
 	 * Constructs a Coordinate cost
 	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param distanceTravelled The current amount of 'tiles' travelled to get here.
	 * @param parent The CoordinateCost this was discovered from .
	 * @param playerX The X coordinate of the player.
	 * @param playerY The Y coordinate of the player.
	 * @param map The map.
 	 */
	public CoordinateCost(int x,int y,int distanceTravelled,CoordinateCost parent,int playerX, int playerY,Map map) {
		this.x = x;
		this.y = y;
		this.playerX = playerX;
		this.playerY = playerY;
		this.parent = parent;
		this.distanceTravelled = distanceTravelled;
		heuristic = map.distanceBetween(x, y, playerX, playerY);
		totalCost = distanceTravelled+heuristic;
	}
	/**
 	 * returns true if the current CoordinateCost has the same coordinates as the player.
 	 * @return boolean if Coordinates match players.
 	 */
	public boolean isPlayer() {
		if(x==playerX && y==playerY) {
			return true;
		}
		return false;
	}
}
