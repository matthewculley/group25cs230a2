package game;
import java.util.ArrayList;

public class Node {
	private int x;
	private int y;
	private ArrayList<Node> reachable;
	private boolean objective;
	private boolean origin;
	 

	public Node(int x, int y ) {
		this.x = x;
		this.y = y;
		reachable = new ArrayList<Node>();
	}

	public void addReachableNode(Node node) {
		reachable.add(node);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String toString() {
		
		String returnString = "(" + x + "," + y + "), reachable nodes: " + reachable.size() + "\n";
		return returnString;
	}

	public boolean isObjective() {
		return objective;
	}

	public void setObjective(boolean objective) {
		this.objective = objective;
	}

	public boolean isOrigin() {
		return origin;
	}

	public void setOrigin(boolean origin) {
		this.origin = origin;
	}
}
