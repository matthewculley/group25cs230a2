package enemies;

import java.util.ArrayList;

import game.Map;

public class EnemySmart extends Enemy {
	public EnemySmart(int x, int y) {
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
	
	private boolean currentMatch(CoordinateCost a) {
		if(a.x==getX()&&a.y==getY()) {
			return true;
		}
		return false;
	}
	
	public void move (Map map) {
		int px = map.getPlayer().getX();
		int py = map.getPlayer().getY();
		
		
		ArrayList<CoordinateCost> openList = new ArrayList<>();
		ArrayList<CoordinateCost> closedList = new ArrayList<>();
		openList.add(new CoordinateCost(getX(),getY(),0,null,px,py,map));
		
		while(coordinateMatch(closedList) != null || openList.size()==0) {
			CoordinateCost current = openList.get(0);
			for(int i = 0;i < openList.size();i++) {
				if(openList.get(i).totalCost<current.totalCost) {
					current = openList.get(i);
				}
				openList.remove(current);
				closedList.add(current);
				
				CoordinateCost above = new CoordinateCost(current.x,current.y-1,current.distanceTravelled+1,current,px,py,map);
				CoordinateCost below = new CoordinateCost(current.x,current.y+1,current.distanceTravelled+1,current,px,py,map);
				CoordinateCost left = new CoordinateCost(current.x-1,current.y,current.distanceTravelled+1,current,px,py,map);
				CoordinateCost right = new CoordinateCost(current.x+1,current.y,current.distanceTravelled+1,current,px,py,map);
				
				if(checkValidMove(map, above.x, above.y)&&!closedList.contains(above)) {
					if(!openList.contains(above)) {
						openList.add(above);
					}else if(above.distanceTravelled>current.distanceTravelled+1) {
						above.parent = current;
						above.distanceTravelled = current.distanceTravelled+1;
					}
				}
				if(checkValidMove(map, below.x, below.y)&&!closedList.contains(below)) {
					if(!openList.contains(below)) {
						openList.add(below);
					}
				}
				if(checkValidMove(map, left.x, left.y)&&!closedList.contains(left)) {
					if(!openList.contains(left)) {
						openList.add(left);
					}
				}
				if(checkValidMove(map, right.x, right.y)&&!closedList.contains(right)) {
					if(!openList.contains(right)) {
						openList.add(right);
					}
				}
			}
		}
		CoordinateCost foundCoordinate = coordinateMatch(closedList);
		ArrayList<CoordinateCost> path = new ArrayList<>();	
		if(foundCoordinate != null) {
			path.set(path.size(),foundCoordinate);
			CoordinateCost currentPath = foundCoordinate;
			do{
				path.set(path.size(), currentPath);
				currentPath = currentPath.parent;
			}while(!currentMatch(currentPath));
		}
		if(path.size()>0) {
			this.setPosition(path.get(0).x,path.get(0).y);
		}
	}
	@Override
	public String getEnemyName() {
		return "enemy:smart";
	}
}
