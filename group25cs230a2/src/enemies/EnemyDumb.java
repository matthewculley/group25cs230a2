package enemies;
import map.*;
import player.*;


public class EnemyDumb extends Enemy {
	public EnemyDumb(int x, int y, Map map,Player player) {
		super(x, y, map);
		setPlayer(player);
	}
	
	public void move() { //ADD CASE FOR EQUAL X/Y
		private Cell toUp = map.getAt(super.getX(),super.getY()-1) ;
		private Cell toDown = map.getAt(super.getX(),super.getY()+1);
		private Cell toLeft = map.getAt(super.getX()-1,super.getY()) ;
		private Cell toRight = map.getAt(super.getX()+1,super.getY());
		
		if(Math.abs(player.getY()-super.getY())>Math.abs(player.getX()-super.getX())) {
			if(player.getY()>super.getY()) {
				if(super.canTraverse(toDown)) {
					super.move('d');
				}else if(player.getX()>super.getX()) {
					if(super.canTraverse(toRight)) {
						super.move('r');
					}
				}else if(super.canTraverse(toLeft)) {
					super.move('l');
				}
			}else {
				if(super.canTraverse(toUp)) {
					super.move('u');
				}else if(player.getX()>super.getX()) {
					if(super.canTraverse(toRight)) {
						super.move('r');
					}
				}else if(super.canTraverse(toLeft)) {
					super.move('l');
				}
			}
		}else {
			if(player.getX()>super.getX()) {
				if(super.canTraverse(toRight)) {
					super.move('r');
				}else if(player.getY()>super.getY()) {
					if(super.canTraverse(toDown)) {
						super.move('d');
					}
				}else if(super.canTraverse(toUp)) {
					super.move('u');
				}
			}else {
				if(super.canTraverse(toLeft)) {
					super.move('l');
				}else if(player.getY()>super.getY()) {
					if(super.canTraverse(toDown)) {
						super.move('d');
					}
				}else if(super.canTraverse(toUp)) {
					super.move('u');
				}
			}
			
		}
	}
}
