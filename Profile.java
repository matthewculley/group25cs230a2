package player;
import java.awt.FileDialog;
import java.awt.Frame;
import java.util.ArrayList;

import java.util.Arrays;

public class Profile {

	String userID;
	String password;
	ArrayList<int[]> levelScores = new ArrayList<>(); //index of arrayList = level number, int[] is personal best scores for that level
	int highestCompletedLevel;
	String avatar;

	public Profile (String userID, String password, ArrayList<int[]> levelScores, String avatar) { //profile pic, map files...
		this.userID = userID;
		this.password = password;
		updateHighestCompletedLevel();
		this.levelScores = levelScores;
		this.avatar = avatar;

	}
/**
 * @param userID
 * @param password
 * Profile constructor with arguments only for userID and password,
 * sets default highestCompletedLevel as 0 and scores as an empty ArrayList.
 */
	public Profile (String userID, String password) {

		this (userID, password, new ArrayList<int[]>(), null);
	}



	public void chooseAvatarImageFile () {
	    FileDialog fileChooser;

		fileChooser = new FileDialog(new Frame() , "Choose a file", FileDialog.LOAD);
	    fileChooser.setDirectory("src");

	    fileChooser.setFile("*.jpg; *.jpeg; *.png");
	    fileChooser.setVisible(true);

	    String filePath = fileChooser.getFile();

	    if (fileChooser.getFile() == null) {
	    	filePath = null;
	        System.out.println("You cancelled the choice.");
	    }
	    else {
	        System.out.println("You chose " + filePath);
	    }
	    System.out.println(filePath);
	    this.avatar = filePath;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
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

	public String getUserID () {
		return this.userID;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	int getHighestCompletedLevel() {
		return highestCompletedLevel;
	}

	private void updateHighestCompletedLevel() {
		this.highestCompletedLevel = levelScores.size();
	}

	private String getPassword() {
		return password;
	}
}