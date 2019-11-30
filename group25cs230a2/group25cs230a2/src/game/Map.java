package game;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import enemies.*;
import player.Player;
import player.Profile;
import cells.*;
import collectibles.*;

/**
 * 
 * @author matth
 *
 */


public class Map {
	private Cell[] map;	//store the data representing the map			//will be cell when can create cell objects
	private String filename;
	private int width;	//the width of the map
	private int height;	//the height of the map
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>(); //all the enemies in the map
	private ArrayList<Collectible> collectibles = new ArrayList<Collectible>(); //all collectibles in the level
	private Profile profile;
	private Player player;
		
	/**
	 * Constructor method to create an instance of Map
	 * @param filename The name of the file that contains the map.
	 */
	public Map(Profile p, String fn) {
		profile = p;
		filename = fn;
		map = convertStringToObjects(readFileToMap());	//read the map file and create the array and various objects
	}
	
	
	//Read the data from the csv file, and turn it into an ArrayList 
	private ArrayList<String> readFileToMap() {
		//create an ArrayList, containing the data from the file
		ArrayList<String> mapAl = new ArrayList<String>();	
		
		File csvFile = new File(filename);	//create File object to read from
		
		//create Scanner to read data from csvFile
		Scanner in = null;
		try {	//try to open file with scanner
		    in = new Scanner(csvFile); 
		} 
		catch (FileNotFoundException e){	//if the file can't be found throw exception 
			System.out.println ("The file, " + filename + " does not exist.");
			System.exit (0);
		}
		
		in.useDelimiter(",|\r");	//change the Scanner delimiter so it is applicable to csv files
		
		//read data from the csv file and add it to the array
		while (in.hasNext()) {
			mapAl.add(in.next().replace("\n", "").replace(" ", ""));	//remove any unneccessary characters
		}
		in.close();
		
		//get the height and width of the map from the ArrayList
		//remove the items after so the ArrayList contains only the map data
		width = Integer.parseInt(mapAl.get(0));
		mapAl.remove(0);
		height = Integer.parseInt(mapAl.get(0));
		mapAl.remove(0);
			
		return mapAl;
	}
	
	
	
	//Convert the ArrayList<String> to an array, and create any objects present in the map 								
	private Cell[] convertStringToObjects(ArrayList<String> stringAl) {
		
		Cell[] map = new Cell[stringAl.size()]; //create an array with the same length as the ArrayList
		System.out.println(stringAl.toString());
		//for each item within the ArrayList, create the appropriate objects, 
		//and store that in the same index in the map array
		for (int i = 0; i < stringAl.size(); i++) {
			//get the x and y coordinates of the object at index i
			int x = i % width;	
			int y = i / width;
			
			//switch statement to check for cells that have no metadata
			switch (stringAl.get(i)) {
				case "ground": 
					map[i] = new Ground();
					break;
				case "wall":
					map[i] = new Wall();
					break;
				case "water":
					map[i] = new Water();
					break;
				case "fire":
					map[i] = new Fire();
					break;
				//below are collectibles that are in the level
				//a ground cell will need to be created for each of these
				//the ground cell will be added to the map, and the colllectible to the collectibles ArrayList
				case "goal":
//					map[i] = "g";
					break;
				case "player":
					map[i] = new Ground();
					player = new Player(profile, x, y);
					
					break;
				case "token":
//					map[i] = "tok";
					break;
				case "flippers":
					map[i] = new Ground();
					collectibles.add(new Flippers(x, y));
					
					//set below to ground
					break;
				case "fireBoots":
					map[i] = new Ground();
					collectibles.add(new FireBoots(x, y));
					break;
				default:
//					map[i] = "not here";
			}
			
			//check for objects that have metadata, and deal with the metadata
			//use regex to match to different objects, then read their metadat afterwords
		
			//check for teleporters
			
			//set pattern to the teleporter regex
			Pattern teleporterPattern = Pattern.compile("^teleporter:[0-9]+$");
			Matcher teleporterMatcher = teleporterPattern.matcher(stringAl.get(i));
			
			//if it is a teleporter
			//split the string in the ":" character to split the different parts of metadata
			//create a new teleporter object, using the split metadata 
			if (teleporterMatcher.matches()) {
				String[] parts = stringAl.get(i).split(":");
			    String teleporterPairing = parts[1]; 
//			    map[i] = "tele:" + teleporterPairing;	//create teleporter here
			} 
			
			//check for different types of doors
			//set pattern to doors regex
			Pattern doorPattern = Pattern.compile("^door:(red|blue|green|yellow|token:[1-9])$");
			Matcher doorMatcher = doorPattern.matcher(stringAl.get(i));
		
			//if it is a door
			//split the string in the ":" character to split the different parts of metadata
			//create a new door object, using the split metadata 			
			if (doorMatcher.matches()) {
				String[] parts = stringAl.get(i).split(":");
			    //if the door is a token door, then create a token door, otherwise create a colour door
			    if (parts[1].equals("token")) {
//			    	map[i] = "door:" + parts[1] + ":" + parts[2];	//create token door here
			    } else {
//			    	map[i] = "door:" + parts[1];	//create colour door here
			    }
			}
    
			//check for different types of keys
			//set pattern to keys regex			
			Pattern keyPattern = Pattern.compile("^key:(red|blue|green|yellow)$");
			Matcher keyMatcher = keyPattern.matcher(stringAl.get(i));
			
			//if the item is a key
			//split the string to get the colour
			if (keyMatcher.matches()) {
				String[] parts = stringAl.get(i).split(":");
//				map[i] = "key:" + parts[1];
			} 
			
			
			//check for different enemy types
			//set the pattern to the enemy regex
			Pattern enemyPattern = Pattern.compile("^enemy:((smart|dumb)|(wall:[ac]:[udlr]|straight:[udlr]))$");
			Matcher enemyMatcher = enemyPattern.matcher(stringAl.get(i));
			
			//if an enemy
			if (enemyMatcher.matches()) {
				String[] parts = stringAl.get(i).split(":");
				
				//determine which type of enemy it is with switch statement
				//then add to the enemies ArrayList an instant of that enemy 
				switch (parts[1]) {
					case "straight":
 						enemies.add(new EnemyStraight(x, y, parts[2].charAt(0)));
						map[i] = new Ground();
						break;
					case "wall":
						enemies.add(new EnemyWall(x, y, parts[2].charAt(0), parts[3].charAt(0)));
						map[i] = new Ground();
						break;
					case "dumb":
						enemies.add(new EnemyDumb(x,y));
						map[i] = new Ground();					
					case "smart": 
						map[i] = new Ground();
						break;
					default:					
						break;
				}					
			} 
		}
		return map;
	}
	

	
	
	/**
	 * Get and return the cell at a given index
	 * @param x The x component of the coordinate
	 * @param y The y component of the coordinate
	 * @return The cell at the given coordinate
	 * @throws ArrayIndexOutOfBoundsException if the supplied coordinates do not exist in the map
	 */
	public Cell getAt(int x, int y) {
		try {
			isValidCoords(x, y);
			return map[getIndexFromCoords(x, y)];
			
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println("coords:"+ x + "," + y);
			System.out.println("Accessed coordinates do not exist");
			System.exit(0);
		}
		return null;
	}
	
	//get the index of a given set of coordinates
	public int getIndexFromCoords(int x, int y) {	
		if (isValidCoords(x, y)) {	//if the coordinates are valid return the index
			return width*y + x;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}		
	}
	
	//chech if a set of coordinates is valid
	private boolean isValidCoords(int x, int y) {
		//if either coordinate is less than 0 or greater than width or height respectivley
		if (x < 0 || y < 0 || x > width || y > height) {
			return false;
		}
		return true;
	}
	
	
	public void listEnemies() {
		for (int i = 0; i < enemies.size(); i++) {
			System.out.println(enemies.get(i).toString());
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int[] indexToCoords(int i) {
		int[] coords = {i % width, i/width};		
		return coords;
	}
	
	public ArrayList<Enemy> getEnemies() {
		return this.enemies;
	}
	
	public ArrayList<Collectible> getCollectibles() {
		return this.collectibles;
	}
	
	/**
	 * Describes the map class
	 * @return The dimensions of the map, and the array the map is stored in
	 */
	public String toString(){
		String returnString = "x: " + width + ", y: " + height + "\n";
		for (int i=0; i < map.length; i++) {
			if (i == 0) {
				returnString += "[" + map[i];
			} else if (i == map.length -1) {
				returnString += ", " + map[i] + "]";
			} else {
				returnString += ", " + map[i];
			}
		};
		return returnString;
	}
	
	//convert from String to correct cell types
	//
}
