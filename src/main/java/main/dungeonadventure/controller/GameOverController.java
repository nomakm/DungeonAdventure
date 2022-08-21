package main.dungeonadventure.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * Controller for game_over_screen used to allow user to play another game or quit the game
 * @Author Micaela Nomakchteinsky
 * @Author Luke Smith
 * @version 8-2022
 */
public class GameOverController {

    /** Label to set win or lose text*/
    @FXML
    private Label myFinishLabel;
    /** Used to play Media */
    private MediaPlayer myMediaPlayer;

    /**
     * Switches back to the welcome screen when the user clicks Play Again button
     * @param theEvent - the Button clicked
     */
    @FXML
    private void playAgain(final ActionEvent theEvent) {
        try {
            myMediaPlayer.stop();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(
                    "welcome_screen.fxml"));
            Stage stage = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * When the user quits the game, the program ends
     */
    @FXML
    private void quitGame() {
        System.exit(0);
    }

    /**
     * Initializes screen with song playing
     */
    public void initialize() {
        playMedia("/assets/titletheme.mp3");
    }

    /**
     * Plays specified song
     * @param theSongName - song to be played
     */
    private void playMedia(final String theSongName) {
        Media myMedia = new Media(getClass().getResource(theSongName).toString());
        myMediaPlayer = new MediaPlayer(myMedia);
        myMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        myMediaPlayer.play();
    }

    /**
     * Sets the screen to show lose or win depending on game outcome
     * @param gameOutcome - String of won or lost
     */
    public void setScreen(final String gameOutcome) {
        myFinishLabel.setText("You " + gameOutcome + "!");
    }

}
