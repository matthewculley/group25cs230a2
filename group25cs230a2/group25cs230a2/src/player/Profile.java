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

	public Profile (String userID, String password,
					ArrayList<int[]> levelScores, String avatar) { //profile pic, map files...
		this.userID = userID;
		this.password = password;
		this.levelScores = levelScores;
		this.avatar = avatar;
		updateHighestCompletedLevel();
	}
/**
 * @param userID
 * @param password
 * Profile constructor with arguments only for userID and password,
 * sets default highestCompletedLevel as 0 and scores as an empty ArrayList.
 */
	public Profile (String userID, String password) {

		this (userID, password, new ArrayList<int[]>(), "default.png");
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
			int[] newScores = {0,0,0};
			this.levelScores.add (newScores);
		}
		try {
			addScore(completedLevel, achievedScore);
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

	public String scoresToString () {

		String returnString = "\nScores: ";

		for (int i = 0; i < levelScores.size(); i++) {
			
			returnString += "\nLevel: " + (i+1) + " ";
			
			for (int j = 0; j < levelScores.get(i).length; j++) {
				returnString += levelScores.get(i)[j] + " ";
			}
			
			
//			scoresString += "\n  Level " + (levelScores.get(i)) + ":";
			
		}
		return returnString;
	}

	public String saveFormat() {
		String scoreString = "";
		
		for (int i = 0; i < levelScores.size(); i++) {
			for (int j = 0; j < levelScores.get(i).length; j++) {
				scoreString += levelScores.get(i)[j] + " ";
			}	
		}
		return (this.userID + " " +
				this.password + " " +
				this.avatar + " " +
				scoreString + "\n");
	}


/**level scores can only be added in correct order, ie if adding score (1, 30) then
 * (3, 45) it will add score of 45 to level 2 rather than 3
*/
	private void addScore(int completedLevel, int newScore) {
		

		int[] levelScores = getScoresForLevel(completedLevel);
		
		Arrays.sort(levelScores);
		
		if (levelScores[0] < Math.abs(newScore)) {
			levelScores[0] = Math.abs(newScore);
		}
		
	}

	public int[] getScoresForLevel (int levelNumber) {
		return this.levelScores.get(levelNumber - 1);
	}
	



	public String getUserID () {
		return this.userID;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getHighestCompletedLevel() {
		return highestCompletedLevel;
	}

	private void updateHighestCompletedLevel() {
		this.highestCompletedLevel = levelScores.size();
	}

	public String getPassword() {
		return password;
	}
}