package main.dungeonadventure.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

public class StartGameController {
    @FXML
    private Button myBackButton, mySubmitButton;
    @FXML
    private TextField myHeroText;
    @FXML
    private RadioButton myThiefButton, myPriestessButton, myWarriorButton;
    @FXML
    private Label myHeroLabel, myNameLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private int submitButtonClick = 0;

    @FXML
    private void backToWelcomeScreen(ActionEvent event) {
        switchStageBuilder(event, "welcome_screen.fxml");
    }

    @FXML
    private void heroNameSet(ActionEvent event) {
        if (!Objects.equals(myHeroText.getText(), null)) {
            String heroName = myHeroText.getText();
            myHeroText.setText(heroName);
            System.out.println(heroName);
            myNameLabel.setTextFill(Color.WHITE);
            submitButtonClick++;
            //send heroName to game? for saving purposes?
        }
    }

    @FXML
    private void chooseHero(ActionEvent event) {
        if (myThiefButton.isSelected()) {
            //send info to model that thief is the hero
            System.out.println("Thief is chosen");
        } else if (myPriestessButton.isSelected()) {
            //send info to model that thief is the hero
            System.out.println("Priestess is chosen");
        } else if (myWarriorButton.isSelected()) {
            //send info to model that the warrior is the hero
            System.out.println("Warrior is chosen");
        }
    }

    @FXML
    private void switchToRoom1(ActionEvent event) {
        if (submitButtonClick <= 0 || myHeroText.getText().length() == 0) {
            myNameLabel.setTextFill(Color.RED);
        } else if (!myThiefButton.isSelected() && !myPriestessButton.isSelected() && !myWarriorButton.isSelected()) {
            myHeroLabel.setTextFill(Color.RED);
        } else {
            submitButtonClick = 0;
            switchStageBuilder(event, "room1.fxml");
        }
    }

    @FXML
    private void switchStageBuilder(ActionEvent event, String fxmlName) {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlName));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            //String css = this.getClass().getClassLoader().getResource("stylesheet.css").toExternalForm();
            //scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}