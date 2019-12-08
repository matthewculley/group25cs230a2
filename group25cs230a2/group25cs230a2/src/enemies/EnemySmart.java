package enemies;

import java.util.ArrayList;
import game.Map;

/**
 * A subclass that creates an enemy that can pathfind.
 * @author Ethan J
 * @version 1.5
 */
public class EnemySmart extends Enemy {
	/**
 	 * Constructs straight enemy.
 	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param direction Which direction the enemy is going.
 	 */
	public EnemySmart(int x, int y,Map map) {
		super(x, y);
		super.setSprite("enemySmart.png");
	}
	private CoordinateCost coordinateMatch(ArrayList<CoordinateCost> a) {
		for(int i = 0; i<a.size();i++) {
			if(a.get(i).isPlayer()) {
				return a.get(i);
			}
		}
		return null;
	}
	private boolean coordinateMatch(ArrayList<CoordinateCost> a,int x,int y) {
		for(int i = 0; i<a.size();i++) {
			if(a.get(i).x == x && a.get(i).y == y ) {
				return true;
			}
		}
		return false;
	}
	
	private boolean currentMatch(CoordinateCost a) {
		if(a.x==getX()&&a.y==getY()) {
			return true;
		}
		return false;
	}
	
	/**
 	 * Calculates the Enemy's next move.
 	 * @param map The map
 	 */
	public void move (Map map) {
		int px = map.getPlayer().getX();
		int py = map.getPlayer().getY();
		System.out.print("\nPLAYER X + Y" +px+"  "+py);
		
		ArrayList<CoordinateCost> openList = new ArrayList<>();
		ArrayList<CoordinateCost> closedList = new ArrayList<>();
		openList.add(new CoordinateCost(getX(),getY(),0,null,px,py,map));
		
			while((coordinateMatch(closedList) == null) && openList.size()>0) {
				CoordinateCost current = openList.get(0);
				for(int i = 1;i < openList.size();i++) {
					if(openList.get(i).totalCost<current.totalCost) {
						current = openList.get(i);
					}
				}
					int o = openList.size();
					openList.remove(current);
					if(o-openList.size() == 1) {
						System.out.println(" \n REMOVED 1");
					}
					closedList.add(current);
					
					System.out.print("\n OPENLIST SIZE"+openList.size()+" \n CLOSED LIST SIZE: " + closedList.size() +"\nFILLING LIST");
					
					CoordinateCost above = new CoordinateCost(current.x,current.y-1,current.distanceTravelled+1,current,px,py,map);
					CoordinateCost below = new CoordinateCost(current.x,current.y+1,current.distanceTravelled+1,current,px,py,map);
					CoordinateCost left = new CoordinateCost(current.x-1,current.y,current.distanceTravelled+1,current,px,py,map);
					CoordinateCost right = new CoordinateCost(current.x+1,current.y,current.distanceTravelled+1,current,px,py,map);
					
					if(checkValidMove(map, above.x, above.y)&&!(coordinateMatch(closedList,above.x,above.y))) {
						if(!(coordinateMatch(openList,above.x,above.y))) {
							openList.add(above);
							System.out.println(" \n ABOVE X ABD Y" + above.x+" "+above.y);
						}else if(above.distanceTravelled>current.distanceTravelled+1) {
							above.parent = current;
							above.distanceTravelled = current.distanceTravelled+1;
						}
					}
					if(checkValidMove(map, below.x, below.y)&&!(coordinateMatch(closedList,below.x,below.y))) {
						if(!(coordinateMatch(openList,below.x,below.y))) {
							openList.add(below);
						}else if(below.distanceTravelled>current.distanceTravelled+1) {
							below.parent = current;
							below.distanceTravelled = current.distanceTravelled+1;
						}
					}
					if(checkValidMove(map, left.x, left.y)&&!(coordinateMatch(closedList,left.x,left.y))) {
						if(!(coordinateMatch(openList,left.x,left.y))) {
							openList.add(left);
						}else if(left.distanceTravelled>current.distanceTravelled+1) {
							left.parent = current;
							left.distanceTravelled = current.distanceTravelled+1;
						}
					}
					if(checkValidMove(map, right.x, right.y)&&!(coordinateMatch(closedList,right.x,right.y))) {
						if(!(coordinateMatch(openList,right.x,right.y))) {
							openList.add(right);
						}else if(right.distanceTravelled>current.distanceTravelled+1) {
							right.parent = current;
							right.distanceTravelled = current.distanceTravelled+1;
						}
					}
				}
			
		CoordinateCost foundCoordinate = coordinateMatch(closedList);
		ArrayList<CoordinateCost> path = new ArrayList<>();	
		
		// System.out.println(" \n FOUND COORDINATE : " +foundCoordinate.x +"  "+ foundCoordinate.y);
		
		if(foundCoordinate != null) {
			path.add(0,foundCoordinate);
			CoordinateCost currentPath = foundCoordinate;
			do{
				path.add(0, currentPath);
				if(currentPath.parent!= null) {
					currentPath = currentPath.parent;
				}
			}while(!currentMatch(currentPath));
		}else {
			System.out.println(" \n FAILED FINDING PATH");
		}
		if(path.size()>0) {
			this.setPosition(path.get(0).x,path.get(0).y);
		}else {
			System.out.println(" \n FAILED creating path");
		}
	}
	/**
 	 * returns the name of the enemy.
 	 * @return the enemy name
 	 */
	@Override
	public String getEnemyName() {
		return "enemy:smart";
	}
}
