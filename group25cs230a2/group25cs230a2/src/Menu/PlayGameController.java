package menu;

import java.io.IOException;
import java.util.ArrayList;

import collectibles.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;


public class PlayGameController {
	@FXML private BorderPane bp;
	@FXML private Button restart;
	@FXML private Button save;
	@FXML private Button quit;
	@FXML private Label tokens;
	@FXML private Label keys;
	@FXML private Label items;

	
	@FXML 
	public void Initialize() {
		tokens.setText("Tokens: 0");
	}
	@FXML void restart() {
		System.out.println("restart");
		String fileName = Main.getParentLevelName();
		try {
			Main.playGame(fileName, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void quit() {
		Main.mainMenu();
	}
	
	@FXML
	private void save() throws IOException {
		if (Main.canSave()) {
			System.out.println("Save");
			Main.saveMapToFile();
		}
		
	}

	public PlayGameController() {
		
		
	}

	public void updateInventory() {	
		ArrayList<Collectible> inv = new ArrayList<Collectible>();
		inv = Main.map.getPlayer().getInventory().getInventory();
		
		String printString = "";
		
		if (inv.size() > 0) {
			for (int i = 0; i < inv.size(); i++) {
				printString += Main.map.getPlayer().getInventory().getInventory().get(i).getCollectibleName() + ", ";
			}
			printString += "	Tokens: " + Integer.toString(Main.map.getPlayer().getInventory().getTokens());
			tokens.setText(printString);
		} else if (Main.map.getPlayer().getInventory().getTokens() > 1) {
			printString += "	Tokens: " + Integer.toString(Main.map.getPlayer().getInventory().getTokens());
			tokens.setText(printString);	
		}		
	}

}
