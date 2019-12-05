package enemies;
import game.Map;

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
	
	public CoordinateCost(int x,int y,int distanceTravelled,CoordinateCost parent,int px, int py,Map map) {
		this.x = x;
		this.y = y;
		this.map = map;
		this.playerX = px;
		this.playerY = py;
		this.distanceTravelled = distanceTravelled;
		heuristic = map.distanceBetween(x, y, playerX, playerY);
		totalCost = distanceTravelled+heuristic;
	}
	
	public boolean isPlayer() {
		if(x==playerX && y==playerY) {
			return true;
		}
		return false;
	}
}