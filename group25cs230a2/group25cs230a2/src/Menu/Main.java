package menu;

import java.io.IOException;
import java.util.ArrayList;
import cells.*;
import collectibles.*;
import game.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import player.Player;
import player.Profile;

public class Main extends Application{

	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 800;
	
	// The dimensions of the canvas
	private static final int CANVAS_WIDTH = 750;
	private static final int CANVAS_HEIGHT = 750;
	
	// The size of each cell
	private static int GRID_CELL_WIDTH = 50;
	private static int GRID_CELL_HEIGHT = 50;
	
	static Map map;
	static Canvas canvas;
	
	int[] test = {1,2,3};
	static ArrayList<int[]> testt = new ArrayList<int[]>();
	static Profile profile = new Profile("Matthew", "1password", 0, testt);

	
	public void start(Stage primaryStage) {
		mainMenu(primaryStage);
	}
	public static void mainMenu(Stage primaryStage) {		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SelectLevelController.class.getResource("MainMenu.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void levelSelectScene(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SelectLevelController.class.getResource("SelectLevel.fxml"));
			BorderPane root = (BorderPane) loader.load();
//			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT);			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void playGame(Stage primaryStage, String fileName, boolean continueGame) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SelectLevelController.class.getResource("PlayGame.fxml"));
		BorderPane root = (BorderPane) loader.load();
		Scene scene = new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT);
		canvas = new Canvas(getCanvasWidth(), getCanvasHeight());
		
		root.setCenter(canvas);
		
		if (continueGame == false) {
			try {
				//create map
				map = new Map(profile, fileName);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				//create map
				map = new Map(fileName + "level.csv");
				Player player = new Player(profile, fileName);
				map.addPlayer(profile, player);
				map.getPlayer().getInventory().unlockDoors(map);
			} catch(Exception e) {
				e.printStackTrace();
			}

		}
		drawGame();
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
		primaryStage.setScene(scene);
		primaryStage.show();	
		
		
	}
	
	/**
	 * Process a key event due to a key being pressed, e.g., to the player.
	 * @param event The key event that was pressed.
	 */
	public static void processKeyEvent(KeyEvent event) {
		int x = map.getPlayer().getX();
		int y = map.getPlayer().getY();
		switch (event.getCode()) {
		    case RIGHT: 
		    	// Right key was pressed. So move the player right by one cell.
		    	if (checkValidMove(x + 1, y)) {
			    	map.getPlayer().setX(x + 1);
		    	} 
		    	event.consume();
		    	if (map.isDifferentPosition(x, y, map.getPlayer().getX(), map.getPlayer().getY())) {
		    		afterValidMove();
		    	}
//		    	System.out.println("\n" + map.getAt(map.getPlayer().getX(), map.getPlayer().getY()).toString());
	        	break;	
		    case LEFT:
		    	// Right key was pressed. So move the player right by one cell.
		    	if (checkValidMove(x - 1, y)) {
		    		map.getPlayer().setX(x - 1);
		    	} 
		    	event.consume();
		    	if (map.isDifferentPosition(x, y, map.getPlayer().getX(), map.getPlayer().getY())) {
		    		afterValidMove();
		    	}
	        	break;		
		    case UP:
		    	// Right key was pressed. So move the player right by one cell.
		    	if (checkValidMove(x, y - 1)) {
		    		map.getPlayer().setY(y - 1);
		    	} 
		    	event.consume();
		    	if (map.isDifferentPosition(x, y, map.getPlayer().getX(), map.getPlayer().getY())) {
		    		afterValidMove();
		    	}
	        	break;		
		    case DOWN:
		    	// Right key was pressed. So move the player right by one cell.
		    	if (checkValidMove(x, y + 1)) { 
		    		map.getPlayer().setY(y + 1);
		    	} 
		    	if (map.isDifferentPosition(x, y, map.getPlayer().getX(), map.getPlayer().getY())) {
		    		afterValidMove();
		    	}
	        	break;		
		    
	        default:
	        	// Do nothing
		    	event.consume();
	        	break;
		}
	}
	
		private static void afterValidMove() {
			Player player = map.getPlayer();
			int x = player.getX();
			int y = player.getY();
			//move enemies
					for (int k = 0; k < map.getEnemies().size(); k++) {
						map.getEnemies().get(k).move(map);
					}
					//check if player dead
					for (int k = 0; k < map.getEnemies().size(); k++) {
						if (map.getEnemies().get(k).getX() == map.getPlayer().getX() & map.getEnemies().get(k).getY() == map.getPlayer().getY()) {
							System.out.println("dead");
							//load death scene
							//1 button restart
							//1 button quit
						}
					}
					//check if player collect anything
					for (int k = 0; k < map.getCollectibles().size(); k++) {
						if (map.getCollectibles().get(k).getX() == map.getPlayer().getX() & map.getCollectibles().get(k).getY() == map.getPlayer().getY() & map.getCollectibles().get(k).isCollected() == false) {
							
							//add to inventory
							map.getPlayer().getInventory().addItem(map.getCollectibles().get(k), map);
						}
					}
					//teleport player
					//if player on a teleporter
					if (map.getAt(x, y).getClass() == (new Teleporter()).getClass()) {
						Teleporter tele = (Teleporter) map.getAt(x, y);
						map.getPlayer().setX(tele.getPartner().getX());
						map.getPlayer().setY(tele.getPartner().getY());
					}
					
					//check if on goal
					if (map.getAt(player.getX(), player.getY()).isGoal()) {
						System.out.println("victory");
						//load victory scene
						//1 button next stage
						//1 button victory
					}
					// Redraw game as the player may have moved.
					drawGame();
		}
		
		private static boolean checkValidMove(int x, int y) {
			boolean returnValue = false;
			//if out of bounds
			if (x >= map.getWidth() || x < 0 || y >= map.getHeight() || y < 0) {
				returnValue = false;
			//else if the spot is an impassable cell e.g. wall
			} else if (map.getAt(x, y).isPassable() == false) {
				//if the player has water boots
				if (map.getPlayer().getInventory().hasItem(new Flippers()) & map.getAt(x, y).getClass() == new Water().getClass()) {
					returnValue = true;
				}
				//if the player has fire boots
				if (map.getPlayer().getInventory().hasItem(new FireBoots()) & map.getAt(x, y).getClass() == new Fire().getClass()) {
					returnValue = true;
				}
			} else { 
				returnValue = true;
			}
			return returnValue;
		}
	
		public static void drawGame() {
			System.out.println(map.toString());
			int offset = 7;	//distance from player to each side of the screen
			int iteratorX = 15; //amount of rows to be drawn
			int iteratorY = 15;	//ammount of rows to be drawn

			// Get the Graphic Context of the canvas. This is what we draw on.
			GraphicsContext gc = canvas.getGraphicsContext2D();
			
			// Clear canvas
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			int newZeroX = map.getPlayer().getX() - offset; //the x to start drawing from
			int newZeroY = map.getPlayer().getY() - offset; //the y to start drawing from

			//update iterators for small levels
			if (map.getWidth() < iteratorX) {
				iteratorX = map.getWidth();
			}
			if (map.getHeight() < iteratorY) {
				iteratorY = map.getHeight();
			}
			//ensure new zero coordiantes cannot be invalid
			while (newZeroX + iteratorX > map.getWidth()) {
				newZeroX--;
			}
			if (newZeroX < 0) {
				newZeroX = 0;
			}
			if (newZeroY < 0) {
				newZeroY = 0;
			}
			while (newZeroY + iteratorY > map.getHeight()) {
				newZeroY--;
			}

			Cell currentCell;
			System.out.println(map.toString());
			for (int i = 0; i < iteratorX; i++) {
				for (int j = 0; j < iteratorY; j++) {
					
					int x = newZeroX + i;
					int y = newZeroY + j;
					
					if (map.isValidCoords(x,y)) {
						currentCell = map.getAt(x, y);
						gc.drawImage(currentCell.getSprite(), i * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
					}
					//draw the player
					if (map.getPlayer().getX() == x & map.getPlayer().getY() == y) {
						gc.drawImage(map.getPlayer().getSprite(), i * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
					}
					//draw enemies
					for (int k = 0; k < map.getEnemies().size(); k++) {
						if (map.getEnemies().get(k).getX() == x & map.getEnemies().get(k).getY() == y) {
							gc.drawImage(map.getEnemies().get(k).getSprite(), i * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
						}
					}
					//draw collectibles
					for (int k = 0; k < map.getCollectibles().size(); k++) {
						if (map.getCollectibles().get(k).getX() == x & map.getCollectibles().get(k).getY() == y & map.getCollectibles().get(k).isCollected() == false) {
							gc.drawImage(map.getCollectibles().get(k).getSprite(), x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
						}
					}
				}
			}
		}
	

	public static void main(String[] args) {
		launch(args);
	}
	
	public static void quit() {
		System.exit(0);
	}
	
	public static String getMapFileName() {
		return map.getParentLevelName();
		
	}
	public static void saveMapToFile() throws IOException {
		System.out.println(map.getPlayer().getProfile().getUserID());
		IO.saveMapToFile(map); 
		// TODO Auto-generated method stub
		
	}
	
	public static Profile getProfile() {
		return profile;
	}
	public static boolean canSave() {
		if (map.getAt(map.getPlayer().getX(), map.getPlayer().getY()).getClass() == new Ground().getClass()) {
			return true;
		} else {
			System.out.println("Can only save on a ground tile");
			return false;
		}
	}
	public static String getParentLevelName() {
		return map.getParentLevelName();
	}
	public static int getCanvasWidth() {
		return CANVAS_WIDTH;
	}
	public static int getCanvasHeight() {
		return CANVAS_HEIGHT;
	}
	
}
