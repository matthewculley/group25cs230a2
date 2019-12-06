package enemies;
import game.*;
import java.lang.Math;

public class EnemyDumb extends Enemy {
	public EnemyDumb(int x, int y) {
		super(x, y);
		super.setSprite("enemyDumb.png");
	}
	
	public void move (Map map) {
		int px = map.getPlayer().getX();
		int py = map.getPlayer().getY();
		
		int distanceUp = map.distanceBetween(getX(), getY() - 1, px, py);
		int distanceDown = map.distanceBetween(getX(), getY() + 1, px, py);
		int distanceLeft = map.distanceBetween(getX() - 1, getY(), px, py);
		int distanceRight = map.distanceBetween(getX() + 1, getY(), px, py);
		
		System.out.println("up " + distanceUp);
		System.out.println("down " + distanceDown);
		System.out.println("left " + distanceLeft);
		System.out.println("right " + distanceRight);
		
		int shortest = Math.min(Math.min(distanceUp, distanceDown), Math.min(distanceLeft, distanceRight));
		System.out.println("::" + shortest);
		
		if (shortest == distanceUp & checkValidMove(map, getX(), getY() - 1)) {
			this.setPosition(getX(), getY() - 1);
		} else if (shortest == distanceDown & checkValidMove(map, getX(), getY() + 1)) {
			this.setPosition(getX(), getY() + 1);
		} else if (shortest == distanceLeft & checkValidMove(map, getX() - 1, getY())) {
			this.setPosition(getX() - 1, getY());
		} else if (shortest == distanceRight & checkValidMove(map, getX() + 1, getY())) {
			this.setPosition(getX() + 1, getY());
		}	
	}

	@Override
	public String getEnemyName() {
		return "enemy:dumb";
	}	
		
}
	
//	public void move() { //ADD CASE FOR EQUAL X/Y
//		private Cell toUp = map.getAt(super.getX(),super.getY()-1) ;
//		private Cell toDown = map.getAt(super.getX(),super.getY()+1);
//		private Cell toLeft = map.getAt(super.getX()-1,super.getY()) ;
//		private Cell toRight = map.getAt(super.getX()+1,super.getY());
//		
//		if(Math.abs(player.getY()-super.getY())>Math.abs(player.getX()-super.getX())) {
//			if(player.getY()>super.getY()) {
//				if(super.canTraverse(toDown)) {
//					super.move('d');
//				}else if(player.getX()>super.getX()) {
//					if(super.canTraverse(toRight)) {
//						super.move('r');
//					}
//				}else if(super.canTraverse(toLeft)) {
//					super.move('l');
//				}
//			}else {
//				if(super.canTraverse(toUp)) {
//					super.move('u');
//				}else if(player.getX()>super.getX()) {
//					if(super.canTraverse(toRight)) {
//						super.move('r');
//					}
//				}else if(super.canTraverse(toLeft)) {
//					super.move('l');
//				}
//			}
//		}else {
//			if(player.getX()>super.getX()) {
//				if(super.canTraverse(toRight)) {
//					super.move('r');
//				}else if(player.getY()>super.getY()) {
//					if(super.canTraverse(toDown)) {
//						super.move('d');
//					}
//				}else if(super.canTraverse(toUp)) {
//					super.move('u');
//				}
//			}else {
//				if(super.canTraverse(toLeft)) {
//					super.move('l');
//				}else if(player.getY()>super.getY()) {
//					if(super.canTraverse(toDown)) {
//						super.move('d');
//					}
//				}else if(super.canTraverse(toUp)) {
//					super.move('u');
//				}
//			}
//			
//		}
//	}
//}
