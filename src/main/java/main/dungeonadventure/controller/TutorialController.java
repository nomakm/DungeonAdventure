package main.dungeonadventure.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.util.LinkedList;

/**
 * Controller class for the tutorial screen
 * @author Micaela Nomakchteinsky
 * @version 8-2022
 */
public class TutorialController {

    /** Image holding specific tutorial step */
    @FXML
    private ImageView myTutImage;
    /** Label displaying text of specific tutorial step */
    @FXML
    private Label myTutText;
    /** Button control for back and next*/
    @FXML
    private Button myBackButton, myNextButton;

    /** Stores all images used in tutorial in step order */
    private LinkedList<Image> images = new LinkedList<>();
    /** Stores all text used in tutorial in step order */
    private LinkedList<String> imageText = new LinkedList<>();
    /** Integer used to keep track of step user is in tutorial */
    private int myStep = 0;
    /** Used to play Media */
    private MediaPlayer myMediaPlayer;


    /** Stores all tutorial Images and text. Plays music for tutorial screen */
    public void initialize() {
        images.add(new Image(getClass().getResourceAsStream(
                        "/assets/tutorial_images/select_char_image.png")));
        images.add(new Image(getClass().getResourceAsStream(
                        "/assets/tutorial_images/dungeon_image.png")));
        images.add(new Image(getClass().getResourceAsStream(
                        "/assets/tutorial_images/fight_image.png")));
        images.add(new Image(getClass().getResourceAsStream(
                "/assets/tutorial_images/inventory_image.png")));
        images.add(new Image(getClass().getResourceAsStream(
                "/assets/tutorial_images/vision_image.png")));
        images.add(new Image(getClass().getResourceAsStream(
                "/assets/tutorial_images/win_image.png")));

        imageText.add("When starting a game, give your hero a name and select from the " +
                "3 hero types: The Thief is quick and may attack twice at once! " +
                "The Priestess is a healer and heals with every attack! " +
                "The Warrior is strong and may hit the monster with a super strong attack!");
        imageText.add("Enter the dungeon and search for 4 Pillars. " +
                "Come across many different items: " +
                "a blue vision potion will help you see neighboring rooms," +
                " a red health potion will give you more HP, " +
                "a pit will do instant damage to you, a bomb will help you attack the monster," +
                " a monster will need to be attacked before continuing on, " +
                "and a pillar will need to be picked up to exit the maze");
        imageText.add("Clicking the Monster will launch an attack " +
                "between your hero and the monster! " +
                "Press attack to fire an attack on the monster!" +
                " Use a Health potion to increase your HP when you're low. " +
                "Use a bomb to blast the monster, immediately dealing 20 HP!");
        imageText.add("When picking up items in a room, " +
                "you can open your inventory and see the items you've collected! " +
                "Make sure you have 4 Pillars before exiting the dungeon. " +
                "You can use the Health Potions and Vision Potions anytime you are in a room");
        imageText.add("If you use the Vision Potion you acquire the ability to see " +
                "8 neighboring rooms surrounding you. " +
                "Use this to avoid entering rooms with pits and monsters," +
                " and to see where the pillars are!");
        imageText.add("Once you have found 4 pillars and the exit room you have won the game!!" +
                " Good luck on your next adventure!");

        playMusic();
    }


    /** Increments step in tutorial based on if user presses back or next button.
     * Sets tutorial image and text for step */
    @FXML
    private void nextStep(ActionEvent theEvent) {
        Button button = (Button)theEvent.getSource();
        if (button.getText().equals("Back")) {
            myStep--;
        } else {
            myStep++;
        }
        if (myStep <= 0) {
            myStep = 0;
            myBackButton.setVisible(false);
        } else {
            myBackButton.setVisible(true);
        }
        if (myStep != images.size() - 1) {
            myNextButton.setVisible(true);
        } else {
            myNextButton.setVisible(false);
        }
        myTutImage.setImage(images.get(myStep));
        myTutText.setText(imageText.get(myStep));
    }


    /** Switches back to welcome screen when user wants to end the tutorial
     * @param theEvent - Button clicked
     */
    @FXML
    private void switchToWelcomeScreen(ActionEvent theEvent) {
        try {
            myMediaPlayer.stop();
            Parent root = FXMLLoader.load(
                    getClass().getClassLoader().getResource("welcome_screen.fxml"));
            System.out.println("DEBUG - welcome_screen.fxml was loaded.");
            Stage stage = (Stage) ((Node)theEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Plays dungeon song
     */
    private void playMusic() {
        Media myMedia = new Media(getClass().getResource("/assets/dungeon.mp3").toString());
        myMediaPlayer = new MediaPlayer(myMedia);
        myMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        myMediaPlayer.play();
    }
}
