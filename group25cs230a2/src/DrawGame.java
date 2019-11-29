import java.awt.Dimension;
import java.awt.Toolkit;

import cells.*;
import enemies.Enemy;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DrawGame extends Application {
	
	int playerX = 0;
	int playerY = 0;
	
	//create the map file for the given level
	private Map map = new Map("test.csv");
		
	// The dimensions of the window
	//	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//	private static final int WINDOW_WIDTH = (int) screenSize.getWidth();
	//	private static final int WINDOW_HEIGHT = (int) screenSize.getHeight();

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 1000;

	// The dimensions of the canvas
	private static final int CANVAS_WIDTH = 750;
	private static final int CANVAS_HEIGHT = 750;

	// The size of each cell
	private static int GRID_CELL_WIDTH = 50;
	private static int GRID_CELL_HEIGHT = 50;
	
	// The canvas in the GUI. This needs to be a global variable
	// (in this setup) as we need to access it in different methods.
	// We could use FXML to place code in the controller instead.
	private Canvas canvas;
	
	
	public void start(Stage primaryStage) {
		// Build the GUI 
		Pane root = buildGUI();

		// Create a scene from the GUI
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
			
		//Register an event handler for key presses
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
		
		// Display the scene on the stage
		drawGame();
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
	/**
	 * Process a key event due to a key being pressed, e.g., to the player.
	 * @param event The key event that was pressed.
	 */
	public void processKeyEvent(KeyEvent event) {
		switch (event.getCode()) {
		    case RIGHT:
		    	// Right key was pressed. So move the player right by one cell.
		    	if (checkValidMove(playerX + 1, playerY)) {
			    	playerX++;
		    	} 
//		    	System.out.println("\n" + map.getAt(playerX, playerY).toString());
	        	break;	
		    case LEFT:
		    	// Right key was pressed. So move the player right by one cell.
		    	if (checkValidMove(playerX - 1, playerY)) {
			    	playerX--;
		    	} 
	        	break;		
		    case UP:
		    	// Right key was pressed. So move the player right by one cell.
		    	if (checkValidMove(playerX, playerY - 1)) {
			    	playerY--;
		    	} 
	        	break;		
		    case DOWN:
		    	// Right key was pressed. So move the player right by one cell.
		    	if (checkValidMove(playerX, playerY + 1)) {
			    	playerY++;
		    	} 
	        	break;		
		    
	        default:
	        	// Do nothing
	        	break;
		}
		
		// Redraw game as the player may have moved.
		drawGame();
		System.out.println("(" + playerX + "," + playerY + ")");
		
		// Consume the event. This means we mark it as dealt with. This stops other GUI nodes (buttons etc) responding to it.
		event.consume();
	}
	
	private boolean checkValidMove(int x, int y) {
		if (x >= map.getWidth() || x < 0 || y >= map.getHeight() || y < 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public void drawGame() {
		// Get the Graphic Context of the canvas. This is what we draw on.
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		// Clear canvas
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				
		int offset = 7;
		
		int newZeroX = playerX - offset;
		int newZeroY = playerY - offset;
		
		if (newZeroX < 0) {
			newZeroX = 0;
		}
	
		while (newZeroX + 15 > map.getWidth()) {
			newZeroX--;
		}
		
		if (newZeroY < 0) {
			newZeroY = 0;
		}
		
		while (newZeroY + 15 > map.getHeight()) {
			newZeroY--;
		}

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				int x = newZeroY + j;
				int y = newZeroX + i;
				
				Cell currentCell = map.getAt(x, y);

				gc.drawImage(currentCell.getSprite(), i * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
				
//				System.out.println("(" + x + "," + y + ")" + currentCell.toString() + "NewZeroCoords" + "(" + newZeroX + "," + newZeroY + ")");

				
				if (playerX == y & playerY == x) {
					gc.drawImage(new Image("enemyWall.png"), i * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
				}
				
				if (newZeroX == y & newZeroY == x) {
					gc.drawImage(new Image("enemyStraight.png"), i * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
				}
				
				
			}
		}
		
		//draw enemies
		
		//draw tokens 
		
		//draw player
		
		
		
		
		
	}
	
	/**
	 * Create the GUI.
	 * @return The panel that contains the created GUI.
	 */
	private Pane buildGUI() {
		// Create top-level panel that will hold all GUI
		BorderPane root = new BorderPane();
				
		// Create canvas
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		root.setCenter(canvas);
		
		// Create a toolbar with some nice padding and spacing
		HBox toolbar = new HBox();
		toolbar.setSpacing(10);
		toolbar.setPadding(new Insets(10, 10, 10, 10)); 
		root.setTop(toolbar);
		
		// Create toolbar content
		Button restartButton = new Button("Restart");
		toolbar.getChildren().add(restartButton);

		Button movePlayerToCenterButton = new Button("Center");
		toolbar.getChildren().add(movePlayerToCenterButton);
		
		// Add button event handlers
		restartButton.setOnAction(e -> {
			restartGame();
		});
		
		movePlayerToCenterButton.setOnAction(e -> {
			movePlayerToCenter();
		});
		
		return root;
	}

	/**
	 * Restart the game.
	 */
	public void restartGame() {
		// We just move the player to cell (0, 0) 
		playerX = 0;
		playerY = 0;
		drawGame();
	}
	
	/**
	 * Move the player to roughly the center of the grid.
	 */
	public void movePlayerToCenter() {
		// We just move the player to cell (2, 2)
		playerX = 2;
		playerY = 2;
		drawGame();		
	}
	
	public static void main(String[] args) {

		launch(args);
		
	}
	

	
}
