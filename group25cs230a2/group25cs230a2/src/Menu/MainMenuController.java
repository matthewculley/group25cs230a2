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

public class MainMenuController {
	@FXML private BorderPane borderpane;
	@FXML private Button play;
	@FXML private Button selectProfile;
	@FXML private Button quit;
	@FXML private ImageView imageView;
	@FXML private Label motd;
	
	
	
	@FXML public void initialize() {
		try {
			motd.setText(Main.getMotd());
		} catch (Exception e) {
			motd.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
		}
	}
	
	@FXML 
	private void quit(ActionEvent event) {
		Main.quit();
	}
	

	@FXML 
	private void play(ActionEvent event) {
		System.out.println("profile");
		Stage stage = (Stage) play.getScene().getWindow();
		Main.levelSelectScene();
	}
	
	@FXML 
	private void selectProfile(ActionEvent event) {
		System.out.println("profile");
	}
	


}
