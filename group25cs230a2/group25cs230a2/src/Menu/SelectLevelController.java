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

	@FXML private Button play;
	@FXML private Button back;
	@FXML private Button continueLevel;
	@FXML private ImageView imageView;
	
	
	
	
	@FXML
	public void initialize() {
		int highest = Main.profile.getHighestCompletedLevel();
		try {
			 imageView.setImage(new Image("level" + (highest + 1) + ".png"));
		} catch (IllegalArgumentException e) {
			imageView.setImage(new Image("level" + (highest) + ".png"));
		}
	}  
	
	@FXML 
	private void back(ActionEvent event) {
		System.out.println("Back");
		Main.mainMenu();
	}
	
	@FXML private void play() {
		System.out.println("play");
		try {
			int highest = Main.profile.getHighestCompletedLevel();
			System.out.println("highest play(): " + highest);
			if (highest == 0) {
				Main.playGame("level1.csv", false);
			} else {
				Main.playGame("level" + (highest + 1) + ".csv", false);
			}
			
		} catch (FileNotFoundException e) {
			try {
				Main.playGame("level1.csv", false);		
			} catch (IOException r) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML private void continueLevel() {
		System.out.println("continue");
		Profile profile = Main.getProfile();
		try {
			Main.playGame(profile.getUserID(), true);
		} catch (FileNotFoundException e) {
			Main.selectLevel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	
}
