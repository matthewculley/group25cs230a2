package game;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.NullPointerException;


public class Graph {
	private LinkedList<Node> graph;	
	private int height;
	private int width;
	private Map map;
	private Node[] nodes;
	
	public Graph (Map map) {
		this.map = map;
		height = map.getHeight();
		width = map.getWidth();
		graph = createGraph();
	}
	
	private LinkedList<Node> createGraph() {
		LinkedList<Node> nodes = new LinkedList<Node>();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				System.out.println("(" + x + "," + y + ")");
				if (map.getAt(x, y).isPassable()) {
					
				}
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public Graph(Map map) {
//		height = map.getHeight();
//		width = map.getWidth();
//		graph = createGraph(map);
//		nodes = convertToNodes();
//		connectNodes();
//	}
//	
////	public isConnected(int x1, int y1, int x2, int y 2) {
////		graph.setOrigin(x1,x2);
////		graph.setObjective(x2,y2);
////		if 
////	}
//
//	public int[] createGraph(Map map) {
//		int[] graph = new int[height * width];
//
//		for (int i = 0; i < map.getWidth()*map.getHeight(); i++) {
//			int x = map.indexToCoords(i)[0];
//			int y = map.indexToCoords(i)[1];
//			if (map.getAt(x,y).isPassable()) {
////				System.out.print(map.getAt(x,y).toString());
//				graph[i] = 0;
//			} else {
//				graph[i] = -1;
//			}
//		}
//		return graph;
//	}
//	
//	public Node[] convertToNodes() {
//		Node [] returnArray = new Node[width * height];
//		for (int y = 0; y < height; y++) {
//			for (int x = 0; x < width; x++) {
//				int index = getIndexFromCoords(x,y);
//				//if a passable tile
//				if (!(graph[index] == -1)) {
//					//create a new node
//					returnArray[index] = new Node(x,y);
//				}			
//			}
//		}
//		return returnArray;
//	}
//	
//	public void connectNodes() {
//		for (int y = 0; y < height; y++) {
//			for (int x = 0; x < width; x++) {
//				int index = getIndexFromCoords(x,y);
//				try {
//					Node currentNode = nodes[index];
//					if (nodes[index] != null) {
//						//if node above
//						int upIndex = getIndexFromCoords(x, y - 1);
//						if (nodes[upIndex] != null) {
//							currentNode.addReachableNode(nodes[upIndex]);
//						}
//						
//						//if node below
//						int downIndex = getIndexFromCoords(x, y + 1);
//						if (nodes[downIndex] != null) {
//							currentNode.addReachableNode(nodes[downIndex]);
//						}
//						
//						//if node right
//						int rightIndex = getIndexFromCoords(x + 1, y);
//						if (nodes[rightIndex] != null) {
//							currentNode.addReachableNode(nodes[rightIndex]);
//						}
//						
//						//if node left
//						int leftIndex = getIndexFromCoords(x- 1, y);
//						if (nodes[leftIndex] != null) {
//							currentNode.addReachableNode(nodes[leftIndex]);
//						}		
//					}
//					System.out.println("node after connecting " + currentNode.toString());
//				} catch (NullPointerException e){
//					continue;
//				}
//								
//				
//			}
//		}
//	}
//	
////	public Node getNodeAt(int x, int y) {
////		System.out.println("fuck shit fuck" + nodes.size());
////		for (int i = 0; i < nodes.size(); i++) {
////			if (nodes.get(i).getX() == x & nodes.get(i).getY() == y) {
////				return nodes.get(i);
////			} 
////		}
////		return null;
////	}
//	
//	public void setOrigin(Map map, int x, int y) {
//		if (isValidCoords(x, y)) {
//			int index = map.getIndexFromCoords(x, y);
//			for (int i = 0; i < nodes.length; i++) {
//				if (nodes[i].isOrigin() == true) {
//					nodes[i].setOrigin(false);
//				}
//				nodes[index].setOrigin(true);
//			}
//		} 		
//	}
//	
//	public void setObjective(Map map, int x, int y) {
//		if (isValidCoords(x, y)) {
//			int index = map.getIndexFromCoords(x, y);
//			for (int i = 0; i < nodes.length; i++) {
//				if (nodes[i].isObjective() == true) {
//					nodes[i].setObjective(false);
//				}
//				nodes[index].setObjective(true);
//			}
//		} 		
//	}
//	
//	
//	
////	public void print() {
////		for (int i = 0; i < height; i++) {
////			for (int j = 0; j < width; j++) {
////				int index = getIndexFromCoords(j,i);
////				if (graph[index] == -1) {
////					System.out.print(graph[index] + " ,");
////				} else {
////					System.out.print(graph[index] + "  ,");
////				}
////				
////			}
////			System.out.println();
////		}
////	}
//	
//	
//	public Node getNodeAt(int x, int y) {
//		try {
//			return nodes[getIndexFromCoords(x,y)];
//		} catch (ArrayIndexOutOfBoundsException e) {
//			return null;
//		}
//	}
//	
//	
//	
//	
////	public void addNode(Node n) {
////		nodes.add(n);
////	}
//	
//	public int getIndexFromCoords(int x, int y) {	
//		if (isValidCoords(x, y)) {	//if the coordinates are valid return the index
//			return width*y + x;
//		} else {
//			throw new ArrayIndexOutOfBoundsException();
//		}		
//	}
//	
//	public boolean isValidCoords(int x, int y) {
//		//if either coordinate is less than 0 or greater than width or height respectivley
//		if (x < 0 || y < 0 || x > width -1 || y > height -1) {
//			return false;
//		}
//		return true;
//	}	
}


