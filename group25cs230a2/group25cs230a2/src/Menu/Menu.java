package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import player.Leaderboard;
import player.Profile;

public class Menu {
	
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		
		menu.createProfile("bob", "pass123");
	}
	
	private static ArrayList<Profile> profiles = new ArrayList<Profile>();
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
	
	public void swapInterface() {
		//draw interface for profile swap
		//Profile newProfile = (selectedProfile);
		//swapProfile(currentProfile, newProfile);
	}
	
	public static Leaderboard createLeaderboard (int levelNumber, int leaderboardSize) {
		return new Leaderboard(profiles, levelNumber, leaderboardSize);
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
	
	public void deleteProfile() {
		profiles.remove(profiles.indexOf(currentProfile));
		currentProfile = null;
	}

	public static ArrayList<Profile> getProfiles() {
		return profiles;
	}
	
}