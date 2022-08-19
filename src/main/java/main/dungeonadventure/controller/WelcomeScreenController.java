package main.dungeonadventure.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

/**
 * Controller class for the welcome screen
 * @author Micaela Nomakchteinsky
 * @author Luke Smith
 * @version 8-2022
 */
public class WelcomeScreenController {

    /** First Anchor Pane in hierarchy is the parent root */
    @FXML
    private Parent myRoot;
    /** Displays about section*/
    @FXML
    private AnchorPane myAboutPane;
    /** Used to play Media */
    private MediaPlayer myMediaPlayer;


    /**
     * Starts a new game and switches to character selection screen
     */
    @FXML
    private void switchToCharacterSelection() {
        switchStageBuilder("character_selection.fxml");
    }


    /**
     * Gives the user a brief description about the game
     * @param theEvent - menuItem click
     */
    @FXML
    private void about(final ActionEvent theEvent) {
        myAboutPane.setVisible(true);
        System.out.println("this is the about section");
    }


    /**
     * Exits the about section
     */
    @FXML
    private void exitAbout() {
        myAboutPane.setVisible(false);
    }


    /**
     * Starts a tutorial for the game
     */
    @FXML
    private void startTutorial() {
        switchStageBuilder("tutorial_screen.fxml");
        System.out.println("you clicked start a tutorial");
    }


    /**
     * Switches screens back to the start_game screen, tutorial, or about section
     * @param theFxmlName - fxml file name for the screen to switch to
     */
    @FXML
    private void switchStageBuilder(final String theFxmlName) {
        try {
            myMediaPlayer.stop();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(theFxmlName));
            System.out.println("DEBUG - character_selection.fxml was loaded.");
            Stage stage = (Stage) myRoot.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Starts a game that was saved previously.
     */
    @FXML
    private void loadGame() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ser files (*.ser)", "*.ser");

        File recordsDir = new File(System.getProperty("user.home"), ".DungeonAdventure/saves");
        if (! recordsDir.exists()) {
            recordsDir.mkdirs();
        }
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(recordsDir);

        //Show save file dialog
        Stage stage = new Stage();
        File saveLocation = fileChooser.showOpenDialog(stage);

        if (saveLocation != null) {
            boolean gameLoaded = DungeonAdventureGame.loadGame(saveLocation);
            if (gameLoaded) {

                switchStageBuilder("dungeon.fxml");
            }
        }

    }


    /**
     * Initializes music when screen is displayed
     */
    public void initialize() {
        playMusic();
    }


    /**
     * Plays title theme song
     */
    private void playMusic() {
        Media media = new Media(getClass().getResource("/assets/titletheme.mp3").toString());
        myMediaPlayer = new MediaPlayer(media);
        myMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        myMediaPlayer.play();
    }

}
