package enemies;
import game.Map;
import player.Player;

public class CoordinateCost {

	protected int x;
	protected int y;
	protected int distanceTravelled;
	protected int heuristic; // estimated distance left
	protected int totalCost;
	private Map map;
	private Player player = map.getPlayer();
	protected CoordinateCost parent;
	
	public CoordinateCost(int x,int y,int distanceTravelled,CoordinateCost parent) {
		this.x = x;
		this.y = y;
		this.distanceTravelled = distanceTravelled;
		heuristic = map.distanceBetween(x, y, player.getX(), player.getY());
		totalCost = heuristic + distanceTravelled;
	}
	
	public boolean isPlayer() {
		if(x==player.getX() && y==player.getY()) {
			return true;
		}
		return false;
	}
}