package Menu;

import game.*;
import player.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import cells.*;
import enemies.*;
import collectibles.*;

public class IO {

	public IO() {
		// TODO Auto-generated constructor stub
	}

	/*
	
	FileWriter csvWriter = new FileWriter("new.csv");
	csvWriter.append("Name");
	csvWriter.append(",");
	csvWriter.append("Role");
	csvWriter.append(",");
	csvWriter.append("Topic");
	csvWriter.append("\r");

	for (List<String> rowData : rows) {
	    csvWriter.append(String.join(",", rowData));
	    csvWriter.append("\r");
	}

	csvWriter.flush();
	csvWriter.close();
	
	*/
	
	public static void saveMapToFile(Map map) throws IOException {
		Player player = map.getPlayer();
		Inventory inventory = player.getInventory();
		
		String fileName = map.getPlayer().getProfile().getUserID();
		ArrayList<String> returnArrayList = new ArrayList<String>();
		for (int y = 0; y < map.getWidth(); y++) {
			for (int x = 0; x < map.getHeight(); x++) {
				String addToArray = map.getAt(x, y).getCellName();
				
				for (int i = 0; i < map.getCollectibles().size(); i++) {
					if (map.getCollectibles().get(i).getX() == x & map.getCollectibles().get(i).getY() == y & map.getCollectibles().get(i).isCollected() == false) {
						addToArray = map.getCollectibles().get(i).getCollectibleName();
					}
				}
				for (int j = 0; j < map.getEnemies().size(); j++) {
					if (map.getEnemies().get(j).getX() == x) {
						if (map.getEnemies().get(j).getY() == y) {
							addToArray = map.getEnemies().get(j).getEnemyName();
						}
					}
				}
				
				if (map.getAt(x, y).getClass() == new Goal().getClass()) {
					addToArray = "goal";
				}
				
				if (player.getX() == x & player.getY() == y) {
					addToArray = "player";
				}
				returnArrayList.add(addToArray);
			}
		}	
		FileWriter fwLevel = new FileWriter(fileName + "level.csv");
		fwLevel.append(Integer.toString(map.getWidth()));
		fwLevel.append(",");
		fwLevel.append(Integer.toString(map.getHeight()));
		fwLevel.append("\r");
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
		fwLevel.append(map.getParentLevelName());
		fwLevel.close();
		
		
		FileWriter fwPlayer = new FileWriter(fileName + "player.txt");
		fwPlayer.append(Integer.toString(player.getX()));
		fwPlayer.append("\r");
		fwPlayer.append(Integer.toString(player.getY()));
		fwPlayer.append("\r");
		fwPlayer.append(Integer.toString(inventory.getTokens()));
		fwPlayer.append("\r");
		for (int i = 0; i < inventory.getInventory().size(); i++) {
			fwPlayer.append(inventory.getInventory().get(i).getCollectibleName());
			fwPlayer.append("\r");
		}
		
		
		//save 
		
		fwPlayer.close();
		System.out.println(returnArrayList.toString());
		
	}
	
	public static ArrayList<Profile> getSavedProfiles(){
		Scanner fileIn = new Scanner("Profiles.txt");
		ArrayList<Profile> profileList= new ArrayList<Profile>();
		
		while(fileIn.hasNextLine()) {
			String line = fileIn.nextLine();
			Scanner lineIn = new Scanner(line);
			
			String id = lineIn.next();
			String pass = lineIn.next();
			int highLevel = lineIn.nextInt();
			ArrayList<Integer> levelScores = new ArrayList<Integer>();
			lineIn.useDelimiter(";");
			while(lineIn.hasNextInt()) {
				levelScores.add(lineIn.nextInt());
			}
			
			profileList.add(makeProfileObj(id, pass, highLevel, levelScores));
		}
		
		return profileList;
	}
	
	public static Profile makeProfileObj(String id, String pass, int highLevel, ArrayList<Integer> scores) {
		Profile p = new Profile(id, pass, highLevel, scores);
		return p;
	}
}
