package enemies;

public class EnemyWall extends Enemy {
	public EnemyWall(int x, int y, char rotation, char direction) {
		super(x, y, "enemyWall.png");
		setDirection(direction);
		setRotation(rotation);
	}
	
	private char direction;
	private char rotation; // cw or anti cw

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
	/**
	 * @return the rotation
	 */
	private char getRotation() {
		return rotation;
	}

	/**
	 * @param rotation the rotation to set
	 */
	private void setRotation(char rotation) {
		this.rotation = rotation;
	}

//	public void move() { // UNFINISHED LOGIC
//		private Cell toUp = map.getAt(super.getX(),super.getY()-1) ;
//		private Cell toDown = map.getAt(super.getX(),super.getY()+1);
//		private Cell toLeft = map.getAt(super.getX()-1,super.getY()) ;
//		private Cell toRight = map.getAt(super.getX()+1,super.getY());
//		
//		if()
//		switch (rotation) {
//			case 'a' :
//				
//			case 'c' :
//				if (!super.canTraverse(toDown) && getDirection()=='r') {
//					if(super.canTraverse(toRight)) {
//						super.move('r');
//					}else { //wall on right block
//						if(super.canTraverse(toUp)) {
//							super.move('u');
//							setDirection('u');
//						}else if(super.canTraverse(toLeft)) {
//							super.move('l');
//							setDirection('l');
//						}
//					}
//				}else if (!super.canTraverse(toRight) && getDirection()=='u') {
//					if(super.canTraverse(toUp)) {
//						super.move('u');
//					}else { //wall on up block
//						if(super.canTraverse(toLeft)) {
//							super.move('l');
//							setDirection('l');
//						}else if(super.canTraverse(toDown)) {
//							super.move('d');
//							setDirection('d')
//						}
//					}
//				}else if (!super.canTraverse(toUp) && getDirection()=='l') {
//					if(super.canTraverse(toLeft)) {
//						super.move('l');
//					}else { //wall on left block
//						if(super.canTraverse(toDown)) {
//							super.move('d');
//							setDirection('d');
//						}else if(super.canTraverse(toRight)) {
//							super.move('d');
//							setDirection('d');
//						}
//					}
//				}else if (!super.canTraverse(toLeft) && getDirection()=='d') {
//					if(super.canTraverse(toDown)) {
//						super.move('d');
//					}else { //wall on down block
//						if(super.canTraverse(toRight)) {
//							super.move('r');
//							setDirection('r');
//						}else if(super.canTraverse(toUp)) {
//							super.move('u');
//							setDirection('u');
//						}
//					}
//				}
//		}	
//	}	
}
