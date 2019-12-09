package menu;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import player.Profile;

/**
 * A class that constructs the leaderboard.
 * @author Matt
 * @version 1.5
 */

public class LeaderboardController {
	@FXML private BorderPane bp;
	@FXML private Button nextLevel;
	@FXML private Button previousLevel;
	@FXML private Button back;
	@FXML private Label level;
	@FXML private Label first;
	@FXML private Label second;
	@FXML private Label third;
	@FXML private Label player;
	@FXML private ImageView imageView;

	private int currentLevel = 1;
	private int numberOfLevels = 8;
	
	/**
 	 * Shows top 3 result on the leaderboard.
	 */
	@FXML
	public void initialize() {
		ArrayList<Profile> podium = getHighScores(IO.getSavedProfiles());
		level.setText("Level " + currentLevel);
		try {
			first.setText(podium.get(0).getScoresForLevel(currentLevel)[0] + " : " + podium.get(0).getUserID());
		} catch (NullPointerException e) {
			first.setText("---");
		}
		
		try {
			second.setText(podium.get(1).getScoresForLevel(currentLevel)[0] + " : " + podium.get(1).getUserID());
		} catch (NullPointerException e) {
			second.setText("---");
		}
		
		try {
			third.setText(podium.get(2).getScoresForLevel(currentLevel)[0] + " : " + podium.get(2).getUserID());
		} catch (NullPointerException e) {
			third.setText("---");
		}
		
		
		try {
			 imageView.setImage(new Image("level" + (currentLevel) + ".png"));
		} catch (IllegalArgumentException e) {}
		
	}  
	/**
	* Method that computes the top 3 scores achieved for a level out of all profiles.
	* @param leaderboardProfiles - list containing every profile
	* @return podium - the best (up to) 3 scores achieved in a level out of all profiles
	*/
	private ArrayList<Profile> getHighScores(ArrayList<Profile> leaderboardProfiles) {
		
		System.out.println("\n\n" + leaderboardProfiles.toString());
		System.out.println("\n\n" + Main.getProfiles());
		ArrayList<Profile> podium = new ArrayList<Profile>();
		 
		Profile pFirst = null;
		Profile pSecond = null;
		Profile pThird = null; 
		
		//get lowest in leaderboardProfiles
		int lowest = -1;
		for (int i = 0; i < leaderboardProfiles.size(); i++) {
			if (leaderboardProfiles.get(i).getHighestCompletedLevel() >= currentLevel) {
				int pHighscore = leaderboardProfiles.get(i).getScoresForLevel(currentLevel)[0];
				if (pHighscore < lowest || lowest == -1) {
					lowest = pHighscore;
					pFirst = leaderboardProfiles.get(i);	
				}
			}
		}
		podium.add(pFirst);
		leaderboardProfiles.remove(pFirst);
		
		//get 2nd lowest in leaderboardProfiles
		int secondLowest = -1;
		for (int i = 0; i < leaderboardProfiles.size(); i++) {
			if (leaderboardProfiles.get(i).getHighestCompletedLevel() >= currentLevel) {
				int pHighscore = leaderboardProfiles.get(i).getScoresForLevel(currentLevel)[0];
				if (pHighscore < secondLowest || secondLowest == -1) {
					secondLowest = pHighscore;
					pSecond = leaderboardProfiles.get(i);	
				}
			}
		}
		podium.add(pSecond);
		leaderboardProfiles.remove(pSecond);
		
		//get 3rd lowest in leaderboardProfiles
		int thirdLowest = - 1;
		for (int i = 0; i < leaderboardProfiles.size(); i++) {
			if (leaderboardProfiles.get(i).getHighestCompletedLevel() >= currentLevel) {
				int pHighscore = leaderboardProfiles.get(i).getScoresForLevel(currentLevel)[0];
				if (pHighscore < thirdLowest || thirdLowest == -1) {
					thirdLowest = pHighscore;
					pThird = leaderboardProfiles.get(i);	
				}
			}
		}
		podium.add(pThird);
		leaderboardProfiles.remove(pThird);

		return podium;
	}
	
	@FXML
	private void nextLevel() {
		if (currentLevel < numberOfLevels) {
			currentLevel++;
			initialize();
		}
	}
	
	@FXML
	private void previousLevel() {
		if (currentLevel > 1) {
			currentLevel--;
			initialize();
		}
	}
	
	@FXML
	private void back() {
		Main.mainMenu();
	}	
}
