package menu;

import game.*;
import player.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;
import cells.*;
import enemies.*;
import collectibles.*;

/**
 * A class that constructs the map from the level files, and constructs all profiles from "profiles.txt".
 * It also saves maps to map files and profiles to a file.
 * @author Matt
 * @version 1.3
 */

public class IO {
	
	/**
	 * Saves the current file the player is on to a csv file, named "<userId>level.csv". 
	 * A serparate file, "<userId>player.txt" stores the player objects details
	 * @param map the current map being played on
	 * @throws IOException If the file names are invalid
	 */
	public static void saveMapToFile(Map map) throws IOException {
		//get time played so far
		map.addToTime(System.currentTimeMillis() - map.getTimeStarted());
		//get player objects to make code easier
		Player player = map.getPlayer();
		Inventory inventory = player.getInventory();
		
		//calculate the base of the filenames
		String fileName = map.getPlayer().getProfile().getUserID();
		
		//store the contents of the map in string form
		ArrayList<String> returnArrayList = new ArrayList<String>();

		//for each position on the map, add that cell to the arraylist
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				String addToArray = map.getAt(x, y).getCellName();

				//add gas
				for (int i = 0; i < map.getGas().size(); i++) {
					if (map.getGas().get(i).getX() == x & map.getGas().get(i).getY() == y) {
						addToArray = "gas";
					} 
				}
				
				//also add any collectibles 
				for (int i = 0; i < map.getCollectibles().size(); i++) {
					if (map.getCollectibles().get(i).getX() == x & map.getCollectibles().get(i).getY() == y & map.getCollectibles().get(i).isCollected() == false) {
						addToArray = map.getCollectibles().get(i).getCollectibleName();
					}
				}
				
				//and add any enemies
				for (int j = 0; j < map.getEnemies().size(); j++) {
					if (map.getEnemies().get(j).getX() == x) {
						if (map.getEnemies().get(j).getY() == y) {
							addToArray = map.getEnemies().get(j).getEnemyName();
						}
					}
				}
				//add the goal
				if (map.getAt(x, y).getClass() == new Goal().getClass()) {
					addToArray = "goal";
				}
				
				//add the cell the player is on. 
				if (player.getX() == x & player.getY() == y) {
					addToArray = "player";
				}
				returnArrayList.add(addToArray);
			}
		}
		
		//create filewriter object to write to he level file
		FileWriter fwLevel = new FileWriter(fileName + "level.csv");
		//add the sizes of the map to the file
		fwLevel.append(Integer.toString(map.getWidth()));
		fwLevel.append(",");
		fwLevel.append(Integer.toString(map.getHeight()));
		fwLevel.append("\r");
		
		//add the contetns of the arraylist to the file
		//when count = the width of the map, start a new line
		int count = 0; 
		for (int i = 0; i < returnArrayList.size(); i++) {
			if (count == map.getWidth() - 1) {
				fwLevel.append(returnArrayList.get(i));
				fwLevel.append("\r");
				count = 0;
			} else {
				fwLevel.append(returnArrayList.get(i));
				fwLevel.append(",");
				count++;
			}
		}
		
		//add the time taken data to the file
		System.out.println("///////////////////////////score: " + Math.abs(map.getTimeTaken()));
		fwLevel.append(Long.toString(Math.abs(map.getTimeTaken())));
		fwLevel.append("\r");
		//add the parent level name, so the continued map can be restarted properly
		fwLevel.append(map.getParentLevelName());
		fwLevel.close();
		
		//create the player data file
		FileWriter fwPlayer = new FileWriter(fileName + "player.txt");
		//add coordinates
		fwPlayer.append(Integer.toString(player.getX()));
		fwPlayer.append("\r");
		fwPlayer.append(Integer.toString(player.getY()));
		fwPlayer.append("\r");
		//add token count
		fwPlayer.append(Integer.toString(inventory.getTokens()));
		fwPlayer.append("\r");
		//add inventory, one item per line
		for (int i = 0; i < inventory.getInventory().size(); i++) {
			fwPlayer.append(inventory.getInventory().get(i).getCollectibleName());
			fwPlayer.append("\r");
		}
 
		fwPlayer.close();
		System.out.println(returnArrayList.toString());
		
	} 
	
	/**
 	 * Get the saved profiles.
 	 * @return The profile in an arraylist.
 	 */
	public static ArrayList<Profile> getSavedProfiles() {
		try {
			File fileIn = new File("Profiles.txt");
			Scanner fileScan = new Scanner(fileIn);
			return getSavedProfiles(fileScan);
		} catch (FileNotFoundException e) {
			return new ArrayList<Profile>();
		}
	}

	/**
 	 * Get the saved profiles.
	 * @param scanner
	 * @return The profile in an arraylist.
 	 */
	private static ArrayList<Profile> getSavedProfiles(Scanner fileScan) {
		ArrayList<Profile> profileList= new ArrayList<Profile>();

		while(fileScan.hasNextLine()) {
			String line = fileScan.nextLine();
			Scanner lineIn = new Scanner(line);
		
			String id = lineIn.next();
			String pass = lineIn.next();
			String avatar = lineIn.next();
			ArrayList<int[]> levelScores = new ArrayList<int[]>();
			while(lineIn.hasNext()) {
				int[] temp = new int[3];
				for(int i = 0; i < 3; i++) {
					int tempInt = lineIn.nextInt();
					temp[i] = tempInt;
				}
				levelScores.add(temp);
			}
			profileList.add(IO.makeProfileObj(id, pass, levelScores, avatar));
			lineIn.close();
		}
		
		return profileList;
	}
	
	/**
 	 * A method that saves the profile.
 	 * @param profileList The profile that is going to save.
 	 */
	public static void saveProfiles(ArrayList<Profile> profileList) {
		File fNew = new File("Profiles.txt");
		String source = "";
		
		for(Profile p : profileList) {
			System.out.println(p.toString());
			source = source + p.saveFormat();
		}
		
		try {
		    FileWriter fWrite = new FileWriter(fNew, false);
		    fWrite.write(source);
		    fWrite.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}   
	}	

	/**
 	 * A method that creates a new profile.
 	 * @param id UserId.
	 * @param pass Password.
	 * @param scores Scores on each level.
	 * @param avatar Avatar of the player.
	 * @return The new profile.
 	 */
	
	public static Profile makeProfileObj(String id, String pass, 
					     ArrayList<int[]> scores, String avatar) {
		
		Profile p = new Profile(id, pass, scores, avatar);
		return p;
	}
}
