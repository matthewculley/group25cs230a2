import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Map {
	private String[] map;			//will be cell when can create cell objects
	private String filename;
	private int x;
	private int y;
	
	public Map (String fn) {
		filename = fn;
		map = convertStringToCells(readFileToMap());
	}
	
	//create a map array from a csv file
	private String[] readFileToMap() {
		//create arraylist to store raw data reading from the csv file
		ArrayList<String> mapAl = createAlFromFile();
		
		//read in the x and y size of the map
		x = Integer.parseInt(mapAl.get(0));
		mapAl.remove(0);
		y = Integer.parseInt(mapAl.get(0));
		mapAl.remove(0);
		//remove dimensions from the arraylist
		
		
		//create new array of the size of the map, and store the arraylist in it
		String[] returnArray = new String[x*y]; 
		mapAl.toArray(returnArray);
		
		return returnArray;
	}

	//read contents of csv file with scanner amd return arraylist of the contents
	private ArrayList<String> createAlFromFile() {
		ArrayList<String> al = new ArrayList<String>();
		
		File csvFile = new File(filename);
		
		//create scanner object containing the csv file
		Scanner in = null;
		try { //try to open file with scanner
		    in = new Scanner(csvFile); 
		} 
		catch (FileNotFoundException e){ //if the file can't be found throw exception 
			System.out.println ("The file, " + filename + " does not exist.");
			System.exit (0);
		}
		
		//edit delimiters to be applicable to csv files
		in.useDelimiter(",|\r");

		//add all data in the csv to the arraylist
		while (in.hasNext()) {
			al.add(in.next().replace("\n", "").replace(" ", ""));
		}
		
		in.close();
		return al;
	}
	
	// 																							change to Cell[] when can not all cells
	private String[] convertStringToCells(String[] stringArray) {
		String[] map = new String[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			//switch to check for 'basic' cells
			switch (stringArray[i]) {
				case "":
					map[i] = "";
					//create wall object and add to map
					break;
				case "goal":
					map[i] = "g";
					//add goal point to map
					break;
				case "player":
					map[i] = "ps";
					//add player start
					break;
				case "water":
					map[i] = "wat";
					//create water object and add
					break;
				case "fire":
					map[i] = "fi";
					//create fire object and add
					break;
				
				default:
					map[i] = stringArray[i];
			}
			
			//check for 'advanced' cells -- doors, teleporters, keys, enemies
		
			//check for teleporters
			//set pattern to the teleporter regex
			Pattern teleporterPattern = Pattern.compile("^teleporter:[a-z]+$");
			
			Matcher teleporterMatcher = teleporterPattern.matcher(stringArray[i]);
			
			//if the item is a teleporter
			if (teleporterMatcher.matches()) {
				//split the string to find the pairing
				String[] parts = stringArray[i].split(":");
			    String teleporterPairing = parts[1]; 
			    //create teleporter object with pairing a
			    map[i] = "tele:" + teleporterPairing;
			} 
			//different types of doors
			
			Pattern doorPattern = Pattern.compile("^door:(red|blue|green|yellow|token:[1-9])$");
			Matcher doorMatcher = doorPattern.matcher(stringArray[i]);
			
			//if the item is a teleporter
			if (doorMatcher.matches()) {
				//split the string to find the pairing
				String[] parts = stringArray[i].split(":");
			    String doorType = parts[1];
			    if (parts[1].equals("token")) {
			    	String tokens = parts[2];
			    	map[i] = "door:" + doorType + ":" + tokens;
			    } else {
			    	map[i] = "door:" + doorType;
			    }
			    
		
			    
			    
			    //create teleporter object with pairing a
//			    map[i] = "tele:" + teleporterPairing;
			} 
			
		}
		return map;
	}
	

	/**
	 * Describes the map class
	 * @return The dimensions of the map, and the array the map is stored in
	 */
	public String toString(){
		String returnString = "x: " + x + ", y: " + y + "\n";
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
	
}
