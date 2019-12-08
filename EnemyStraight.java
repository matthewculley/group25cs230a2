package enemies;

import game.*;

/**
 * A subclass that defines enemy that only goes striaght.
 * @author Ethan J
 * @version 1.3
 */

public class EnemyStraight extends Enemy {
	
	private char direction;

	/**
 	 * Constructs straight enemy.
 	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param direction Which direction the enemy is going.
 	 */
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
	
	private char getDirection() {
		return direction;
	}

	private void setDirection(char direction) {
		this.direction = direction;
	}

	/**
 	 * Overrides the method move.
 	 * @param map The map.
 	 */
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
					super.setSprite("enemyStraightD.png");

				}
				break;
			case 'd':
				if (checkValidMove(map, getX(), getY() + 1)) {
					this.setPosition(getX(), getY() + 1);
				//else try and move up
				} else if (checkValidMove(map, getX(), getY() - 1)) {
					this.setPosition(getX(), getY() - 1);
					this.setDirection('u');
					super.setSprite("enemyStraightU.png");
				}
				break;
			case 'l':
				if (checkValidMove(map, getX() - 1, getY())) {
					this.setPosition(getX() - 1, getY());
				//else try and move right
				} else if (checkValidMove(map, getX() + 1, getY())) {
					this.setPosition(getX() + 1, getY());
					this.setDirection('r');
					super.setSprite("enemyStraightR.png");
				} 
				break;
			case 'r':
				if (checkValidMove(map, getX() + 1, getY())) {
					this.setPosition(getX() + 1, getY());
				//else try and move left
				} else if (checkValidMove(map, getX() - 1, getY())) {
					this.setPosition(getX() - 1, getY());
					this.setDirection('l');
					super.setSprite("enemyStraightL.png");
				}
				
			default:
				return;
		}
	}
	
	/**
 	 * Prints the coordinate of straight line enemy.
 	 * @return Straight line enemy and the coordinate.
 	 */
	public String toString() {
		return "Straight Line Enemy (" + this.getX() + "," + this.getY() + ")";
	}

	/**
 	 * Prints the direction of the straight line enemy.
 	 * @return Straight enemy and the direction.
 	 */
	@Override
	public String getEnemyName() {
		return "enemy:straight:" + getDirection();
	}
}
