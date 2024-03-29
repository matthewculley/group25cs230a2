package menu;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.ArrayList;
import cells.*;
import collectibles.*;
import game.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import player.Player;
import player.Profile;

/**
 * A class that starts the game.
 * @author Matt
 * @version 1.5
 */

public class Main extends Application{

	//Height and width of the game window
	private static final int WINDOW_WIDTH = 850;
	private static final int WINDOW_HEIGHT = 850;

	// The dimensions of the canvas the game is drawn on
	private static final int CANVAS_WIDTH = 750;
	private static final int CANVAS_HEIGHT = 750;

	// The size of each cell in px
	private static int GRID_CELL_WIDTH = 50;
	private static int GRID_CELL_HEIGHT = 50;

	//the map being played
	private static Map map;

	//the stage being displayed
	static Stage stage;

	//the canvas the map displayed on
	static Canvas canvas;

	static ArrayList<Profile> allProfiles;

	static Profile profile;


	/**
	 * Start the game.
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) {
		stage = primaryStage;
		selectProfile();
	}

	/**
	 * Load the select profile scene.
	 */
	public static void selectProfile() {
		//try to laod scene with from MainMenu.fxml
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SelectLevelController.class.getResource("SelectProfile.fxml"));
			BorderPane root = (BorderPane) loader.load();
			//create the scene
			Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
			//set scene and show the stage
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Select a profile while the game is being played.
	 */
	public static void selectProfileInGame() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SelectLevelController.class.getResource("SelectProfileInGame.fxml"));
			BorderPane root = (BorderPane) loader.load();
			//create the scene
			Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
			//set scene and show the stage
			stage.setScene(scene);
			stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load the leaderboard scene.
	 */
	public static void leaderboard() {
		//try to laod scene with from MainMenu.fxml
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SelectLevelController.class.getResource("leaderboard.fxml"));
			BorderPane root = (BorderPane) loader.load();
			//create the scene
			Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
			//set scene and show the stage
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load the main menu scene.
	 */
	public static void mainMenu() {
		//try to laod scene with from MainMenu.fxml
		//System.out.println(profile.toString());
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SelectLevelController.class.getResource("MainMenu.fxml"));
			BorderPane root = (BorderPane) loader.load();
			//create the scene
			Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
			//set scene and show the stage
			stage.setScene(scene);
			stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load the select level scene.
	 */
	public static void selectLevel() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SelectLevelController.class.getResource("SelectLevel.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
			stage.setScene(scene);
			stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load the play game scene and allow the game to be played.
	 * @param fileName The name of the map file.
	 * @param continueGame Wether or not the game is being played from a saved file.
	 * @throws IOException In case of error with the map name.
	 */
	public static void playGame(String fileName, boolean continueGame) throws IOException  {
		//load and display play game scene
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SelectLevelController.class.getResource("PlayGame.fxml"));
		BorderPane root = (BorderPane) loader.load();
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		canvas = new Canvas(getCanvasWidth(), getCanvasHeight());
		root.setCenter(canvas);

		//if not continuing a game
		if (!continueGame) {
			//set the map in Main
			setMap(new Map(profile, fileName));
		} else {
			//otherwise load the map from the file
			setMap(new Map(fileName + "level.csv"));
			//create a new player object form the saved file
			Player player = new Player(profile, fileName);
			//add the player to the map, and update the map to opend doors that the player can open
			getMap().addPlayer(profile, player);
			System.out.println("\n\n\n\n\n" + map.getPlayer().getInventory().toString());
			getMap().getPlayer().getInventory().unlockDoors(map);
		}
		//System.out.println(getMap().toString());
		//draw the game
		drawGame();

		//allow user inputs to be taken
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Handles what happens when a key is pressed during a playthrough.
	 * */
	private static void processKeyEvent(KeyEvent event) {
		int x = getMap().getPlayer().getX();
		int y = getMap().getPlayer().getY();
		switch (event.getCode()) {
		    case RIGHT:
		    	// Right key was pressed. So move the player right by one cell.
		    	if (checkValidMove(x + 1, y)) {
			    	getMap().getPlayer().setX(x + 1);
		    	}
		    	event.consume();
		    	if (getMap().isDifferentPosition(x, y, getMap().getPlayer().getX(), getMap().getPlayer().getY())) {
		    		afterValidMove('r');
		    	}
	        	break;
		    case LEFT:
		    	// Right key was pressed. So move the player right by one cell.
		    	if (checkValidMove(x - 1, y)) {
		    		getMap().getPlayer().setX(x - 1);
		    	}
		    	event.consume();
		    	if (getMap().isDifferentPosition(x, y, getMap().getPlayer().getX(), getMap().getPlayer().getY())) {
		    		afterValidMove('l');
		    	}
	        	break;
		    case UP:
		    	// Right key was pressed. So move the player right by one cell.
		    	if (checkValidMove(x, y - 1)) {
		    		getMap().getPlayer().setY(y - 1);
		    	}
		    	event.consume();
		    	if (getMap().isDifferentPosition(x, y, getMap().getPlayer().getX(), getMap().getPlayer().getY())) {
		    		afterValidMove('u');
		    	}
	        	break;
		    case DOWN:
		    	// Right key was pressed. So move the player right by one cell.
		    	if (checkValidMove(x, y + 1)) {
		    		getMap().getPlayer().setY(y + 1);
		    	}
		    	if (getMap().isDifferentPosition(x, y, getMap().getPlayer().getX(), getMap().getPlayer().getY())) {
		    		afterValidMove('d');
		    	}
	        	break;
	        default:
	        	// Do nothing if any other key pressed
		    	event.consume();
	        	break;
		}
	}

	/**
	 * Method to determine whether a potential move by the player is valid
	 * (i.e. not walking through an impassable cell)
	 * @param x, y - position of where the player is moving to
	 * @return returnValue - true if move is valid, false otherwise
	 */
	private static boolean checkValidMove(int x, int y) {
		boolean returnValue = false;
		//if out of bounds
		if (x >= getMap().getWidth() || x < 0 || y >= getMap().getHeight() || y < 0) {
			returnValue = false;
		//else if the spot is an impassable cell e.g. wall
		} else if (getMap().getAt(x, y).isPassable() == false) {
			return false;
		} else {
			returnValue = true;
		}
		return returnValue;
	}

	/**
	 * Handles what happens after each valid move by the player.
	 * @param direction - represents the direction the player's move
	 * */
	private static void afterValidMove(char direction) {
		int x = getMap().getPlayer().getX();
		int y = getMap().getPlayer().getY();

		//check player walk into enemy
		//for each enemy, if the player and the enemy shair the same position
		for (int k = 0; k < getMap().getEnemies().size(); k++) {
			if (getMap().getEnemies().get(k).getX() == getMap().getPlayer().getX() & getMap().getEnemies().get(k).getY() == getMap().getPlayer().getY()) {
				die();
			}
		}

		//move enemies
		//for every enemy move it
		for (int k = 0; k < getMap().getEnemies().size(); k++) {
			getMap().getEnemies().get(k).move(map);
		}

		//expand gas
		int gasSize = map.getGas().size();
		System.out.println(gasSize);
		for (int i = 0; i < gasSize; i++) {
			map.getGas().get(i).infect(map);
		}

		//check if player collect anything
		for (int k = 0; k < getMap().getCollectibles().size(); k++) {
			if (getMap().getCollectibles().get(k).getX() == getMap().getPlayer().getX() & getMap().getCollectibles().get(k).getY() == getMap().getPlayer().getY() & getMap().getCollectibles().get(k).isCollected() == false) {
				//add to inventory
				getMap().getPlayer().getInventory().addItem(getMap().getCollectibles().get(k), map);
			}
		}

		//teleport player if player on a teleporter
		if (getMap().getAt(x, y).getClass() == (new Teleporter()).getClass()) {
			Teleporter tele = (Teleporter) getMap().getAt(x, y);

			//move the player to the teleporters partners coords
			if (direction == 'u') {
				getMap().getPlayer().setX(tele.getPartner().getX());
				getMap().getPlayer().setY(tele.getPartner().getY() - 1);
			}

			if (direction == 'd') {
				getMap().getPlayer().setX(tele.getPartner().getX());
				getMap().getPlayer().setY(tele.getPartner().getY() + 1);
			}

			if (direction == 'l') {
				getMap().getPlayer().setX(tele.getPartner().getX() - 1);
				getMap().getPlayer().setY(tele.getPartner().getY());
			}

			if (direction == 'r') {
				getMap().getPlayer().setX(tele.getPartner().getX() + 1);
				getMap().getPlayer().setY(tele.getPartner().getY());
			}

		}

		//if player doesnt have flippers and is in water
		if (getMap().getPlayer().getInventory().hasItem(new Flippers()) == false & getMap().getAt(x, y).getClass() == new Water().getClass()) {
			drawGame();
			die();
		}
		//if the player doesnt have fireboots and in fire
		if (getMap().getPlayer().getInventory().hasItem(new FireBoots()) == false & getMap().getAt(x, y).getClass() == new Fire().getClass()) {
			drawGame();
			die();
		}

		//if the player doesnt have gasj mask and in gas
		//System.out.println("GAS SIZE: " + map.getGas().size());
		for (int i = 0; i < map.getGas().size(); i++) {
			if (map.getGas().get(i).getX() == x & map.getGas().get(i).getY() == y & getMap().getPlayer().getInventory().hasItem(new GasMask()) == false) {
				drawGame();
				die();
			}
		}

		//check enemy walk into player
		for (int k = 0; k < getMap().getEnemies().size(); k++) {
			if (getMap().getEnemies().get(k).getX() == getMap().getPlayer().getX() & getMap().getEnemies().get(k).getY() == getMap().getPlayer().getY()) {
				drawGame();
				die();
			}
		}

		//check if on goal
		if (getMap().getAt(getMap().getPlayer().getX(), getMap().getPlayer().getY()).isGoal()) {
			System.out.println("victory");
			win();
		}
		// Redraw game as the player may have moved.
		drawGame();
	}

	/**
	 * Draw the game in its current status.
	 */
	public static void drawGame() {
		int offset = 7;	//distance from player to each side of the screen
		int iteratorX = 15; //amount of rows to be drawn
		int iteratorY = 15;	//ammount of rows to be drawn

		// Get the Graphic Context of the canvas. This is what we draw on.
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Clear canvas
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		int newZeroX = getMap().getPlayer().getX() - offset; //the x to start drawing from
		int newZeroY = getMap().getPlayer().getY() - offset; //the y to start drawing from

		/*
		 * Calculates a zero point that is 7 above and 7 to the left of the player, from this point
		 * the game draws a 15x15 grid, this ensures player is always in the center
		 *
		 * If the player is too close to an edge, the grid is drawn from that edge, and the
		 * player moves freely within that grid, until they move far enough away from the
		 * edge, and the drawing is centered on the player again
		 */

		//Update iterators for small levels
		if (getMap().getWidth() < iteratorX) {
			iteratorX = getMap().getWidth();
		}
		if (getMap().getHeight() < iteratorY) {
			iteratorY = getMap().getHeight();
		}
		//Ensure new zero coordiantes cannot be invalid
		while (newZeroX + iteratorX > getMap().getWidth()) {
			newZeroX--;
		}

		if (newZeroX < 0) {
			newZeroX = 0;
		}

		while (newZeroY + iteratorY > getMap().getHeight()) {
			newZeroY--;
		}

		if (newZeroY < 0) {
			newZeroY = 0;
		}


		Cell currentCell;
		for (int i = 0; i < iteratorX; i++) {
			for (int j = 0; j < iteratorY; j++) {
				//The coords of cells being drawn are the zero points, plus the current iteration away from them
				int x = newZeroX + i;
				int y = newZeroY + j;
				//System.out.println("(" + x + "," + y + ")");
				//Draw cells
				if (getMap().isValidCoords(x,y)) {
					currentCell = getMap().getAt(x, y);
					gc.drawImage(currentCell.getSprite(), i * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
				}
				//Draw collectibles
				for (int k = 0; k < getMap().getCollectibles().size(); k++) {
					if (getMap().getCollectibles().get(k).getX() == x & getMap().getCollectibles().get(k).getY() == y & getMap().getCollectibles().get(k).isCollected() == false) {
						gc.drawImage(getMap().getCollectibles().get(k).getSprite(), x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
					}
				}

				//draw enemies and players last so they overlay other collectibles and the cells in the map

				//draw enemies
				for (int k = 0; k < getMap().getEnemies().size(); k++) {
					if (getMap().getEnemies().get(k).getX() == x & getMap().getEnemies().get(k).getY() == y) {
						 getMap().getEnemies().get(k).toString();
						gc.drawImage(getMap().getEnemies().get(k).getSprite(), i * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
					}
				}


				//draw the player
				if (getMap().getPlayer().getX() == x & getMap().getPlayer().getY() == y) {
					gc.drawImage(getMap().getPlayer().getSprite(), i * GRID_CELL_WIDTH, j * GRID_CELL_HEIGHT);
				}

				//draw gas
				for (int k = 0; k < getMap().getGas().size(); k++) {
					if (getMap().getGas().get(k).getX() == x & getMap().getGas().get(k).getY() == y) {
						gc.drawImage(getMap().getGas().get(k).getSprite(), x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
					}
				}
			}
		}
	}

	/**
	 * Deal with player victory, update score in profile, return to level select screen.
	 */
	public static void win() {
		int score = getMap().calculateScore();
		System.out.println("Score: " + score);
		int levelNumber = Character.getNumericValue(getMap().getParentLevelName().charAt(getMap().getParentLevelName().length() - 5));

		profile.completeLevel(levelNumber, score);


		IO.saveProfiles(allProfiles);
		selectLevel();
	}

	/**
	 * deal with player death, restart the level.
	 */
	public static void die() {
		try {
			playGame(getMap().getParentLevelName(), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void setMap(Map newMap) {
		map = newMap;
	}

	/**
	 * check if the player is in a position where the game can be saved from.
	 * @return True if player is on ground tile, false otherwise.
	 */
	public static boolean canSave() {
		//if player on ground cell
		if (getMap().getAt(getMap().getPlayer().getX(), getMap().getPlayer().getY()).getClass()
				== new Ground().getClass()) {
			return true;
		} else {
			System.out.println("Can only save on a ground tile");
			return false;
		}
	}

	/**
	 * Return the name of the map file being played.
	 * @return Parent level name.
	 */
	public static String getMapFileName() {
		//return parent name, so that the game can be restarted
		return getMap().getParentLevelName();
	}

	/**
	 * Gets the message of the day form the web server.
	 * @return The message of the day.
	 * @throws Exception If error connecting to the web server.
	 */
	public static String getMotd() throws Exception {

		//set up commcetion mdetails
		URL url = new URL("http://cswebcat.swan.ac.uk/puzzle");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		//get the puzzle
		BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String puzzle = rd.readLine();
		//System.out.println(puzzle);
		StringBuilder solution = new StringBuilder("");

		//solve the puzzle
		for(int i = 0; i < puzzle.length(); i++) {
			char current;
			if(i % 2 == 0) { // 1st,3rd,etc
				int value = (int) puzzle.charAt(i);
				if(value == 90) { // Z
					current = 'A';
				}else {
					current = (char) (value + 1);
				}
			} else {
				int value = (int) puzzle.charAt(i);
				if(value == 65) { // A
					current = 'Z';
				} else {
					current = (char) (value - 1);
				}
			}
			solution.insert(i, current);
		}
		String solved = solution.toString();
		//System.out.println(solved);
		//set up connection to get the message
		URL motdURL = new URL("http://cswebcat.swan.ac.uk/message?solution="+solved);
		HttpURLConnection motdcon = (HttpURLConnection) motdURL.openConnection();
		motdcon.setRequestMethod("GET");
		//get the message
		BufferedReader readMotd = new BufferedReader(new InputStreamReader(motdcon.getInputStream()));
		String motd = readMotd.readLine();
		return motd;
	}

	/**
	 * Launch the program.
	 * @param args Arguments to run the programm with.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Return the map that is being played.
	 * @return The current map beign played.
	 */
	public static Map getMap() {
		return map;
	}

	/**
	 * Save the map that is currently being played to a file.
	 * @throws IOException Error if the map is named incorrecty.
	 */
	public static void saveMapToFile() throws IOException {
		IO.saveMapToFile(map);
	}

	/**
	 * Return the profile that is currently selected.
	 * @return The profile that is currently used.
	 */
	public static Profile getProfile() {
		return profile;
	}

	/**
	 * Get the width of the canvas.
	 * @return The width of the canvas in px.
	 */
	public static int getCanvasWidth() {
		return CANVAS_WIDTH;
	}

	/**
	 * Get the height of the canvas.
	 * @return The height, of the canvas in px.
	 */
	public static int getCanvasHeight() {
		return CANVAS_HEIGHT;
	}

	/**
 	 * Set the profile.
 	 * @param newProfile A new profile.
 	 */
	public static void setProfile(Profile newProfile) {
		profile = newProfile;

	}

	/**
 	 * Set all the profiles.
 	 * @param newAllProfiles All the new profiles.
 	 */
	public static void setAllProfiles(ArrayList<Profile> newAllProfiles) {
		allProfiles = newAllProfiles;
	}

	/**
 	 * Get all the profiles.
 	 * @return All the profiles.
 	 */
	public static ArrayList<Profile> getProfiles() {
		return allProfiles;
	}
}
