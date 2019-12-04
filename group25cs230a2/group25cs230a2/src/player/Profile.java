package player;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import javafx.scene.image.Image;
import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.awt.FileDialog;


public class Profile {

	public static void main(String[] args) throws FileNotFoundException {
		Profile myProfile = new Profile ("user123", "password123");
		myProfile.setValidUniqueUserID("bob");
		Profile myProfile2 = new Profile ("k", "password123");
		myProfile.completeLevel(1, 30);
		myProfile.completeLevel(1, 35);
		myProfile.completeLevel(1, 50);
		myProfile.completeLevel(1, 10);
		myProfile.completeLevel(2, 23);
		myProfile.completeLevel(2, 24);
		myProfile.completeLevel(4, 19);
		myProfile2.completeLevel(1, 23);
		myProfile2.completeLevel(1, 30);
		myProfile2.completeLevel(1, 20);
		myProfile2.completeLevel(1, 15);


		System.out.println (myProfile.toString());
		System.out.println (myProfile2.toString());
		Profile myProfile3 = new Profile ("bib", "password123");
		System.out.println (myProfile3.toString());
		Leaderboard leaderboard = createLeaderboard (1, 6);
		System.out.println(leaderboard.toString());
	}

	private static ArrayList<Profile> profiles = new ArrayList<>();
	private String userID;
	private String password;
	private ArrayList<int[]> levelScores = new ArrayList<>(); //index of arrayList = level number, int[] is personal best scores for that level
	private int highestCompletedLevel;
	private Image avatar;

	private static final String DEFAULT_AVATAR_FILEPATH = "src/avatars/default.jpg";

	public Profile (String userID, String password, String avatarFilePath, ArrayList<int[]> levelScores) throws FileNotFoundException { //profile pic, map files...
		setValidUniqueUserID (userID);
		setValidPassword (password);
		setHighestCompletedLevel();
		this.levelScores = levelScores;
		setAvatar (avatarFilePath);

		profiles.add (this);
	}
/**
 * @param userID
 * @param password
 * Profile constructor with arguments only for userID and password,
 * sets default highestCompletedLevel as 0 and scores as an empty ArrayList.
 * @throws FileNotFoundException
 */
	public Profile (String userID, String password) throws FileNotFoundException {
		this (userID, password, DEFAULT_AVATAR_FILEPATH, new ArrayList<int[]>());
	}

	//
	public Profile (String userID, String password, String avatarFilePath) throws FileNotFoundException {
		this (userID, password, avatarFilePath, new ArrayList<int[]>());
	}

	public Image getAvatar () {
		return avatar;
	}

	public void setAvatar (Image avatar) {
		this.avatar = avatar;
	}

	public void setAvatar (String avatarFilePath) throws FileNotFoundException {

		try {
			FileInputStream inputstream = new FileInputStream(avatarFilePath);
			Image avatar = new Image(inputstream);
			this.avatar = avatar;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("No such image file (in jpg, jpeg or png format) found at " + avatarFilePath);
		}
	}

	public void chooseAvatar() throws FileNotFoundException {

		String avatarFilePath = chooseAvatarImageFile();
		setAvatar(avatarFilePath);
	}

	private String chooseAvatarImageFile () {
	    FileDialog fileChooser;

		fileChooser = new FileDialog(new Frame() , "Choose a file", FileDialog.LOAD);
	    fileChooser.setDirectory("C:\\");

	    fileChooser.setFile("*.jpg; *.jpeg; *.png");
	    fileChooser.setVisible(true);

	    String filePath = fileChooser.getDirectory() + fileChooser.getFile();

	    if (fileChooser.getFile() == null) {
	    	filePath = null;
	        System.out.println("You cancelled the choice.");
	    }
	    else {
	        System.out.println("You chose " + filePath);
	    }
	    return filePath;
	}

	public void completeLevel (int completedLevel, int achievedScore) {

		if (completedLevel > this.highestCompletedLevel) {
			this.highestCompletedLevel = completedLevel;
			this.levelScores.add (new int[0]);
		}
		try {
			addScore (completedLevel, achievedScore);
		}
		catch (IndexOutOfBoundsException e) {
			this.levelScores.add (new int[0]);
			completeLevel (completedLevel, achievedScore);
		}
	}



	public String toString () {
		return "\nUser ID: " + this.userID +
				"\nPassword: " + this.password +
				"\nHighest completed level: " + this.highestCompletedLevel +
				scoresToString();
	}

	public static Leaderboard createLeaderboard (int levelNumber, int leaderboardSize) {
		return new Leaderboard(profiles, levelNumber, leaderboardSize);
	}

	private String scoresToString () {

		String scoresString = "\nScores:";

		for (int[] levelScores : this.levelScores) {
			scoresString = scoresString + "\n  Level " + (this.levelScores.indexOf(levelScores) + 1) + ":";
			for (int score : levelScores) {
				scoresString = scoresString + " " + score;
			}
		}
		return scoresString;
	}



/**level scores can only be added in correct order, ie if adding score (1, 30) then
 * (3, 45) it will add score of 45 to level 2 rather than 3
*/
	private void addScore (int completedLevel, int newScore) {

		int[] oldScores = getScoresForLevel (completedLevel);
		int[] newScores = new int[oldScores.length + 1];

		for (int i = 0; i < oldScores.length; i++) {
			newScores[i] = oldScores[i];
		}
		newScores[oldScores.length] = newScore;

		Arrays.sort(newScores);

		setScoresForLevel (newScores, completedLevel);
	}

	public int[] getScoresForLevel (int levelNumber) {
		return this.levelScores.get(levelNumber - 1);
	}

	private void setScoresForLevel (int[] scores, int levelNumber) {
		this.levelScores.set(levelNumber - 1, scores);
	}

	private void setValidUniqueUserID (String userID) {

		while (!isUniqueUserID (userID) || !isValidUserID (userID)) {

			if (!isUniqueUserID (userID)) {
				System.out.println ("Username \"" + userID + "\" is taken, please enter a new one:");
			}

			else if (!isValidUserID (userID) ) {
				System.out.println ("\"" + userID + "\" is not a valid username, please enter a new one:");
			}

			userID = getUserInput ();
		}

		this.userID = userID;
	}

	private String getUserInput () {

		Scanner in = new Scanner (System.in);
		String input = in.nextLine();
		in.close();

		return input;
	}

	private boolean isUniqueUserID (String userID) {

		for (Profile profile : profiles) {
			if (profile.getUserID() == userID) {
				return false;
			}
		}
		return true;
	}

	private void setValidPassword (String password) {

		while (!isValidPassword (password)) {
			password = getUserInput ();
		}

		this.password = password;
	}

	private boolean isValidPassword (String password) {

		if (password.length() < 6) {
			System.out.println("Password must be at least 6 characters in length.");
			return false;
		}
		if (containsOnlyLetters (password)) {
			System.out.println("Password must contain non-alpha character.");
			return false;
		}
		return true;
	}

	private boolean containsOnlyLetters (String str) {
		char[] chars = str.toCharArray();

		for (char c : chars) {
			if (!Character.isLetter(c)) {
				return false;
			}
		}
		return true;
	}

	private boolean isValidUserID (String userID) {
		if (userID == "") {
			return false;
		}
		return true;
	}

	public String getUserID () {
		return this.userID;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	public int getHighestCompletedLevel() {
		return highestCompletedLevel;
	}

	private void setHighestCompletedLevel() {
		this.highestCompletedLevel = this.levelScores.size();
	}

	private String getPassword() {
		return password;
	}
}
