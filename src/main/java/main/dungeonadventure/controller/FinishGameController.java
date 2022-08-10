package main.dungeonadventure.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.dungeonadventure.model.Dungeon;

public class FinishGameController {
    @FXML
    private Button myPlayAgain, myQuitGame;
    @FXML
    private Label myFinishLabel;

    @FXML
    private void playAgain(ActionEvent theEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("welcome_screen.fxml"));
            Stage stage = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setGameLabel(String gameOutcome) {
        myFinishLabel.setText("You " + gameOutcome + "!");
    }

    @FXML
    private void quitGame() {
    }
}
