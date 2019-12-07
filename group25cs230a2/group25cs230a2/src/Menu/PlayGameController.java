package menu;

import java.io.IOException;
import java.util.ArrayList;

import collectibles.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class PlayGameController {
	@FXML private BorderPane bp;
	@FXML private Button restart;
	@FXML private Button save;
	@FXML private Button quit;
	@FXML private Label tokens;
	@FXML private Label keys;
	@FXML private Label items;

	/**
	 * On startup, set the text of the inventory label to 0 tokens
	 */
	@FXML 
	public void Initialize() {
		//add a key listener to update the inventory after each move
		Main.stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> updateInventory(event));
		tokens.setText("Tokens: 0");
	}
	
	/**
	 * When the restart button is pressed, cause the game to restart the level
	 * currently being played
	 */
	@FXML void restart() {
		System.out.println("restart");
		String fileName = Main.getMapFileName();
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
	
	public void updateInventory(KeyEvent event) {	
		System.out.println("hey");
		ArrayList<Collectible> inv = new ArrayList<Collectible>();
		inv = Main.getMap().getPlayer().getInventory().getInventory();
		
		String printString = "";
		
		if (inv.size() > 0) {
			for (int i = 0; i < inv.size(); i++) {
				printString += Main.getMap().getPlayer().getInventory().getInventory().get(i).getCollectibleName() + ", ";
			}
			printString += "	Tokens: " + Integer.toString(Main.getMap().getPlayer().getInventory().getTokens());
			tokens.setText(printString);
		} else if (Main.getMap().getPlayer().getInventory().getTokens() > 1) {
			printString += "	Tokens: " + Integer.toString(Main.getMap().getPlayer().getInventory().getTokens());
			tokens.setText(printString);	
		}	
		event.consume();
	}

}
