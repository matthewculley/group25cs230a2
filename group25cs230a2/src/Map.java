import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
	private String[] map;
	private String filename;
	private int x;
	private int y;
	
	public Map (String fn) {
		filename = fn;
		map = readFileToMap();
	}
	
	//create a map array from a csv file
	private String[] readFileToMap() {
		//create arraylist to store raw data reading from the csv file
		ArrayList<String> mapAl = createAlFromFile();
		
		//read in the x and y size of the map
		x = Integer.parseInt(mapAl.get(0));
		y = Integer.parseInt(mapAl.get(0));
		//remove dimensions from the arraylist
		mapAl.remove(0);
		mapAl.remove(1);
		
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
			al.add(in.next().replace("\n", ""));
		}
		
		in.close();
		return al;
	}

	/**
	 * Describes the map class
	 * @return The dimensions of the map, and the array the map is stored in
	 */
	public String toString(){
		String returnString = "x: " + x + ", y: " + y + "\n";
		for (int i=0; i < map.length; i++) {
			if (i == 0) {
				returnString += map[i];
			} else {
				returnString += ", " + map[i];
			}
		};
		return returnString;
	}
	
}
