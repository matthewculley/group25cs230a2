import cells.*;
import enemies.Enemy;
import javafx.*;
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
	
	//the map file for the given level
	private Map map = new Map("test.csv");
	private int mapWidth = map.getWidth();
	private int mapHeight = map.getHeight();
	
	
	// The dimensions of the window
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 500;

	// The dimensions of the canvas
	private static final int CANVAS_WIDTH = 500;
	private static final int CANVAS_HEIGHT = 500;

	// The size of each cell
	private static int GRID_CELL_WIDTH = 16;
	private static int GRID_CELL_HEIGHT = 16;
	
	// The canvas in the GUI. This needs to be a global variable
	// (in this setup) as we need to access it in different methods.
	// We could use FXML to place code in the controller instead.
	private Canvas canvas;
	
	
	public void start(Stage primaryStage) {
		// Build the GUI 
		Pane root = buildGUI();
		
		
		
		// Create a scene from the GUI
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
			
		// Register an event handler for key presses
//		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
		
		// Display the scene on the stage
		drawGame();
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
	public void drawGame() {
		// Get the Graphic Context of the canvas. This is what we draw on.
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		// Clear canvas
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		//for loop to draw the game 
		
		for (int i = 0; i < mapWidth * mapHeight; i++) {
			int[] coords = map.indexToCoords(i);
			int x = coords[0];
			int y = coords[1];
			Cell currentCell = map.getAt(x,y);
			System.out.println("index: "+i+ "  "+ x + "," + y + ": " + currentCell.toString());
			gc.drawImage(currentCell.getSprite(), y * GRID_CELL_WIDTH, x * GRID_CELL_HEIGHT);

		}
		
		for (int i = 0; i < map.getEnemies().size(); i++) {
			Enemy enemy = map.getEnemies().get(i); 
			gc.drawImage(enemy.getSprite(), enemy.getX() * GRID_CELL_WIDTH, enemy.getY() * GRID_CELL_HEIGHT);

		}
		
		// Draw player at current location
//		gc.drawImage(player, playerX * GRID_CELL_WIDTH, playerY * GRID_CELL_HEIGHT);			
	}
	
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
//		playerX = 0;
//		playerY = 0;
		drawGame();
	}
	
	/**
	 * Move the player to roughly the center of the grid.
	 */
	public void movePlayerToCenter() {
		// We just move the player to cell (2, 2)
//		playerX = 2;
//		playerY = 2;
		drawGame();		
	}
		
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	
	
}
