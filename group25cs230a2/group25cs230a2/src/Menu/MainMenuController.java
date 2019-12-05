package menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainMenuController {
	@FXML private BorderPane borderpane;
	@FXML private Button play;
	@FXML private Button selectProfile;
	@FXML private Button quit;
	
	
	@FXML 
	private void quit(ActionEvent event) {
		Main.quit();
	}
	

	@FXML 
	private void play(ActionEvent event) {
		System.out.println("profile");
		Stage stage = (Stage) play.getScene().getWindow();
		stage.close();
		Main.levelSelectScene(stage);
	}
	
	@FXML 
	private void selectProfile(ActionEvent event) {
		System.out.println("profile");
	}
	


}
