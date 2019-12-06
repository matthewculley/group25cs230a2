package enemies;

import game.*;

public class EnemyWall extends Enemy {
	private int prevX;
	private int prevY;
	private char direction; //of wall
	private char rotation; // cw or anti cw
	
	public EnemyWall(int x, int y, char rotation, char direction) {
		super(x, y);
		super.setSprite("enemyWall.png");
		setDirection(direction);
		setRotation(rotation);
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
	
	public void move(Map map) {
		switch (direction) {
		case 'u':
			switch (rotation) {
				case 'c':
					if(!checkValidMove(map, getX(), getY()-1)||(getX()==prevX&&getY()-1==prevY)){ // wall still above
						if(checkValidMove(map, getX()-1, getY())) { // can move left
							this.setPosition(getX()-1, getY());
						}else if(checkValidMove(map, getX(), getY()+1)) { // cant move left but can down
							this.setPosition(getX(), getY()+1);
							this.setDirection('l');
						}else if(checkValidMove(map, getX()+1, getY())) { // can only move right
							this.setPosition(getX()+1, getY());
							this.setDirection('d');
						}
					}else { // wall above gone
						this.setPosition(getX(), getY()-1);
						this.setDirection('r');
					}
					break;
				case 'a':
					if(!checkValidMove(map, getX(), getY()-1)||(getX()==prevX&&getY()-1==prevY)){ // wall still above
						if(checkValidMove(map, getX()+1, getY())) { // can move right
							this.setPosition(getX()+1, getY());
						}else if(checkValidMove(map, getX(), getY()+1)) { // cant move right but can down
							this.setPosition(getX(), getY()+1);
							this.setDirection('r');
						}else if(checkValidMove(map, getX()-1, getY())) { // can only move left
							this.setPosition(getX()-1, getY());
							this.setDirection('d');
						}
					}else { //wall above gone
						this.setPosition(getX(), getY()-1);
						this.setDirection('l');
					}
					break;
			}
			break;
		case 'd':
			switch (rotation) {
				case 'c':
					if(!checkValidMove(map, getX(), getY()+1)||(getX()==prevX&&getY()+1==prevY)){ // wall still below
						if(checkValidMove(map, getX()+1, getY())) { // can move right
							System.out.println("OOOOOO");
							this.setPosition(getX()+1, getY());
						}else if(checkValidMove(map, getX(), getY()-1)) { // cant move right but can up
							this.setPosition(getX(), getY()-1);
							this.setDirection('r');
						}else if(checkValidMove(map, getX()-1, getY())) { // can only move left
							this.setPosition(getX()-1, getY());
							this.setDirection('u');
						}
					}else { // wall below gone
						this.setPosition(getX(), getY()+1);
						this.setDirection('l');
					}
					break;
				case 'a':
					if(!checkValidMove(map, getX(), getY()+1)||(getX()==prevX&&getY()+1==prevY)){ // wall still below
						if(checkValidMove(map, getX()-1, getY())) { // can move left
							this.setPosition(getX()-1, getY());
						}else if(checkValidMove(map, getX(), getY()-1)) { // cant move left but can up
							this.setPosition(getX(), getY()-1);
							this.setDirection('l');
						}else if(checkValidMove(map, getX()-1, getY())) { // can only move right
							this.setPosition(getX()+1, getY());
							this.setDirection('u');
						}
					}else { // wall below gone
						this.setPosition(getX(), getY()+1);
						this.setDirection('r');
					}
					break;
			}
			break;
		case 'l':
			switch (rotation) {
				case 'c':
					if(!checkValidMove(map, getX()-1, getY())||(getX()-1==prevX&&getY()==prevY)){ // wall still left
						if(checkValidMove(map, getX(), getY()+1)) { // can move down
							this.setPosition(getX(), getY()+1);
						}else if(checkValidMove(map, getX()+1, getY())) { // cant move down but can right
							this.setPosition(getX()+1, getY());
							this.setDirection('d');
						}else if(checkValidMove(map, getX(), getY()-1)) { // can only move up
							this.setPosition(getX(), getY()-1);
							this.setDirection('r');
						}
					}else { // wall left gone
						this.setPosition(getX()-1, getY());
						this.setDirection('u');
					}
					break;
				case 'a':
					if(!checkValidMove(map, getX()-1, getY())||(getX()-1==prevX&&getY()==prevY)){ // wall still left
						if(checkValidMove(map, getX(), getY()-1)) { // can move up
							this.setPosition(getX(), getY()-1);
						}else if(checkValidMove(map, getX()+1, getY())) { // cant move up but can right
							this.setPosition(getX()+1, getY());
							this.setDirection('u');
						}else if(checkValidMove(map, getX(), getY()+1)) { // can only move down
							this.setPosition(getX(), getY()+1);
							this.setDirection('r');
						}
					}else { // wall left gone
						this.setPosition(getX()-1, getY());
						this.setDirection('d');
					}
					break;					
			}
			break;
		case 'r':
			switch (rotation) {
				case 'c':
					if(!checkValidMove(map, getX()+1, getY())||(getX()+1==prevX&&getY()==prevY)){ // wall still right
						if(checkValidMove(map, getX(), getY()-1)) { // can move up
							this.setPosition(getX(), getY()-1);
						}else if(checkValidMove(map, getX()-1, getY())) { // cant move up but can left
							this.setPosition(getX()-1, getY());
							this.setDirection('u');
						}else if(checkValidMove(map, getX(), getY()+1)) { // can only move down
							this.setPosition(getX(), getY()+1);
							this.setDirection('l');
						}
					}else { // wall right gone
						this.setPosition(getX()+1, getY());
						this.setDirection('d');
					}
					break;
				case 'a':
					if(!checkValidMove(map, getX()+1, getY())||(getX()+1==prevX&&getY()==prevY)){ // wall still right
						if(checkValidMove(map, getX(), getY()+1)) { // can move down
							this.setPosition(getX(), getY()+1);
						}else if(checkValidMove(map, getX()-1, getY())) { // cant move down but can left
							this.setPosition(getX()-1, getY());
							this.setDirection('d');
						}else if(checkValidMove(map, getX(), getY()-1)) { // can only move up
							this.setPosition(getX(), getY()-1);
							this.setDirection('l');
						}
					}else { // wall right gone
						this.setPosition(getX()+1, getY());
						this.setDirection('u');
					}
					break;
			}
		}
//		System.out.println("direction of wall " + direction +" rotation: " + rotation);
		prevX = getX();
		prevY = getY();
	}
	
	@Override
	public String getEnemyName() {
		return "enemy:wall:" + getRotation() + ":" + getDirection();
	}
}
