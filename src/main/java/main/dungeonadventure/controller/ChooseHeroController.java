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
import main.dungeonadventure.model.*;

import java.util.Objects;

public class ChooseHeroController {
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

    private Hero myHero;
    private String myHeroName;
    HeroType myHeroType;

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
            myHeroName = heroName;
        }
    }

    @FXML
    private void chooseHero(ActionEvent event) {
        if (myThiefButton.isSelected()) {
            myHeroType = HeroType.THIEF;
            System.out.println("Thief is chosen");
        } else if (myPriestessButton.isSelected()) {
            myHeroType = HeroType.PRIESTESS;
            System.out.println("Priestess is chosen");
        } else if (myWarriorButton.isSelected()) {
            myHeroType = HeroType.WARRIOR;
            System.out.println("Warrior is chosen");
        }
    }

    @FXML
    private void switchToRoom1(ActionEvent event) {
        if (myHeroText.getText().length() == 0) {
            myNameLabel.setTextFill(Color.RED);
        } else if (!myThiefButton.isSelected() && !myPriestessButton.isSelected() && !myWarriorButton.isSelected()) {
            myHeroLabel.setTextFill(Color.RED);
        } else {
            createHero(myHeroType, myHeroName);
            switchStageBuilder(event, "room1.fxml");
        }
    }

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
    }

    @FXML
    private void switchStageBuilder(ActionEvent event, String fxmlName) {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlName));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}