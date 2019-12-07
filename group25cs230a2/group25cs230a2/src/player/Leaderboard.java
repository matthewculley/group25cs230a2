package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import menu.*;

import player.Profile;

import java.lang.Integer;


public class Leaderboard {

	private String[][] leaderboard;
	private int levelNumber;
	private int numberOfEmptySpots;
	private Profile first;
	private Profile second;
	private Profile third;
	
	public Leaderboard (int levelNumber) {
//		

	}

	public void update () {
		Leaderboard updatedLeaderboard = new Leaderboard (Main.getProfiles(), this.getLevelNumber(), this.getLength());
		this.leaderboard = updatedLeaderboard.leaderboard;
	}



	private Leaderboard (ArrayList<Profile> profiles, int levelNumber, int leaderboardLength) {

		
		this.levelNumber = levelNumber;
		this.leaderboardLength = leaderboardLength;

		//if profiles is empty
		if (!(profiles.size() == 0)) {
			
			//
			String[][] allScoreEntries = new String [0] [2] ;

			for (Profile profile : profiles) {
				System.out.println(profile.getUserID() + profile.getHighestCompletedLevel());
				if (profile.getHighestCompletedLevel() >= levelNumber) {
					int [] levelScores = profile.getScoresForLevel(levelNumber);
					for (int levelScore : levelScores) {
						String[] leaderboardEntry = new String[2];
						leaderboardEntry[0] = profile.getUserID();
						leaderboardEntry[1] = String.valueOf(levelScore);
						allScoreEntries = append (allScoreEntries, leaderboardEntry);
					}
				}
			}
			Arrays.sort(allScoreEntries, new Comparator<String[]>() {
				@Override
				public int compare (String[] a1, String[] a2) {
					int score1 = Integer.parseInt(a1[1]);
					int score2 = Integer.parseInt(a2[1]);

					if (score1 > score2) {
						return 1;
					}
					if (score1 == score2) {
						return 0;
					}
					return -1;
				}
			});

			String [][] leaderboard = new String [leaderboardLength] [2];

			this.numberOfEmptySpots = leaderboardLength;

			for (int i = 0; i < leaderboardLength && i < allScoreEntries.length; i++) {
				leaderboard[i] = allScoreEntries[i];
				if (this.numberOfEmptySpots >= 0) {
					this.numberOfEmptySpots --;
				};
			}

			this.leaderboard = leaderboard;
			System.out.println(this.getLength());
		}
	}

	private static String[][] append (String[][] arr1, String[] arr2) {

		String[][] newArr = new String[arr1.length + 1][arr2.length];

		for (int i = 0; i < arr1.length; i++) {
			newArr[i] = arr1[i];
		}
		newArr[arr1.length] = arr2;

		return newArr;
	}

	int getLevelNumber() {
		return levelNumber;
	}

	void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	int getLength() {
		return this.leaderboardLength;
	}

	void setLength(int length) {
		this.leaderboardLength = length;
	}
	
	public String toString () {
		String leaderboardString = "LEADERBOARD FOR LVL " + levelNumber + ":\nUser		Score";
		for (int i = 0; i < (this.getLength() - this.numberOfEmptySpots); i++) {
			leaderboardString = leaderboardString + "\n" + this.leaderboard[i][0] + "		" + this.leaderboard[i][1];
		}
		return leaderboardString;
	}
}