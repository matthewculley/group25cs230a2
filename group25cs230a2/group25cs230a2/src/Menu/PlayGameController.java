package menu;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class PlayGameController {
	@FXML private Canvas canvas;
	@FXML private BorderPane borderpane;
	@FXML private ToolBar tb;
	@FXML private Button restart;
	@FXML private Button save;
	@FXML private Button quit;

	
	
	@FXML 
	private void restart(ActionEvent event) {
		System.out.println("restart");
		Stage stage = (Stage) restart.getScene().getWindow();
		stage.close();
		String fileName = Main.getMapFileName();
		try {
			Main.playGame(stage, fileName, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void quit(ActionEvent event) {
		System.exit(0);
	}
	
	@FXML
	private void save(ActionEvent event) throws IOException {
		System.out.println("Save");
		Main.saveMapToFile();
	}
	
	
	public PlayGameController() {
		
		
	}

}
