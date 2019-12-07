package cells;

import java.util.ArrayList;
import java.util.Random;

import game.Map;

public class Gas extends Cell{

	private int count;
	private int x;
	private int y;
	
	public Gas(int x,int y) {
		super("gas.png",true);
		setX(x);
		setY(y);
	}
	
	public Gas infect(long seed,Map map) {
	    Random generator = new Random(count*7+seed);
	    int num = generator.nextInt(4);

	  // switch(num){
	   //	case 0:
	   		if(map.getAt(x+1,y).isPassable()&&!map.gasExists(x+1,y)) {
	   			return(new Gas(x+1,y));
	   		}
	   		//break;
	   //	case 1:
	   		if(map.getAt(x-1,y).isPassable()&&!map.gasExists(x-1,y)) {
	   			return(new Gas(x-1,y));
	   		}
	   	//	break;
	   	//case 2:
	   		if(map.getAt(x,y+1).isPassable()&&!map.gasExists(x,y+1)) {
	   			return(new Gas(x,y+1));
	   		}
	   	//  break;
	   //	case 3:
	   		if(map.getAt(x,y-1).isPassable()&&!map.gasExists(x,y-1)) {
	   			return(new Gas(x,y-1));
	   		}
	   	//	break;
	  // }
	   count++;
	   System.out.println("returning null");
	   return null;
	}
	
	private void setX(int x) {
		this.x = x;		
	}

	private void setY(int y) {
		this.y = y;		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String getCellName() {
		return "gas";
	}
	
}
