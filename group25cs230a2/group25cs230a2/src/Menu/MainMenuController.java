package menu;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * A class that constructs the main menu.
 * @author Matt
 * @version 1.5
 */

public class MainMenuController {
	
	@FXML private BorderPane borderpane;
	@FXML private Button play;
	@FXML private Button Leaderboard;
	@FXML private Button selectProfile;
	@FXML private Button quit;
	@FXML private ImageView imageView;
	@FXML private Label motd;
	
	/**
 	 * Message of the day appear on the main menu.
 	 */
	@FXML
	public void initialize() {
		try {
			motd.setText(Main.getMotd());
		} catch (Exception e) {
			motd.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
		}
	}
	
	@FXML 
	private void leaderboard() {
		Main.leaderboard();
	}
	
	@FXML 
	private void quit(ActionEvent event) {
		System.exit(0);
	}
	
	@FXML 
	private void play(ActionEvent event) {
		System.out.println("profile");
		Main.selectLevel();
	}
	
	@FXML 
	private void selectProfile(ActionEvent event) {
		System.out.println("profile");
		Main.selectProfileInGame();
	}
}
