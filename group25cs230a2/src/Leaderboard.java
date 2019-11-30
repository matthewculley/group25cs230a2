import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.lang.Integer;

/**TODO
 * implement leaderboard as a priority queue
 */
public class Leaderboard {
	
	String[][] leaderboard;
	
	
	public String toString () {
		String leaderboardString = "Leaderboard\nUser		Score";
		for (int i = 0; i < this.leaderboard.length; i++) {
			leaderboardString = leaderboardString + "\n" + this.leaderboard[i][0] + "		" + this.leaderboard[i][1];
		}
		return leaderboardString;
	}
	
	public Leaderboard (ArrayList<Profile> profiles, int levelNumber, int leaderboardLength) {
		
		String[][] allScoreEntries = new String [0] [2] ;

		for (Profile profile : profiles) {
			int [] levelScores = profile.getScoresForLevel(levelNumber);
			for (int levelScore : levelScores) {
				String[] leaderboardEntry = new String[2];
				leaderboardEntry[0] = profile.getUserID();
				leaderboardEntry[1] = String.valueOf(levelScore);
				allScoreEntries = append (allScoreEntries, leaderboardEntry);
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
		
		for (int i = 0; i < leaderboardLength; i++) {
			leaderboard[i] = allScoreEntries[i];
		}

		this.leaderboard = leaderboard;
	}
	
	private static String[][] append (String[][] arr1, String[] arr2) {
		
		String[][] newArr = new String[arr1.length + 1][arr2.length];
		
		for (int i = 0; i < arr1.length; i++) {
			newArr[i] = arr1[i];
		}
		newArr[arr1.length] = arr2;
		
		return newArr;
	}
	

}