package menu;
//package menu;
//import java.awt.Dimension;
//import java.awt.Toolkit;
//import java.util.ArrayList;
//
//import cells.*;
//import enemies.Enemy;
//import game.Map;
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import player.*;
//import collectibles.*;
//
//public class PlayGame extends Application {
//	
//	private static final int WINDOW_WIDTH = 1000;
//	private static final int WINDOW_HEIGHT = 1000;
//
//	// The dimensions of the canvas
//	private static final int CANVAS_WIDTH = 750;
//	private static final int CANVAS_HEIGHT = 750;
//
//	// The size of each cell
//	private static int GRID_CELL_WIDTH = 50;
//	private static int GRID_CELL_HEIGHT = 50;
//	
//	private Canvas canvas;
//	
//
//	public static void main(String[] args) {
//		launch(args);
//	}
//
//	int[] test = {1,2,3};
//	ArrayList<int[]> testt = new ArrayList<int[]>();
////	public Profile (String userID, String password, int highestCompletedLevel, ArrayList<int[]> levelScores)
//	Profile profile = new Profile("Matthew", "1password", 0, testt);
//
//	//create the map file for the given level
//	private Map map = new Map(profile, "test.csv");
//
//	
//	/**
//	 * Process a key event due to a key being pressed, e.g., to the player.
//	 * @param event The key event that was pressed.
//	 */
//	public void processKeyEvent(KeyEvent event) {
//		int x = map.getPlayer().getX();
//		int y = map.getPlayer().getY();
//		System.out.println();
//		switch (event.getCode()) {
//		    case RIGHT: 
//		    	// Right key was pressed. So move the player right by one cell.
//		    	if (checkValidMove(x + 1, y)) {
//			    	map.getPlayer().setX(x + 1);
//		    	} 
//		    	event.consume();
//		    	if (map.isDifferentPosition(x, y, map.getPlayer().getX(), map.getPlayer().getY())) {
//		    		afterValidMove();
//		    	}
////		    	System.out.println("\n" + map.getAt(map.getPlayer().getX(), map.getPlayer().getY()).toString());
//	        	break;	
//		    case LEFT:
//		    	// Right key was pressed. So move the player right by one cell.
//		    	if (checkValidMove(x - 1, y)) {
//		    		map.getPlayer().setX(x - 1);
//		    	} 
//		    	event.consume();
//		    	if (map.isDifferentPosition(x, y, map.getPlayer().getX(), map.getPlayer().getY())) {
//		    		afterValidMove();
//		    	}
//	        	break;		
//		    case UP:
//		    	// Right key was pressed. So move the player right by one cell.
//		    	if (checkValidMove(x, y - 1)) {
//		    		map.getPlayer().setY(y - 1);
//		    	} 
//		    	event.consume();
//		    	if (map.isDifferentPosition(x, y, map.getPlayer().getX(), map.getPlayer().getY())) {
//		    		afterValidMove();
//		    	}
//	        	break;		
//		    case DOWN:
//		    	// Right key was pressed. So move the player right by one cell.
//		    	if (checkValidMove(x, y + 1)) { 
//		    		map.getPlayer().setY(y + 1);
//		    	} 
//		    	if (map.isDifferentPosition(x, y, map.getPlayer().getX(), map.getPlayer().getY())) {
//		    		afterValidMove();
//		    	}
//	        	break;		
//		    
//	        default:
//	        	// Do nothing
//		    	event.consume();
//	        	break;
//		}
//
////		System.out.println("Inventory: " + map.getPlayer().getInventory().toString());
////		System.out.println("Current Position: " + "(" + map.getPlayer().getX() + "," + map.getPlayer().getY() + ")" + "\nStanding on: " + map.getAt(map.getPlayer().getX(), map.getPlayer().getY()).toString());
//	}
//	
//	private void afterValidMove() {
//		Player player = map.getPlayer();
//		int x = player.getX();
//		int y = player.getY();
//
//		//move enemies
//				for (int k = 0; k < map.getEnemies().size(); k++) {
//					map.getEnemies().get(k).move(map);
//				}
//				
//				//check if player dead
//				for (int k = 0; k < map.getEnemies().size(); k++) {
//					if (map.getEnemies().get(k).getX() == map.getPlayer().getX() & map.getEnemies().get(k).getY() == map.getPlayer().getY()) {
//						System.out.println("dead");
//						//deal with death here
//					}
//				}
//				
//				//check if player collect anything
//				for (int k = 0; k < map.getCollectibles().size(); k++) {
//					if (map.getCollectibles().get(k).getX() == map.getPlayer().getX() & map.getCollectibles().get(k).getY() == map.getPlayer().getY() & map.getCollectibles().get(k).isCollected() == false) {
//						
//						//add to inventory
//						map.getPlayer().getInventory().addItem(map.getCollectibles().get(k), map);
//
//						//deal with death here
//					}
//				}
//				
//				//teleport player
//				//if player on a teleporter
//				if (map.getAt(x, y).getClass() == (new Teleporter()).getClass()) {
//					Teleporter tele = (Teleporter) map.getAt(x, y);
//					map.getPlayer().setX(tele.getPartner().getX());
//					map.getPlayer().setY(tele.getPartner().getY());
//				}
//				// Redraw game as the player may have moved.
//				drawGame();
//	}
//	
//	private boolean checkValidMove(int x, int y) {
//		System.out.print("Check if cell (" + x + "," + y + ") is a valid move: ");
//		boolean returnValue = false;
//		
//		//if out of bounds
//		if (x >= map.getWidth() || x < 0 || y >= map.getHeight() || y < 0) {
//			returnValue = false;
//		//else if the spot is an impassable cell e.g. wall
//		} else if (map.getAt(x, y).isPassable() == false) {
//			//if the player has water boots
//			if (map.getPlayer().getInventory().hasItem(new Flippers()) & map.getAt(x, y).getClass() == new Water().getClass()) {
//				returnValue = true;
//			}
//			//if the player has fire boots
//			if (map.getPlayer().getInventory().hasItem(new FireBoots()) & map.getAt(x, y).getClass() == new Fire().getClass()) {
//				returnValue = true;
//			}
//		} else { 
//			returnValue = true;
//		}
//		System.out.println(returnValue);
//		return returnValue;
//	}
//		
//	
//	public void drawGame() {
//		
//		int offset = 7;	//distance from player to each side of the screen
//		int iteratorX = 15; //amount of rows to be drawn
//		int iteratorY = 15;	//ammount of rows to be drawn
//
//		// Get the Graphic Context of the canvas. This is what we draw on.
//		GraphicsContext gc = canvas.getGraphicsContext2D();
//		
//		// Clear canvas
//		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//				
//		int newZeroX = map.getPlayer().getX() - offset; //the x to start drawing from
//		int newZeroY = map.getPlayer().getY() - offset; //the y to start drawing from
//
//		//update iterators for small levels
//		if (map.getWidth() < iteratorX) {
//			iteratorX = map.getWidth();
//		}
//		if (map.getHeight() < iteratorY) {
//			iteratorY = map.getHeight();
//		}
//		
//		//ensure new zero coordiantes cannot be invalid
//		while (newZeroX + iteratorX > map.getWidth()) {
//			newZeroX--;
//		}
//		if (newZeroX < 0) {
//			newZeroX = 0;
//		}
//		if (newZeroY < 0) {
//			newZeroY = 0;
//		}
//		while (newZeroY + iteratorY > map.getHeight()) {
//			newZeroY--;
//		}
//
//		Cell currentCell;
//		for (int i = 0; i < iteratorX; i++) {
//			for (int j = 0; j < iteratorY; j++) {
//				
//				int x = newZeroX + i;
//				int y = newZeroY + j;
//				
//				if (map.isValidCoords(x,y)) {
//					currentCell = map.getAt(x, y);
//					gc.drawImage(currentCell.getSprite(), i * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
//				}
//				
//				//draw the player
//				if (map.getPlayer().getX() == x & map.getPlayer().getY() == y) {
//					gc.drawImage(new Image("flippers.png"), i * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
//				}
//				
//				//draw enemies
//				for (int k = 0; k < map.getEnemies().size(); k++) {
//					if (map.getEnemies().get(k).getX() == x & map.getEnemies().get(k).getY() == y) {
////						System.out.println(map.getEnemies().get(k).toString());
//						gc.drawImage(map.getEnemies().get(k).getSprite(), i * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
//					}
//				}
//				
//				//draw collectibles
//				for (int k = 0; k < map.getCollectibles().size(); k++) {
//					if (map.getCollectibles().get(k).getX() == x & map.getCollectibles().get(k).getY() == y & map.getCollectibles().get(k).isCollected() == false) {
////						System.out.println(map.getCollectibles().get(k).toString());
//						gc.drawImage(map.getCollectibles().get(k).getSprite(), x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
//					}
//				}
//			}
//		}
//	}
//}
//
//
//
////public void start(Stage primaryStage) {
////// Build the GUI 
////Pane root = buildGUI();
////
////// Create a scene from the GUI
////Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
////	
//////Register an event handler for key presses
////scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
////
////// Display the scene on the stage
////drawGame();
////primaryStage.setScene(scene);
////primaryStage.show();		
////}
//
//
//
//
//
//
///**
// * Create the GUI.
// * @return The panel that contains the created GUI.
// */
////private Pane buildGUI() {
////	// Create top-level panel that will hold all GUI
////	BorderPane root = new BorderPane();
////			
////	// Create canvas
////	
////	root.setCenter(canvas);
////	
////	// Create a toolbar with some nice padding and spacing
////	HBox toolbar = new HBox();
////	toolbar.setSpacing(10);
////	toolbar.setPadding(new Insets(10, 10, 10, 10)); 
////	root.setTop(toolbar);
////	
////	// Create toolbar content
////	Button restartButton = new Button("Restart");
////	toolbar.getChildren().add(restartButton);
////
////	Button movePlayerToCenterButton = new Button("Center");
////	toolbar.getChildren().add(movePlayerToCenterButton);
////
////	return root;
////}
//
//
////The dimensions of the window
//	//	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//	//	private static final int WINDOW_WIDTH = (int) screenSize.getWidth();
//	//	private static final int WINDOW_HEIGHT = (int) screenSize.getHeight();
//
////	private static final int WINDOW_WIDTH = 1000;
////	private static final int WINDOW_HEIGHT = 1000;
////
////	// The dimensions of the canvas
////	private static final int CANVAS_WIDTH = 750;
////	private static final int CANVAS_HEIGHT = 750;
////
////	// The size of each cell
////	private static int GRID_CELL_WIDTH = 50;
////	private static int GRID_CELL_HEIGHT = 50;
//	
//	// The canvas in the GUI. This needs to be a global variable
//	// (in this setup) as we need to access it in different methods.
//	// We could use FXML to place code in the controller instead.
//	
//	
//	
//
//	