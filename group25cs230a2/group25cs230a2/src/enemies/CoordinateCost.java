package enemies;
import game.Map;

/**
 * This class defines path finding mechanic for enemy.
 * @author Ethan J
 * version 1.3
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
 	 * Constructer method that gets the player coordinate, distance travelled and so on.
 	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param distanceTravelled Distance travelled of the enemy.
	 * @param parent A cell that has been explored, use to track player.
	 * @param playerX The x coordinate of the player.
	 * @param playerY The y coordinate of the player.
	 * @param map The map.
 	 */
	public CoordinateCost(int x,int y,int distanceTravelled,CoordinateCost parent,int playerX, int playerY, Map map) {
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
 	 * @return True if yes, false otherwise.
 	 */
	public boolean isPlayer() {
		if(x == playerX && y == playerY) {
			return true;
		}
		return false;
	}
}
