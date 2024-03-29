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
import java.lang.Math; 
import java.lang.ClassCastException;

/**
 * A class that defines the map.
 * @author Matt
 * @version 1.4
 */

public class Map {
	private Cell[] map;	//store the data representing the map
	private String filename;//name of the map file
	private int width;	//the width of the map
	private int height;	//the height of the map
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>(); //all the enemies in the map
	private ArrayList<Collectible> collectibles = new ArrayList<Collectible>(); //all collectibles in the level
	private ArrayList<Gas> gas = new ArrayList<Gas>(); //all the gas in the map	
	private Profile profile;
	private Player player;
	private ArrayList<Teleporter> teleporters;
	private String parentLevelName;
	private Long timeStarted;
	private Long timeTaken;
	private ArrayList<Gas> allGas;
		
	/**
	 * Constructor method to create an instance of Map.
	 * @param filename The name of the file that contains the map.
	 * @throws FileNotFoundException 
	 */
	public Map(Profile p, String fn) throws FileNotFoundException {
		allGas = new ArrayList<Gas>();
		profile = p;
		filename = fn;
		setParentLevelName(fn);
		teleporters = new ArrayList<Teleporter>();
		ArrayList<String> mapAl = readFileToMap();
		mapAl.add(fn);
		map = convertStringToObjects(mapAl);	//read the map file and create the array and various objects
		if (teleporters.size() > 0) connectTeleporters();
		timeStarted = System.currentTimeMillis();
		timeTaken = (long) 0;
	}
	
	/**
 	 * Creates a map from a string.
 	 * @param fn Map file name.
	 * @throws FileNotFoundException
 	 */
	public Map(String fn) throws FileNotFoundException {
		allGas = new ArrayList<Gas>();

		timeTaken = (long)0;
		filename = fn;
		setParentLevelName("");
		teleporters = new ArrayList<Teleporter>();
		map = convertStringToObjects(readFileToMap());	//read the map file and create the array and various objects
		if (teleporters.size() > 0) connectTeleporters();
		timeStarted = System.currentTimeMillis();
	}
	
	/**
 	 * Add player to the map.
 	 * @param profile The player's profile.
	 * @param player The player.
 	 */
	public void addPlayer(Profile profile, Player player) {
		this.profile = profile;
		this.player = player;
		
	}
	
	/**
 	 * Set the parent level name.
 	 * @param fileName The parent level map file name.
 	 */
	public void setParentLevelName(String fileName) {
		this.parentLevelName = fileName;
	}
	
	/**
 	 * Get the parent level name.
	 * @return Parent level name.
 	 */
	public String getParentLevelName() {
		return this.parentLevelName;
	}
	
	private void connectTeleporters() {
		int numberOfPairs = teleporters.size() / 2;
		for (int p = 0; p < numberOfPairs; p++) {
			Teleporter head = teleporters.get(0);
			teleporters.remove(0);

			for (int i = 0; i < teleporters.size(); i++) {
				if (head.getPairingId() == teleporters.get(i).getPairingId()) {
					head.setPartner(teleporters.get(i));
					teleporters.get(i).setPartner(head);
					teleporters.remove(i);
				}
			}	
		}
	}

	/*
	 * Read the data from the csv file, and turn it into an ArrayList 
	 * containg the string names for each cell
	 */
	private ArrayList<String> readFileToMap() throws FileNotFoundException {
		ArrayList<String> mapAl = new ArrayList<String>();	
		
		File csvFile = new File(getFileName());
		
		//Scanner to read data from csvFile
		Scanner in = null;
		    in = new Scanner(csvFile); 
		
		in.useDelimiter(",|\r");	//change the Scanner delimiter so it is applicable to csv files
		
		//read data from the csv file and add it to the array
		while (in.hasNext()) {
			mapAl.add(in.next().replace("\n", "").replace(" ", ""));	//remove any unneccessary characters 
		}
		in.close();

		//get the height and width of the map from the ArrayList
		//remove the items after so the ArrayList contains only the map data
		
		if (parentLevelName.equals("")) {
			setParentLevelName(mapAl.get(mapAl.size() - 1));
			mapAl.remove(mapAl.size() - 1);
			addToTime(Long.parseLong(mapAl.get(mapAl.size() - 1)));
			mapAl.remove(mapAl.size() - 1);
		}
		
		width = Integer.parseInt(mapAl.get(0));
		mapAl.remove(0);
		height = Integer.parseInt(mapAl.get(0));
		mapAl.remove(0);
			
		return mapAl;
	}
	
	/**
	 * Get the map object for use by other classes.
	 * @return The map in an array.
	 */
	public Cell[] getMap() {
		return map;
	}
	
	/*
	 * Convert the array list of strings into Cell[] to create the map
	 * also creates collectible and enemy objects
	 */
	private Cell[] convertStringToObjects(ArrayList<String> stringAl) {
		Cell[] map = new Cell[stringAl.size()]; 
		//for each item within the ArrayList, create the appropriate objects, 
		//and store that in the same index in the map array
		for (int i = 0; i < stringAl.size(); i++) {
			//get the x and y coordinates of the object at index i
			int x = i % width;	
			int y = i / width;
			
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
				case "gas":
					map[i] = new Ground();
					allGas.add(new Gas(x, y));
					break;
				//below are collectibles that are in the level
				//a ground cell will need to be created for each of these
				//the ground cell will be added to the map, and the colllectible to the collectibles ArrayList
				case "goal":
					map[i] = new Goal();
					//TODO
					break;
				case "player":
					map[i] = new Ground();
					player = new Player(profile, x, y);
					break;
				case "token":
					map[i] = new Ground();
					collectibles.add(new Token(x,y));
					break;
				case "flippers":
					map[i] = new Ground();
					collectibles.add(new Flippers(x, y));
					break;
				case "fireBoots":
					map[i] = new Ground();
					collectibles.add(new FireBoots(x, y));
					break;
				case "gasMask":
					map[i] = new Ground();
					collectibles.add(new GasMask(x, y));
					break;
				default:
					break;
			}
			
			//check for objects that have metadata, and deal with the metadata
			//use regex to match to different objects, then read their metadat afterwords
		
			//check for teleporters
			
			//done by creating a pattern to check the text,
			//matchign the pattern to the text
			//if it matches split the text into the data parts and create a new object
			
			//set pattern to the teleporter regex
			Pattern teleporterPattern = Pattern.compile("^teleporter:[0-9]+$");
			Matcher teleporterMatcher = teleporterPattern.matcher(stringAl.get(i));
			
			//if pattern matches create object
			if (teleporterMatcher.matches()) {
				String[] parts = stringAl.get(i).split(":");
			    String teleporterPairing = parts[1]; 
			    Teleporter tele = new Teleporter(x, y, Integer.parseInt(teleporterPairing));	
			    teleporters.add(tele);
			    map[i] = tele;
			} 
			
			//create doors
			Pattern doorPattern = Pattern.compile("^door:(red|blue|green|yellow|token:[1-9])$");
			Matcher doorMatcher = doorPattern.matcher(stringAl.get(i));
		
			if (doorMatcher.matches()) {
				String[] parts = stringAl.get(i).split(":");
			    //if the door is a token door, then create a token door, otherwise create a colour door
			    if (parts[1].equals("token")) {
			    	map[i] = new TokenDoor(Integer.parseInt(parts[2]));
			    } else {
			    	map[i] = new Door(false, parts[1]);  
			    }
			}
    
			//create keys
			Pattern keyPattern = Pattern.compile("^key:(red|blue|green|yellow)$");
			Matcher keyMatcher = keyPattern.matcher(stringAl.get(i));

			if (keyMatcher.matches()) {
				String[] parts = stringAl.get(i).split(":");
				map[i] = new Ground();
				collectibles.add(new Key(x, y, parts[1]));
			} 
			
			//create enemies
			Pattern enemyPattern = Pattern.compile("^enemy:((smart|dumb)|(wall:[ac]:[udlr]|straight:[udlr]))$");
			Matcher enemyMatcher = enemyPattern.matcher(stringAl.get(i));
			//set the map at that location to gorund so they can stand on it
			if (enemyMatcher.matches()) {
				String[] parts = stringAl.get(i).split(":");
				switch (parts[1]) {
					case "straight":
 						enemies.add(new EnemyStraight(x, y, parts[2].charAt(0)));
						map[i] = new Ground();
						System.out.println("CREATING STRAIGHT");
						break;
					case "wall":
						enemies.add(new EnemyWall(x, y, parts[2].charAt(0), parts[3].charAt(0)));
						map[i] = new Ground();
						System.out.println("CREATING WALL");
						break;
					case "dumb":
						enemies.add(new EnemyDumb(x,y));
						map[i] = new Ground();				
						System.out.println("CREATING DUMB");
						break;
					case "smart": 
						enemies.add(new EnemySmart(x,y,this));
						map[i] = new Ground();
						System.out.println("CREATING SMART");
						break;
					default:					
						break;
				}					
			} 
		}
		if (map.length < getWidth() * getHeight()) {
			System.out.println("Error loading map, the map does contain all cells");
			System.exit(0);
		}
		return map;
	}

	
	/**
	 * Get and return the cell at a given index.
	 * @param x The x component of the coordinate.
	 * @param y The y component of the coordinate.
	 * @return The cell at the given coordinate.
	 */
	public Cell getAt(int x, int y)  {
		try {
			isValidCoords(x, y);
			return map[getIndexFromCoords(x, y)];
			
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println("coords:"+ x + "," + y);
			System.out.println("Accessed coordinates do not exist");

		}
		return null;
	}
	
	/**
	 * Get the index of a map location from the coordinates.
	 * @param x The x location of the item in the map.
	 * @param y The y location of the item in the map.
	 * @return The index of the item at position (x,y) in the map.
	 * @throws ArrayIndexOutOfBoundsException If the given coordinates are invalid.
	 */
	public int getIndexFromCoords(int x, int y) throws ArrayIndexOutOfBoundsException {	
		if (isValidCoords(x, y)) {	//if the coordinates are valid return the index
			return width*y + x;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}		
	}
	
	/**
	 * Checks if two sets of coordinates are different locations.
	 * @param x1 The x position of the first location.
	 * @param y1 The y position of the first location.
	 * @param x2 The x position of the second location.
	 * @param y2 The y position of the second location.
	 * @return True if different, false otherwise.
	 */
	public boolean isDifferentPosition(int x1, int y1, int x2, int y2) {
		if (x1 == x2 & y1 == y2) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Check if a set of coordinates are valid.
	 * @param x The x componenet of the coordinates.
	 * @param y The y componenet of the coordinates.
	 * @return True if valid, false otherwise.
	 */
	public boolean isValidCoords(int x, int y) {
		//if either coordinate is less than 0 or greater than width or height respectivley
		if (x < 0 || y < 0 || x > width - 1 || y > height - 1) {
			return false;
		}
		return true;
	}
	
	/**
	 * Get the distance between two points on the map.
	 * @param x1 The x position of the first location.
	 * @param y1 The y position of the first location.
	 * @param x2 The x position of the second location.
	 * @param y2 The y position of the second location.
	 * @return The distance between the points.
	 */
	public int distanceBetween(int x1, int y1, int x2, int y2) {
		return (int) Math.sqrt( Math.pow(x2 - x1 , 2) + Math.pow(y2 - y1, 2));
	}
	
	/**
	 * Prints a list of enemies on the map.
	 */
	public void listEnemies() {
		for (int i = 0; i < enemies.size(); i++) {
			System.out.println(enemies.get(i).toString());
		}
	}
	
	/**
	 * Get the player object
	 * @return The player on the map
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Get the height of the map.
	 * @return The height of the map.
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Get the width of the map.
	 * @return The width of the map.
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Get the coordinates of a location on the map from an index in the map array.
	 * @param i The location in the array to find the coords for.
	 * @return The coordinate in an array, [x,y].
	 */
	public int[] indexToCoords(int i) {
		int[] coords = {i % width, i / width};		
		return coords;
	}
	
	/**
	 * Get an arraylist containing all the enemies on the map.
	 * @return All the enemies on the map in an arraylist.
	 */
	public ArrayList<Enemy> getEnemies() {
		return this.enemies;
	}
	
	/**
	 * Get an arraylist containing all the collectibles on the map.
	 * @return All the collectibles on the map in an arraylist.
	 */
	public ArrayList<Collectible> getCollectibles() {
		return this.collectibles;
	}
	
	/**
 	 * Get the gas on the map.
 	 * @return All the gas on the map in an arraylist.
 	 */
	public ArrayList<Gas> getGas(){
		return this.allGas;
	}
	
	/**
 	 * Checks if the gas exists on the map.
 	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @return True if exist, false otherwise.
 	 */
	public boolean gasExists(int x, int y) {
		for(int i = 0; i < allGas.size(); i++) {
			if(allGas.get(i).getX() == x && allGas.get(i).getY() == y) {
				return true;
			}
		} 

		return false;
	}
	/**
	 * Describes the map class.
	 * @return The dimensions of the map, and the array the map is stored in.
	 */
	public String toString(){
		String returnString = "File: " + getFileName() + " Parent name: "+ getParentLevelName() + "x: " + width + ", y: " + height + "\n";
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

	/**
 	 * Add new gas to the map.
 	 * @param newGas New Gas.
 	 */
	public void addGas (Gas newGas) {
		allGas.add(newGas);
	}

	/**
 	 * Get the file name.
 	 * @return File name.
 	 */
	public String getFileName() {
		return this.filename;
	}	

	/**
 	 * Get the starting time.
 	 * @return The starting time
 	 */
	public Long getTimeStarted() {
		return timeStarted;
	}

	/**
 	 * Add the time to the total time taken.
 	 * @param time Time taken.
 	 */
	public void addToTime(long time) {
		timeTaken += time;
	}

	/**
	 * Get the total time taken.
 	 * @return Total time taken.
 	 */
	public long getTimeTaken() {
		return timeTaken;
	}

	/**
 	 * Calculates the score.
 	 * @return The player score.
 	 */
	public int calculateScore() {
		System.out.println("//////////////////started" + getTimeStarted());
		System.out.println("//////////////////mill" + System.currentTimeMillis());
		System.out.println("//////////////////" + getTimeTaken());
		
		Long totalTimeTaken = (System.currentTimeMillis() - getTimeStarted())  + getTimeTaken();
		int score = (int) (totalTimeTaken / 1000);
		return score;
	}
}
