package menu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import player.Player;
import player.Profile;
import java.io.FileNotFoundException;

public class SelectLevelController {

	@FXML private BorderPane borderpane;
	@FXML private Button play;
	@FXML private Button back;
	@FXML private Button continueLevel;
	@FXML private ImageView imageView;
	@FXML private Label label;
	
	
	
	
	@FXML
	public void initialize() {
		Image image = new Image("level1.png");
        imageView.setImage(image);
	}
	
	@FXML 
	private void back(ActionEvent event) {
		System.out.println("Back");
		Stage stage = (Stage) back.getScene().getWindow();
		Main.mainMenu();
	}
	
	@FXML private void play() {
		System.out.println("play");
		Stage stage = (Stage) play.getScene().getWindow();
		try {
			Main.playGame("level2.csv", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@FXML private void continueLevel() {
		System.out.println("continue");
		Profile profile = Main.getProfile();
		Stage stage = (Stage) continueLevel.getScene().getWindow();	
		try {
			Main.playGame(profile.getUserID(), true);
		} catch (FileNotFoundException e) {
			Main.levelSelectScene();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	
}
