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

public class SelectLevelController {

	@FXML private BorderPane borderpane;
	@FXML private Button play;
	@FXML private Button back;
	@FXML private Button continueLevel;
	@FXML private Button previousLevel;
	@FXML private Button nextLevel;
	@FXML private ImageView imageView;
	
	
	private ArrayList<String> levels = new ArrayList<String>();
	//private Image level1 = new Image("level1.png");
//	private Image level2 = new Image("level2.png");
	
	public void initialize() {
		//imageView.setImage(level1);
		levels.add("level1.csv");
//		levels.add("level2.csv");
		 
	}
	
	@FXML 
	private void back(ActionEvent event) {
		System.out.println("Back");
		Stage stage = (Stage) play.getScene().getWindow();
		stage.close();
		Main.mainMenu(stage);
	}
	
	@FXML private void play() {
		System.out.println("play");
		Stage stage = (Stage) play.getScene().getWindow();
		stage.close();
		try {
			Main.playGame(stage, "level1.csv", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML public void previousLevel() {
		
	}
	
	@FXML public void nextLevel() {
		
	}
	
	public SelectLevelController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML private void continueLevel() {
		System.out.println("play");
		Profile profile = Main.getProfile();
		Stage stage = (Stage) play.getScene().getWindow();
		stage.close();
		
		try {
			Player p = new Player(profile, profile.getUserID());
			Main.playGame(stage, profile.getUserID(), true);
		} catch (FileNotFoundException e) {
			//say no saved game
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
