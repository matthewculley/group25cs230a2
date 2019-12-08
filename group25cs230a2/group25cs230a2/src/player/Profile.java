package player;
import java.awt.FileDialog;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Profile is an object that contains all the information required for a single user
 * @author Tom McKee
 * */

public class Profile {

  private String userID;    //login, username and unique identifier for each profile.
  private String password;  //password used by user to login to their profile.
  private ArrayList<int[]> levelScores = new ArrayList<>(); //stores user's achieved scores for each level.
  private int highestCompletedLevel;  //the level number (identifier) of the a user's highest completed level, used to track the user's progression throught the game
  private String avatar;    //the file path & name of the png/jpg/jpeg file used for a user's profile picture.


  /**
   * Constructor method to create an instance of Profile.
   * @param userID
   * @param password
   * @param levelScores
   * @param avatar
   */
  public Profile(String userID, String password,
          ArrayList<int[]> levelScores, String avatar) { //profile pic, map files...
    this.userID = userID;
    this.password = password;
    this.levelScores = levelScores;
    this.avatar = avatar;
    updateHighestCompletedLevel();
  }



  /**
   * Constructor for profile with only userID and password given.
   * @param userID
   * @param password
   */
  public Profile(String userID, String password) {
    this(userID, password, new ArrayList<int[]>(), null);
  }


  /**
   * Method that allows to browse for image files (jpg/jpeg/png) in file explorer
   * and choose an avatar. Their choice is saved to their profile as the image's filepath
   */
  public void chooseAvatarImageFile() {
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

  /**
   * Method must be called whenever a user completes a level.
   * It adds the new score to the user's levelScores for the given level
   * if it is one of their best 3 scores for that level. It also marks their
   * progress if it is their first level completion.
   * @param completedLevel - the level number (identifier) of the completed level
   * @param achievedScore  - the score (number of moves made) in a given level
   * */
  public void completeLevel(int completedLevel, int achievedScore) {

    if(completedLevel > this.highestCompletedLevel) {
      this.highestCompletedLevel = completedLevel;
      int[] newScores = {0,0,0};
      this.levelScores.add (newScores);
    }
    try {
      addScore(completedLevel, achievedScore);
    }
    catch(IndexOutOfBoundsException e) {
      this.levelScores.add (new int[0]);
      completeLevel (completedLevel, achievedScore);
    }
  }

  /**Method for creating a string that contains all the information about
   * an instance of a Profile, so that it can be saved/loaded to/from a .txt file.
   * @return String containing all the information for a given profile.
   */
  public String saveFormat() {
    String scoreString = "";

    for(int i = 0; i < levelScores.size(); i++) {
      for (int j = 0; j < levelScores.get(i).length; j++) {
        scoreString += levelScores.get(i)[j] + " ";
      }
    }
    return(this.userID + " " +
        this.password + " " +
        this.avatar + " " +
        scoreString + "\n");
  }


  /**
   * Method to add a new score for a given level to levelScores.
   */
  private void addScore(int completedLevel, int newScore) {


    int[] levelScores = getScoresForLevel(completedLevel);

    Arrays.sort(levelScores);

    if(levelScores[0] < Math.abs(newScore)) {
      levelScores[0] = Math.abs(newScore);
    }

  }

  /**
   * Method to get top 3 scores achieved by the user for a given level.
   * @param levelNumber - identifies the level
   * @return levelScores - the best (up to) 3 scores achieved for given level on this profile*/
  public int[] getScoresForLevel(int levelNumber) {
    return this.levelScores.get(levelNumber - 1);
  }

  /**
   * Method to get the user's high score for a given level.
   * @param levelNumber - identifies the level
   * @return highScore - lowest (best) score achieved for given level
   * */
  public int getHighScoreForLevel(int levelNumber) {
    int highScore = getScoresForLevel(levelNumber)[0];
    return highScore;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar){
    this.avatar = avatar;
  }

  public String getUserID() {
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

  private String getPassword() {
    return password;
  }

  public String toString() {
    return "\nUser ID: " + this.userID +
        "\nPassword: " + this.password +
        "\nHighest completed level: " + this.highestCompletedLevel +
        scoresToString();
  }

    private String scoresToString() {

    String returnString = "\nScores: ";

    for(int i = 0; i < levelScores.size(); i++) {

      returnString += "\nLevel: " + (i+1) + " ";

      for(int j = 0; j < levelScores.get(i).length; j++) {
        returnString += levelScores.get(i)[j] + " ";
      }

    }
    return returnString;
  }
}
