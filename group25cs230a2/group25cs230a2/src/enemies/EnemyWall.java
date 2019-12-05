package enemies;

import cells.*;
import game.*;
import java.lang.Math;
import java.util.ArrayList;

public class EnemyWall extends Enemy {
	private int parentWallX;
	private int parentWallY;
	private char direction;
	private char rotation; // cw or anti cw
	
	public EnemyWall(int x, int y, char rotation, char direction) {
		super(x, y, "enemyWall.png");
		setDirection(direction);
		setRotation(rotation);
		
		switch (direction) {
		case 'u':
			setParentWallX(x);
			setParentWallY(y - 1);
			break;
		case 'd':
			setParentWallX(x);
			setParentWallY(y + 1);
			break;
		case 'l':
			setParentWallX(x - 1);
			setParentWallY(y);
			break;
		case 'r':
			setParentWallX(x + 1);
			setParentWallY(y);
			break;
		}
		
		
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
		
		int enemyPositionalValue = 0;
		ArrayList<Integer> validPositions = new ArrayList<Integer>();
		
		
		
		for (int i = 0; i < 8; i++) {
			switch (i) {
				case 0:
					if (map.getAt(getParentWallX(), getParentWallY() - 1).isPassable()) {
							//if the enemy at this position
							if (getParentWallX() == getX() & getParentWallY() - 1 == getY()) {
								enemyPositionalValue = 0;
							} else {
								validPositions.add(i);
							}
					}
					break;
				case 1:
					if (map.getAt(getParentWallX() + 1, getParentWallY() - 1).isPassable()) {
							//if the enemy at this position
							if (getParentWallX() + 1 == getX() & getParentWallY() - 1 == getY()) {
								enemyPositionalValue = 1;
							} else {
								validPositions.add(1);
							}
					}
					break;
				case 2:
					if (map.getAt(getParentWallX() + 1, getParentWallY()).isPassable()) {
							//if the enemy at this position
							if (getParentWallX() + 1 == getX() & getParentWallY() == getY()) {
								enemyPositionalValue = 2;
							} else {
								validPositions.add(2);
							}
					}
					break;
				case 3:
					if (map.getAt(getParentWallX() + 1, getParentWallY() + 1).isPassable()) {
							//if the enemy at this position
							if (getParentWallX() + 1 == getX() & getParentWallY() + 1 == getY()) {
								enemyPositionalValue = 3;
							} else {
								validPositions.add(3);
							}
					}
					break;
				case 4:
					if (map.getAt(getParentWallX(), getParentWallY() + 1).isPassable()) {
							//if the enemy at this position
							if (getParentWallX() == getX() & getParentWallY() + 1 == getY()) {
								enemyPositionalValue = 4;
							} else {
								validPositions.add(4);
							}
					}
					break;
				case 5:
					if (map.getAt(getParentWallX() - 1, getParentWallY() + 1).isPassable()) {
							//if the enemy at this position
							if (getParentWallX() - 1 == getX() & getParentWallY() + 1 == getY()) {
								enemyPositionalValue = 5;
							} else {
								validPositions.add(5);
							}
					}
					break;
				case 6:
					if (map.getAt(getParentWallX() - 1, getParentWallY()).isPassable()) {
							//if the enemy at this position
							if (getParentWallX() - 1 == getX() & getParentWallY() + 1 == getY()) {
								enemyPositionalValue = 6;
							} else {
								validPositions.add(6);
							}
					}
					break;
				case 7:
					if (map.getAt(getParentWallX() - 1, getParentWallY() - 1).isPassable()) {
							//if the enemy at this position
							if (getParentWallX() - 1 == getX() & getParentWallY() - 1 == getY()) {
								enemyPositionalValue = 7;
							} else {
								validPositions.add(7);
							}
					}
					break;
				
			}
			
		}
		System.out.println(enemyPositionalValue);
		System.out.println(validPositions.toString());
		System.out.println("next move: " + validPositions.get(0));
		
		int nextMove = validPositions.get(0);
		moveTo(nextMove);
		
	}
	
	private void moveTo(int i) {
		switch (i) {
		case 0:
			this.setPosition(getParentWallX(), getParentWallY() - 1);
			updateParentWallPosition(); break;
		case 1:
			this.setPosition(getParentWallX() + 1 , getParentWallY() - 1);
			updateParentWallPosition(); break;
		case 2:
			this.setPosition(getParentWallX() + 1, getParentWallY() + 1);
			updateParentWallPosition(); break;
		case 3:
			this.setPosition(getParentWallX() + 1, getParentWallY() + 1);
			updateParentWallPosition(); break;
		case 4:
			this.setPosition(getParentWallX(), getParentWallY() + 1);
			updateParentWallPosition(); break;
		case 5:
			this.setPosition(getParentWallX() -1, getParentWallY() + 1);
			updateParentWallPosition(); break;
		case 6:
			this.setPosition(getParentWallX() - 1, getParentWallY());
			updateParentWallPosition(); break;
		case 7:
			this.setPosition(getParentWallX() - 1, getParentWallY() -1 );
			updateParentWallPosition(); break;
		default:
			this.setPosition(getParentWallX(), getParentWallY());
		
		}
	}
	
	private void updateParentWallPosition() {
		
	}
	
	
//	public void move(Map map) {
//		//clockwise movement
//		
//		//get xy of the current cell its following
//		System.out.println("parent wall: " + getParentWallX() + "," + getParentWallY());
//		
//		boolean[] directions = {
//				map.getAt(getX(), getY() + 1).isPassable() == false,	
//				map.getAt(getX() + 1, getY()).isPassable() == false,
//				map.getAt(getX(), getY() - 1).isPassable() == false,
//				map.getAt(getX() - 1, getY()).isPassable() == false
//		};
//
//		int trueCount = 0;
//		
//		for (int i = 0; i < directions.length; i++){
//			if (directions[i] == true)
//			trueCount++;
//		}
////		System.out.println("trueCount: " + trueCount);
//		System.out.println("direction: " + direction);
//		
//		int lastTrue = 0;
//		
//		if (trueCount > 1) {
//			for (int i = 0; i < directions.length; i++){
//				if (directions[i] == true)
//					lastTrue = i;
//			}
//			if (lastTrue == 2) {
//				setDirection('r');
//			}
//			if (lastTrue == 3) {
//				setDirection('l');
//			}
//		}
//		
//		
////			System.out.println("boogy");
////			setNextDirection();
//		System.out.println("last true: " + lastTrue);
////		System.out.println("new direction: " + direction);
//		
		
//		
//		switch (direction) {
//			//moving right
//			case 'd':
//				//right from a corner
//				if (map.getAt(getX() + 1, getY() + 1).isPassable() == false & getParentWallY() != getY()) {
//					System.out.println("right from corner");
//					if (safeMove(map, getX() + 1, getY())) {
//						moveRight();
//						System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY()); return;
//					} 
//				}
//				//right on a straight line
//				else if (map.getAt(getX() + 1, getY() + 1).isPassable() == false) {
//					System.out.println("right");
//					if (safeMove(map, getX() + 1, getY())) {
//						moveRight();
//						setParentWallX(getParentWallX() + 1);
//						System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY()); return;
//					}
//				}
//				//right to a corner
//				else if (map.getAt(getX(), getY() + 1).isPassable() == false) {
//					System.out.println("right to corner");
//					if (safeMove(map, getX() + 1, getY())) {
//						moveRight();
//						setDirection('l');
//						System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY()); return;
//					}
//				}
//
//				break;
//			//moving down
//			case 'l':
//				//down from a corner
//				if (map.getAt(getX() -1, getY() + 1).isPassable() == false & getParentWallY() != getY()) {
//					System.out.println("down from corner");
//					if (safeMove(map, getX(), getY() + 1)) {
//						this.setPosition(getX(), getY() + 1);
//						System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY()); return;
//					}
//				}
//				//down on a vertical line
//				else if (map.getAt(getX() - 1, getY() + 1).isPassable() == false) {
//					System.out.println("down");
//					if (safeMove(map, getX(), getY() + 1)) {
//						this.setPosition(getX(), getY() + 1);
//						setParentWallY(getParentWallY() + 1);
//						System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY()); return;
//					}	
//				}
//				//down to corner
//				else if (map.getAt(getX() - 1, getY()).isPassable() == false) {
//					System.out.println("down to corner");
//					if (safeMove(map, getX(), getY() + 1)) {
//						this.setPosition(getX(), getY() + 1);
//						setDirection('u');
//						System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY()); return;
//					} 
//				}			
//				
//				
//				break;
//			//moving left
//			case 'u':
//				//left out of a corner
//				if (map.getAt(getX() - 1, getY() - 1).isPassable() == false & getParentWallX() != getX()) {
//					System.out.println("left from corner");
//					if (safeMove(map, getX() - 1, getY())) {
//						this.setPosition(getX() - 1, getY());
//						System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY()); return;
//					} 
//				}
//				
//				//left along line
//				else if (map.getAt(getX() - 1, getY() - 1).isPassable() == false) {
//					int oldX = getX();
//					int oldY= getY();
//					System.out.println("left");
//					if (safeMove(map, getX() - 1, getY())) {
//						this.setPosition(getX() - 1, getY());
//						setParentWallX(getParentWallX() - 1);
//						System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY()); return;
//					} 
//					if(oldX == getX() & oldY == getY()) {
//						setNextDirection();	
//					}
//				}
//				
//				//if left to corner
//				else if (map.getAt(getX(), getY() - 1).isPassable() == false) {
//					System.out.println("left to corner");
//					if (safeMove(map, getX() - 1, getY())) {
//						this.setPosition(getX() - 1, getY());
//						setDirection('r');
//						System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY()); return;
//	//					setParentWallX(getParentWallX() - 1);
//					} 
//				}			
//				
//				break;
//			//moving up
//			case 'r':
//				//up from corner
//				if (map.getAt(getX() + 1, getY() - 1).isPassable() == false & getParentWallY() != getY()) {
//					System.out.println("up from corner");
//					if (safeMove(map, getX(), getY() - 1)) {
//						this.setPosition(getX(), getY() - 1);
//						System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY()); return;
//					} 
//				}
//				//if one move up
//				else if (map.getAt(getX() + 1, getY() - 1).isPassable() == false) {
//					System.out.println("up");
//					if (safeMove(map, getX(), getY() - 1)) {
//						this.setPosition(getX(), getY() - 1);
//					setParentWallY(getParentWallY() - 1);
//					System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY()); return;
//					}
//				}
//				//up to corner
//				else if (map.getAt(getX() + 1, getY()).isPassable() == false) {
//					System.out.println("up to corner");
//					if (safeMove(map, getX(), getY() - 1)) {
//						this.setPosition(getX(), getY() - 1);
//	//					setParentWallX(getParentWallX() + 1);
//						setDirection('d');
//						System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY()); return;
//					} 
//				}
//				
//			default:
//				System.out.print("rippperererweassfhdvxzcjklnadfgs);kak;jdg");
//				break;
//			}
//		
//		
//		
//		System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY());
//	}
		
	
			
	private boolean safeMove(Map map, int x, int y) {
		if (map.getAt(x, y).isPassable()) {
			return true;
		} else {
			return false;
		}
	}
			
	private void setNextDirection() {
		switch (getRotation()) {
		case 'c':
			switch (getDirection()) {
				case 'u':
					setDirection('l');
				case 'l':
					setDirection('d');
				case 'd':
					setDirection('r');
				case 'r':
					setDirection('u');
				default:
					break;
			}	
		}
	}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//			System.out.println("parent wall: " + getParentWallX() + "," + getParentWallY());
////			parentNextXOffset = 0;
////			parentNextYOffset = 0;
//			enemyNextXOffset = 0;
//			enemyNextYOffset = 0;
//			
//			for (int i = 0; i < 8; i++) {
//				if (i == 0) {
//					prevX = getParentWallX();
//					prevY = getParentWallY() - 1;
//					if (getX() == prevX & getY() == prevY) {
//						System.out.println("move right");
////						if (adjacentTo(getParentWallX(), getParentWallY(), getX() + 1, getY())) {
//						if (map.getAt(getX(), getY()).isPassable() == false) {
//							this.setPosition(getX() + 1, getY());
//							//if below not passable move 
//							if (map.getAt(getX(), getY() + 1).isPassable() == false) {
//								setParentWallX(getParentWallX() + 1);
//							}
//						}
//						break;
//					}
//					
//				}
//				if (i == 1) {
//					prevX = getParentWallX() + 1;
//					prevY = getParentWallY() - 1;
//					
//					if (getX() == prevX & getY() == prevY) {
//						System.out.println("move down");
//						if (adjacentTo(getParentWallX(), getParentWallY(), getX(), getY() + 1)) {
//							this.setPosition(getX(), getY() + 1);
//							//if below not passable move 
////							if (map.getAt(getX(), getY() + 1).isPassable() == true & map.getAt(getX() - 1, getY() + 1).isPassable() == false) {
//							if (map.getAt(getX() - 1, getY() - 1).isPassable() == false) {
//								setParentWallY(getParentWallY() + 1);
//							}
//						}
//						break;
//					}		
//				}
//				if (i == 2) {
//					prevX = getParentWallX() -1 ;
//					prevY = getParentWallY() - 1;
//					if (getX() == prevX & getY() == prevY) {
//						System.out.println("move left");
//						if (adjacentTo(getParentWallX(), getParentWallY(), getX() - 1, getY())) {
//							this.setPosition(getX() - 1, getY());
//							//if below not passable move 
//							if (map.getAt(getX(), getY() - 1).isPassable() == false) {
//								setParentWallX(getParentWallX() - 1);
//							}
//						}
//						break;
//					}
//				}
//				if (i == 3) {
//					
//				}
//				if (i == 4) {
//					
//				}
//				if (i == 5) {
//					
//				}
//				if (i == 6) {
//					
//				}
//				if (i == 7) {
//					
//				}
//			}
//			System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY());

			
//			//check move right and move down right
//			if (map.getAt(getParentWallX() + 1, getParentWallY() - 1).isPassable()) {
//				System.out.println("a");
//				enemyNextXOffset++;
//				if (map.getAt(getParentWallX() + 2, getParentWallY() - 1).isPassable()) {
//					System.out.println("x");
//					parentNextXOffset++;
//					enemyNextXOffset++;
//				} else {
//					System.out.println("x");
//					parentNextXOffset++;
//				}
//				
//				
//				if (map.getAt(getParentWallX() + 1, getParentWallY()).isPassable()) {
//					System.out.println("b");
//					parentNextXOffset--;
//					
//					enemyNextYOffset++;
//				}
//			} else {
//				System.out.println("c");
//			}
//				
//			nextX = getX() + enemyNextXOffset;
//			nextY = getY() + enemyNextYOffset;
//			
//			System.out.println("p x Off: " + parentNextXOffset);
//			System.out.println("p y off: " + parentNextYOffset);
//			
//			System.out.println("e x Off: " + enemyNextXOffset);
//			System.out.println("e y off: " + enemyNextYOffset);
//			
//			System.out.println(adjacentTo(getParentWallX(), getParentWallY(), getX() + parentNextXOffset, getY() + parentNextXOffset));
//				
//			if (adjacentTo(getParentWallX(), getParentWallY(), nextX, nextY)) {
//				System.out.println("d");
//				//update parent wall location
//				setParentWallX(getParentWallX() + parentNextXOffset);
//				setParentWallY(getParentWallY() + parentNextYOffset);
//				//move the enemy
//				this.setPosition(nextX, nextY);
//			}
//			System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY());
			
			
		
		
//			//if can move right, and wall below is not passable
//			nextXOffset = 0;
//			nextYOffset = 0;
//			if (map.getAt(getParentWallX() + 1, getParentWallY() - 1).isPassable()) {
//				System.out.println("a");
//				nextXOffset++;
//				if (map.getAt(getParentWallX() + 1, getParentWallY()).isPassable()){
//					System.out.println("b");
////					nextXOffset--;
//					
//				}
//				setParentWallX(getParentWallX() + nextXOffset);
//				setParentWallY(getParentWallY() + nextYOffset);
//				
//				this.setPosition(getX() + nextXOffset, getY() + nextYOffset);
//				
//				if (adjacentTo(getParentWallX(), getParentWallY(), nextX, nextY)) {
//					t
//				}
			
		
//			if (map.getAt(getParentWallX() + 1, getParentWallY()).isPassable() == false) {
//				this.setPosition(getX() + 1, getY());
//				setParentWallX(getParentWallX() + 1);
//				System.out.println("right");
//				System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY());
//					
//	
//			//move down right round corner
////			} else if (map.getAt(getParentWallX() + 1, getParentWallY()).isPassable() == true & map.getAt(getParentWallX() + 1, getParentWallY()).getClass() == new Wall().getClass()) {
////				this.setPosition(getX() + 1, getY() + 1);
////				System.out.println("d right");
////				System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY());
//
//				
//			//move down
//			} else if (map.getAt(getParentWallX(), getParentWallY() + 1).isPassable() == false) {
//				this.setPosition(getX(), getY() + 1);
//				setParentWallY(getParentWallY() + 1);
//				System.out.println("down");
//				System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY());
//
//	
//			//move down left round corner
//			} else if (map.getAt(getParentWallX(), getParentWallY() + 1).isPassable() == true) {
//				this.setPosition(getX() - 1, getY() + 1);
//				System.out.println("d left");
//				System.out.println("new parent wall: " + getParentWallX() + "," + getParentWallY());
//
//				
//			//move left
//			} else if (map.getAt(getParentWallX() - 1, getParentWallY()).isPassable() == false) {
//				this.setPosition(getX() - 1, getY());
//				setParentWallX(getParentWallX() - 1);
//				
//			//move up left round corner
//			} else if (map.getAt(getParentWallX() - 1, getParentWallY()).isPassable() == true) {
//				this.setPosition(getX() - 1, getY() - 1);
//			 
//			//move up
//			} else if (map.getAt(getParentWallX(), getParentWallY() - 1).isPassable() == false) {
//				this.setPosition(getX(), getY() - 1);
//				setParentWallY(getParentWallY() + 1);
//			 	
//			//move up round corner
//			} else if (map.getAt(getParentWallX() - 1, getParentWallY()).isPassable() == true) {
//				this.setPosition(getX() - 1, getY());
//			} 
		

	
	private boolean adjacentTo(int parentWallX, int parentWallY, int nextX, int nextY) {
		if ((Math.abs(parentWallX - nextX) <= 1) & (Math.abs(parentWallY - nextY) <= 1)) {
			return true;
		} else {
			return false;
		}
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



	public int getParentWallX() {
		return parentWallX;
	}



	public void setParentWallX(int parentWallX) {
		this.parentWallX = parentWallX;
	}



	public int getParentWallY() {
		return parentWallY;
	}

	public void setParentWallPosition(int x, int y) {
		setParentWallX(x);
		setParentWallY(y);
	}


	public void setParentWallY(int parentWallY) {
		this.parentWallY = parentWallY;
	}



	@Override
	public String getEnemyName() {
		return "enemy:wall:" + getRotation() + ":" + getDirection();
	}
}
