package menu;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import player.Profile;

public class SelectProfileController {
	@FXML ComboBox profiles;
	@FXML Button submitExistingUser;
	@FXML PasswordField passwordExistingUser;
	@FXML TextField userNameNewUser;
	@FXML PasswordField passwordNewUser;
	@FXML Button submitNewUser;
	@FXML Label errorMessage;
	private ArrayList<Profile> allProfiles = new ArrayList<Profile>();
	

	@FXML public void initialize() {
		allProfiles.add(new Profile("matt", "password1"));
		allProfiles.add(new Profile("james", "password1"));
		allProfiles.add(new Profile("culley", "password1"));
		allProfiles.add(new Profile("mattmatt", "password1"));
		allProfiles.add(new Profile("jamesjames", "password1"));
		allProfiles.add(new Profile("culleyculley", "password1"));
		
		
		
		for (Profile ele : allProfiles) {
			profiles.getItems().add(ele.getUserID());
		}
		
	}
	
	
	@FXML private void submitExistingUser() {
		try {
			String userId = (String) profiles.getValue();
			String pass = passwordExistingUser.getText();
			System.out.println(userId + " " + pass);
		} catch (NullPointerException e) {
			errorMessage.setText("One or more fields are empty.");
		}
		
		
		//if userID and pass correct
			//Main.mainMenu();
	}
	
	@FXML private void submitNewUser() {
		String userId = userNameNewUser.getText();
		String pass = passwordNewUser.getText();
		System.out.println(userId + " " + pass);
		
		if (userId.equals("") || pass.equals("")) {
			errorMessage.setText("One or more fields are empty.");
		}
		//if valid pass and username
			//create profile
			//add to profiles
			//save all profiles
	}

}
