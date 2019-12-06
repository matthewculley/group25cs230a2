package menu;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;


public class PlayGameController {
	@FXML private Canvas canvas;
	@FXML private BorderPane borderpane;
	@FXML private ToolBar tb;
	@FXML private Button restart;
	@FXML private Button save;
	@FXML private Button quit;
	
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

}
