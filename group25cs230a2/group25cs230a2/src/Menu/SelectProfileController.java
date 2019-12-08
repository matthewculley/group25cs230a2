package menu;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import player.Profile;

/**
 * Class to handle the initial profile selection at the beginning of the game
 * 
 * @author Tom
 * @version 1.5
 */

public class SelectProfileController {
	@FXML ComboBox profiles;
	@FXML Button submitExistingUser;
	@FXML PasswordField passwordExistingUser;
	@FXML TextField userNameNewUser;
	@FXML PasswordField passwordNewUser;
	@FXML Button submitNewUser;
	@FXML Label errorMessage;
	@FXML ImageView avatarImage;
	private ArrayList<Profile> allProfiles = new ArrayList<Profile>();

	/**
	 * initialize gets the variables needed for the scren ready for use
	 */
	@FXML
	public void initialize() {
		allProfiles = IO.getSavedProfiles();

		for (Profile ele : allProfiles) {
			profiles.getItems().add(ele.getUserID());
		}
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
	private void updateAvatarImage() {
		String userId = (String) profiles.getValue();
		String avatarImageName = "";
		for (Profile ele : allProfiles) {
			if (userId == ele.getUserID()) {
				avatarImageName = ele.getAvatar();
			}
		}
		if (avatarImageName != "") {
			avatarImage.setImage(new Image("Avatars/" + avatarImageName));
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
			System.out.println(allProfiles.toString());

			// set profile and allProfiles in Main
			Main.setAllProfiles(allProfiles);
			Main.setProfile(newProfile);

			Main.mainMenu();
		} else {
			errorMessage.setText("Invalid username or password");
		}
	}

	/**
	 * Check if inputted string is a valid input
	 * 
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
	 * 
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
	 * 
	 * @param userID   The inputted username.
	 * @param password The inputted password.
	 * @return A new profile object.
	 */
	public Profile createProfile(String userID, String password) {
		return new Profile(userID, password);
	}

}
