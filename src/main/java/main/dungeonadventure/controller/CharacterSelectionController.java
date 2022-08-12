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
    @FXML
    private TextField myHeroText;
    @FXML
    private RadioButton myThiefButton, myPriestessButton, myWarriorButton;
    @FXML
    private Label myHeroLabel, myNameLabel, myErrorMessage;
    @FXML
    private AnchorPane someRoot;
    @FXML
    private ImageView myThiefImage, myPriestessImage, myWarriorImage;

    private Stage myStage;
    private Scene myScene;
    private Parent myRoot;

    private Hero myHero;
    private String myHeroName;
    private HeroType myHeroType;
    private Media myMedia;
    private MediaPlayer myMediaPlayer;


    public void initialize() {
        playMedia("/assets/choosecharacter.mp3");
    }

    /**
     * When back button is hit, switches to welcome screen
     * @param theEvent - button click
     */
    @FXML
    private void switchToWelcomeScreen(final ActionEvent theEvent) {
        switchStageBuilder("welcome_screen.fxml");
    }

    //Deprecated by LS
//    /**
//     * When submit button is clicked and name is filled out, saves the name
//     * @param theEvent - button click
//     */
//    @FXML
//    private void heroNameSet(final ActionEvent theEvent) {
//        if (!Objects.equals(myHeroText.getText(), null)) {
//            String heroName = myHeroText.getText();
//            myHeroText.setText(heroName);
//            System.out.println(heroName);
//            myNameLabel.setTextFill(Color.WHITE);
//            myHeroName = heroName;
//        }
//    }

    /**
     * When a hero is selected from choices, saves the hero type
     * @param theEvent - radio button click
     */
    @FXML
    private void chooseHero(final ActionEvent theEvent) {
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
     * @param theEvent - button click
     */
    @FXML
    private void switchToDungeon(final ActionEvent theEvent) throws IOException {
        if (myHeroText.getText().length() == 0) {
            myErrorMessage.setText("Please give your character a name.");
            //myNameLabel.setTextFill(Color.RED);
        } else if (!myThiefButton.isSelected() && !myPriestessButton.isSelected()
                    && !myWarriorButton.isSelected()) {
            myErrorMessage.setText("Please select a character.");
            //myHeroLabel.setTextFill(Color.RED);
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
            Stage stage = (Stage) someRoot.getScene().getWindow();
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
    public void createHero(final HeroType theHeroType,
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

    private void playMedia(String theSongName) {
        myMedia = new Media(getClass().getResource(theSongName).toString());
        myMediaPlayer = new MediaPlayer(myMedia);
        myMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        myMediaPlayer.play();
    }



}