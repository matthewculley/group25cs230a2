package enemies;

public class EnemyStraight extends Enemy {

	public EnemyStraight(int x, int y, char direction) {
		super(x, y, "enemyStraight.png");
		setDirection(direction);
	}
	
	private char direction; // u,d,l,r for up down left right respectively

	/**
	 * @return the direction
	 */
	private char getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	private void setDirection(char direction) {
		this.direction = direction;
	}

//	public void move() {
//		private Cell toUp = map.getAt(super.getX(),super.getY()-1) ;
//		private Cell toDown = map.getAt(super.getX(),super.getY()+1);
//		private Cell toLeft = map.getAt(super.getX()-1,super.getY()) ;
//		private Cell toRight = map.getAt(super.getX()+1,super.getY());
//		
//		switch (direction) {
//			case 'u' :
//				if (super.canTraverse(toUp) {
//					super.move(direction);
//				}else if(super.canTraverse(toDown){
//					setDirection('d')
//					super.move('d'); 
//				}
//			case 'd' :
//				if (super.canTraverse(toDown) {
//					super.move(direction);
//				}else if(super.canTraverse(toUp){
//					setDirection('u');
//					super.move('u'); 
//				}
//			case 'l' :
//				if (super.canTraverse(toLeft) {
//					super.move(direction);
//				}else if(super.canTraverse(toRight){
//					setDirection('r');
//					super.move('r'); 
//				}
//			case 'r' :
//				if (super.canTraverse(toRight) {
//					super.move(direction);
//				}else if(super.canTraverse(toLeft){
//					setDirection('l');
//					super.move('l'); 
//				}
//		}
//	}
	
	public String toString() {
		return "Straight Line Enemy";
	}
}
