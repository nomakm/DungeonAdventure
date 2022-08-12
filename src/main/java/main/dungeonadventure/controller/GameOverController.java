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

public class GameOverController {
    @FXML
    private Label myFinishLabel;
    private MediaPlayer myMediaPlayer;

    @FXML
    private void playAgain(ActionEvent theEvent) {
        try {
            myMediaPlayer.stop();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("welcome_screen.fxml"));
            Stage stage = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void quitGame() {
        System.exit(0);
    }

    public void initialize() {
        playMedia("/assets/titletheme.mp3");
    }


    private void playMedia(String theSongName) {
        Media myMedia = new Media(getClass().getResource(theSongName).toString());
        myMediaPlayer = new MediaPlayer(myMedia);
        myMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        myMediaPlayer.play();
    }

    public void setScreen(String gameOutcome) {
        myFinishLabel.setText("You " + gameOutcome + "!");
    }

}
