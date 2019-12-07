package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import player.Profile;

public class Menu {
	
	private Profile currentProfile;
	private int levelSelected;
	private Leaderboard lb;
		
	public void createLeaderboard() {
		this.lb = new Leaderboard(levelSelected, 6);
	}
	
	public void updateLeaderboard() {
		this.lb.update();
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
		
		for (Profile profile : Main.profiles) {
			if (profile.getUserID() == userID) {
				return false;
			}
		}
		return true;
	}

	public Profile createProfile(String userID, String password) {
		return new Profile(userID, password);
	}
	
	public void selectLevel(int level) {
		levelSelected = level;
	}
	
	public void selectProfile(Profile p) {
		this.currentProfile = p;
	}
	
	public void deleteCurrentProfile(Profile profile) {
		Menu.profiles.remove(currentProfile);
		currentProfile = null;
	}
	
}