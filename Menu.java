package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import player.Profile;

public class Menu {
	
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		
		menu.createProfile("bob", "pass123");
	}
	
	private static ArrayList<Profile> profiles = IO.getSavedProfiles();;
	private Profile currentProfile;
	private int levelSelected;
	
	public Menu() {
		this.currentProfile = null;
		this. levelSelected = 0;
		}
	
	public void playGame() {
		//if(currentProfile != null && levelSelected > 0)
		//call playGame class to begin game
	}
	
	public void quitGame() {
		System.exit(0);
	}
	
	public static Leaderboard createLeaderboard (int levelNumber, int leaderboardSize) {
		return new Leaderboard(profiles, levelNumber, leaderboardSize);
	}
	
	public boolean isStringValid(String input) {
		
		if(input.equals("")) {
			System.out.println("Input is empty!");
			return false;
		}else if(containsWhitespace(input)) {
			System.out.println("Input can't contain any spaces");
			return false;
		}else {
			System.out.println(input);
			return true;
		}
	}
	
	public boolean isPassValid(String pass) {
		if((pass.length() < 6) || containsOnlyLetters(pass)) {
			return false; 
		}else {
			return true;
		}
	}
	
	private boolean containsOnlyLetters(String str) {
		char[] chars = str.toCharArray();

		for (char c : chars) {
			if (!Character.isLetter(c)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean containsWhitespace(String str) {
		char[] chars = str.toCharArray();

		for (char c : chars) {
			if (Character.isWhitespace(c)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isUniqueUserID (String userID) {
		
		for (Profile profile : profiles) {
			if (profile.getUserID() == userID) {
				return false;
			}
		}
		return true;
	}

	public void createProfile(String userID, String password) {
		Profile profileToAdd = new Profile(userID, password);
		profiles.add(profileToAdd);
	}
	
	public void selectLevel(int level) {
		levelSelected = level;
	}
	
	public void selectProfile(int profileIndex) {
		currentProfile = profiles.get(profileIndex);
	}
	
	public void deleteProfile(Profile profile) {
		profiles.remove(profiles.indexOf(currentProfile));
		currentProfile = null;
	}

	public static ArrayList<Profile> getProfiles() {
		return profiles;
	}
	
	public int getProfileIndex(Profile profile) {
		return profiles.indexOf(profile);
	}
	
}