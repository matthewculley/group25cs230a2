package menu;

import java.awt.FileDialog;
import java.awt.Frame;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import player.Profile;

/**
 * Gives the player an option to select profiles in game.
 * @author Tom
 * @version 1.5
 */

public class SelectProfileInGameController {
	@FXML ComboBox profiles;
	@FXML Button submitExistingUser;
	@FXML PasswordField passwordExistingUser;
	@FXML TextField userNameNewUser;
	@FXML PasswordField passwordNewUser;
	@FXML Button submitNewUser;
	@FXML Label errorMessage;
	@FXML ImageView avatar;
	@FXML Button deleteButton;
	@FXML Label deleteMessage;
	@FXML Label currentUserDisplay;
	@FXML Button avatarButton;
	@FXML Label avatarText;

	private ArrayList<Profile> allProfiles = new ArrayList<Profile>();
	private int currentUserIndex;
	private Profile currentUser;

	/**
	 * Generates variables required for levelSelect screen.
	 */
	@FXML
	public void initialize() {
		allProfiles = IO.getSavedProfiles();
		currentUser = Main.getProfile();

		for (Profile ele : allProfiles) {
			profiles.getItems().add(ele.getUserID());

			if (ele.getUserID().equals(currentUser.getUserID())) {
				currentUser = ele;
			}
		}

		System.out.println(allProfiles.indexOf(Main.getProfile()));
		currentUserDisplay.setText("Current Profile: " + currentUser.getUserID());
		avatar.setImage(new Image("Avatars/" + currentUser.getAvatar()));
	}

	@FXML
	private void back() {
		Main.mainMenu();
	}

	@FXML
	private void submitExistingUser() {
		for (Profile ele : allProfiles) {
			String userId = (String) profiles.getValue();
			String pass = passwordExistingUser.getText();
			if (ele.getUserID().equals(userId) & ele.getPassword().equals(pass)) {
				Main.setProfile(ele);
				Main.setAllProfiles(allProfiles);
				IO.saveProfiles(allProfiles);
				Main.mainMenu();
			} else {
				errorMessage.setText("Invalid username or password");
			}
		}
	}

	@FXML
	private void submitNewUser() {
		String userId = userNameNewUser.getText();
		String pass = passwordNewUser.getText();
		// if valid pass and username
		if (isUniqueUserId(userId) & isValidPass(pass) & isValidString(userId)) {
			// make new profile
			Profile newProfile = new Profile(userId, pass);

			// add to and save profiles arraylist
			allProfiles.add(newProfile);
			IO.saveProfiles(allProfiles);

			// set profile and allProfiles in Main
			Main.setAllProfiles(allProfiles);
			Main.setProfile(newProfile);

			Main.mainMenu();
		}
	}

	@FXML
	private void deleteCurrentUser() {
		profiles.getItems().remove(currentUser.getUserID());

		allProfiles.remove(currentUser);
		System.out.println(allProfiles.toString());
		IO.saveProfiles(allProfiles);
		Main.setAllProfiles(allProfiles);

		deleteMessage.setText("Profile deleted, select or create profile below");
		currentUserDisplay.setText("No profile selected");
		deleteButton.setDisable(true);
		avatarButton.setDisable(true);
	}

	/**
	 * Method that allows to browse for image files (jpg/jpeg/png) in file explorer
	 * and choose an avatar. Their choice is saved to their profile as the image's
	 * filepath.
	 */
	@FXML
	private void changeAvatar() {
		FileDialog fileChooser;

		fileChooser = new FileDialog(new Frame(), "Choose a file", FileDialog.LOAD);
		fileChooser.setDirectory("Avatars");

		fileChooser.setFile("*.jpg; *.jpeg; *.png");
		fileChooser.setVisible(true);

		String filePath = fileChooser.getFile();

		if (fileChooser.getFile() == null) {
			filePath = null;
			avatarText.setText("You cancelled the choice.");
		} else {
			avatarText.setText("You chose " + filePath);
			currentUser.setAvatar(filePath);
			System.out.println(currentUser.getAvatar());
			IO.saveProfiles(allProfiles);
			Main.setProfile(currentUser);
			avatar.setImage(new Image("Avatars/" + currentUser.getAvatar()));
		}

	}

	/**
	 * Check if inputted string is a valid input.
	 * @param input User input.
	 * @return True if string is valid, false otherwise.
	 */
	public boolean isValidString(String input) {
		if (input.equals("")) {
			System.out.println("Input is empty!");
			return false;
		} else if (containsWhitespace(input)) {
			System.out.println("Input can't contain any spaces");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Check that password is strong enough (to be valid).
	 * @param pass the inputted password.
	 * @return True if yes, false otherwise.
	 */
	public boolean isValidPass(String pass) {
		if ((pass.length() < 6) || containsOnlyLetters(pass) || !isValidString(pass)) {
			return false;
		} else {
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

	private boolean isUniqueUserId(String userID) {
		for (Profile profile : allProfiles) {
			if (profile.getUserID() == userID) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Creates a new profile instance with inputted data.
	 * @param userID   The inputted username.
	 * @param password The inputted password.
	 * @return A new profile object.
	 */
	public Profile createProfile(String userID, String password) {
		return new Profile(userID, password);
	}
}
