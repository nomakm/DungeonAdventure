package main.dungeonadventure.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

/**
 * Controller class for the welcome screen
 * @Author Micaela Nomakchteinsky
 * @version 8-2022
 */
public class WelcomeScreenController {
    @FXML
    private Parent someRoot;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button musicButton;
    private Media myMedia;
    private MediaPlayer myMediaPlayer;

    public void initialize() {
        playMusic();
    }


    /**
     * Starts a new game and switches to character selection screen
     * @param theEvent - button or menuItem click
     */
    @FXML
    private void switchToCharacterSelection(final ActionEvent theEvent) {
        switchStageBuilder(theEvent, "character_selection.fxml");
    }


    /**
     * Gives the user a brief description about the game
     * @param theEvent - menuItem click
     */
    @FXML
    private void about(final ActionEvent theEvent) {
        System.out.println("this is the about section");
    }


    /**
     * Starts a tutorial for the game
     * @param theEvent - menuItem click
     */
    @FXML
    private void startTutorial(final ActionEvent theEvent) {
        //switchStageBuilder(event, "tutorial.fxml");
        System.out.println("you clicked start a tutorial");
    }


    /**
     * Switches screens back to the start_game screen, tutorial, or about section
     * @param theEvent - button or menuItem click
     * @param theFxmlName - fxml file name for the screen to switch to
     */
    @FXML
    private void switchStageBuilder(final ActionEvent theEvent, final String theFxmlName) {
        try {
            myMediaPlayer.stop();
            root = FXMLLoader.load(getClass().getClassLoader().getResource(theFxmlName));
            System.out.println("fxml was loaded.");
            stage = (Stage) someRoot.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void playMusic() {
        myMedia = new Media(getClass().getResource("/assets/titletheme.mp3").toString());
        myMediaPlayer = new MediaPlayer(myMedia);
        myMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        myMediaPlayer.play();
    }


    /**
     * Starts a game that was saved previously.
     * @param theEvent - button or menuItem click
     */
    @FXML
    private void loadGame(final ActionEvent theEvent) {
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
        File saveLocation = fileChooser.showOpenDialog(stage);

        if (saveLocation != null) {
            boolean gameLoaded = DungeonAdventureGame.loadGame(saveLocation);
            if (gameLoaded) {

                switchStageBuilder(theEvent, "dungeon.fxml");
            }
        }

        System.out.println("you clicked load a game");
    }

}
