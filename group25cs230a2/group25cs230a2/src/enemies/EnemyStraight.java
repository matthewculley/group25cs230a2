package enemies;

import cells.*;
import game.*;


public class EnemyStraight extends Enemy {
	private char direction;

	public EnemyStraight(int x, int y, char direction) { 
		super(x, y);
		switch(direction) {
			case 'u':super.setSprite("enemyStraightU.png");
			case 'd':super.setSprite("enemyStraightD.png");
			case 'l':super.setSprite("enemyStraightL.png");
			case 'r':super.setSprite("enemyStraightR.png");
			
		}
		setDirection(direction);
	}
	

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

	@Override
	public void move(Map map) {
		switch (direction) {
			case 'u':
				
				if (checkValidMove(map, getX(), getY() - 1)) {
					this.setPosition(getX(), getY() - 1);
				//else try and move down
				} else if (checkValidMove(map, getX(), getY() + 1)) {
					this.setPosition(getX(), getY() + 1);
					this.setDirection('d');
				}
				break;
			case 'd':
				if (checkValidMove(map, getX(), getY() + 1)) {
					this.setPosition(getX(), getY() + 1);
				//else try and move up
				} else if (checkValidMove(map, getX(), getY() - 1)) {
					this.setPosition(getX(), getY() - 1);
					this.setDirection('u');
				}
				break;
			case 'l':
				if (checkValidMove(map, getX() - 1, getY())) {
					this.setPosition(getX() - 1, getY());
				//else try and move right
				} else if (checkValidMove(map, getX() + 1, getY())) {
					this.setPosition(getX() + 1, getY());
					this.setDirection('r');
				} 
				break;
			case 'r':
				if (checkValidMove(map, getX() + 1, getY())) {
					this.setPosition(getX() + 1, getY());
				//else try and move left
				} else if (checkValidMove(map, getX() - 1, getY())) {
					this.setPosition(getX() - 1, getY());
					this.setDirection('l');
				}
				
			default:
				return;
		}
	}
				
		
		
//
		
		
		
//		Cell toUp = map.getAt(super.getX(),super.getY()-1) ;
//		Cell toDown = map.getAt(super.getX(),super.getY()+1);
//		Cell toLeft = map.getAt(super.getX()-1,super.getY()) ;
//		Cell toRight = map.getAt(super.getX()+1,super.getY());
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
	
	
	public String toString() {
		return "Straight Line Enemy (" + this.getX() + "," + this.getY() + ")";
	}


	@Override
	public String getEnemyName() {
		return "enemy:straight:" + getDirection();
	}
}
