package main.dungeonadventure.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class WelcomeScreenController {
    @FXML
    private Button myNewGameButton, myLoadGameButton;
    @FXML
    private MenuBar menu;
    @FXML
    private MenuItem myAbout, myTutorial, myLoadGame, myNewGame;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Parent someRoot;

    @FXML
    private void switchToStartScreen(ActionEvent event) {
        switchStageBuilder(event, "start_game.fxml");
    }

    @FXML
    private void about(ActionEvent event) {
        System.out.println("this is the about section");
    }

    @FXML
    private void loadGame(ActionEvent event) {
        System.out.println("you clicked load a game");
    }

    @FXML
    private void startTutorial(ActionEvent event) {
        //switchStageBuilder(event, "tutorial.fxml");
        System.out.println("you clicked start a tutorial");
    }
    @FXML
    private void switchStageBuilder(ActionEvent event, String fxmlName) {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlName));
            System.out.println("fxml was loaded.");
            stage = (Stage) someRoot.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
