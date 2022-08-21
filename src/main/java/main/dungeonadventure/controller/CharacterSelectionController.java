package main.dungeonadventure.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.dungeonadventure.model.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Controller for start_game screen used to choose and name a character
 * @Author Micaela Nomakchteinsky
 * @Author Luke Smith
 * @version 8-2022
 */
public class CharacterSelectionController {

    /** Text Field control for user typing in their hero name */
    @FXML
    private TextField myHeroText;
    /** RadioButton controls for selecting heroes */
    @FXML
    private RadioButton myThiefButton, myPriestessButton, myWarriorButton;
    /** Label displaying error when hero name is not entered or hero is not chosen */
    @FXML
    private Label myErrorMessage;
    /** First Anchor Pane in hierarchy is the parent root */
    @FXML
    private AnchorPane myRoot;
    /**
     * Image View Controls
     * myThiefImage - holds the image for the Thief
     * myPriestessImage - holds the image for the Priestess
     * myWarriorImage - holds the image for the Warrior
     */
    @FXML
    private ImageView myThiefImage, myPriestessImage, myWarriorImage;

    /** Game hero object */
    private Hero myHero;
    /** Hero Type selected used to specify hero */
    private HeroType myHeroType;
    /** Song Media for character selection */
    private Media myMedia;
    /** Used to play Media */
    private MediaPlayer myMediaPlayer;


    /**
     * Initializes music when entering screen
     */
    public void initialize() {
        playMedia("/assets/choosecharacter.mp3");
    }


    /**
     * When back button is hit, switches to welcome screen
     */
    @FXML
    private void switchToWelcomeScreen() {
        switchStageBuilder("welcome_screen.fxml");
    }


    /**
     * When a hero is selected from choices, saves the hero type and styles the button selected
     */
    @FXML
    private void chooseHero() {
        String thiefSelected = "/assets/THIEF_HIGHLIGHTED.png";
        String thiefUnselected = "/assets/THIEF.png";
        String priestessSelected = "/assets/PRIESTESS_HIGHLIGHTED.png";
        String priestessUnselected = "/assets/PRIESTESS.png";
        String warriorSelected = "/assets/WARRIOR_HIGHLIGHTED.png";
        String warriorUnselected = "/assets/WARRIOR.png";

        if (myThiefButton.isSelected()) {
            System.out.println("DEBUG - Thief is chosen");
            myHeroType = HeroType.THIEF;
            myPriestessImage.setImage(new Image(getClass().getResourceAsStream(priestessUnselected)));
            myWarriorImage.setImage(new Image(getClass().getResourceAsStream(warriorUnselected)));
            myThiefImage.setImage(new Image(getClass().getResourceAsStream(thiefSelected)));

        } else if (myPriestessButton.isSelected()) {
            System.out.println("DEBUG - Priestess is chosen");
            myHeroType = HeroType.PRIESTESS;
            myPriestessImage.setImage(new Image(getClass().getResourceAsStream(priestessSelected)));
            myWarriorImage.setImage(new Image(getClass().getResourceAsStream(warriorUnselected)));
            myThiefImage.setImage(new Image(getClass().getResourceAsStream(thiefUnselected)));


        } else if (myWarriorButton.isSelected()) {
            System.out.println("DEBUG - Warrior is chosen");
            myHeroType = HeroType.WARRIOR;
            myPriestessImage.setImage(new Image(getClass().getResourceAsStream(priestessUnselected)));
            myWarriorImage.setImage(new Image(getClass().getResourceAsStream(warriorSelected)));
            myThiefImage.setImage(new Image(getClass().getResourceAsStream(thiefUnselected)));


        }
    }


    /**
     * When name and hero are chosen and the next button is clicked will go to the
     * first room in the dungeon
     */
    @FXML
    private void switchToDungeon() {
        if (myHeroText.getText().length() == 0) {
            myErrorMessage.setText("Please give your character a name.");
        } else if (!myThiefButton.isSelected() && !myPriestessButton.isSelected()
                    && !myWarriorButton.isSelected()) {
            myErrorMessage.setText("Please select a character.");
        } else {
            DungeonAdventureGame.buildNewDungeon();
            createHero(myHeroType, myHeroText.getText());
            switchStageBuilder("dungeon.fxml");
        }
    }


    /**
     * Switches screens back to the welcome screen or the first room in the dungeon
     * @param theFxmlName - fxml file name for the screen to switch to
     */
    @FXML
    private void switchStageBuilder(final String theFxmlName) {
        try {
            myMediaPlayer.stop();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(theFxmlName));
            Parent root = loader.load();
            System.out.println("DEBUG - dungeon.fxml was loaded.");
            Stage stage = (Stage) myRoot.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Creates the hero for the game given the hero type and hero name
     * @param theHeroType - type of hero user chooses
     * @param theHeroName - name of hero user chooses
     */
    private void createHero(final HeroType theHeroType,
                           final String theHeroName) {
        if (theHeroType == HeroType.WARRIOR) {
            myHero = new Warrior(theHeroName);
        } else if (theHeroType == HeroType.THIEF) {
            myHero = new Thief(theHeroName);
        } else if (theHeroType == HeroType.PRIESTESS) {
            myHero = new Priestess(theHeroName);
        } else {
            throw new IllegalArgumentException("Invalid HeroType");
        }
        DungeonAdventureGame.getDungeon().setHero(myHero);
    }


    /**
     * Plays specified song
     * @param theSongName - song to be played
     */
    private void playMedia(final String theSongName) {
        myMedia = new Media(getClass().getResource(theSongName).toString());
        myMediaPlayer = new MediaPlayer(myMedia);
        myMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        myMediaPlayer.play();
    }

}