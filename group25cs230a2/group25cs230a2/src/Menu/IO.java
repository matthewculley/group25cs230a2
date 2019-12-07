package menu;

import game.*;
import player.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
		map.addToTime(System.currentTimeMillis() - map.getTimeStarted());
//		System.out.println("\nStarted: " + map.getTimeStarted());
//		
//		System.out.println("finished: " + System.currentTimeMillis());
//		System.out.print("taken: ");
//		System.out.println(System.currentTimeMillis() - map.getTimeStarted());
//		
		System.out.println("Time Taken: " + map.getTimeTaken());
		Player player = map.getPlayer();
		Inventory inventory = player.getInventory();
//		System.out.println(map.toString());
		String fileName = map.getPlayer().getProfile().getUserID();
		ArrayList<String> returnArrayList = new ArrayList<String>();

		
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				String addToArray = map.getAt(x, y).getCellName();
				System.out.println(map.getAt(x,y).toString());

				
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
		fwLevel.append(Long.toString(map.getTimeTaken()));
		fwLevel.append("\r");
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
}
