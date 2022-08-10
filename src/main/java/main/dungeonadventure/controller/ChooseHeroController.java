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
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

import main.dungeonadventure.model.*;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Controller for start_game screen used to choose and name a character
 * @Author Micaela Nomakchteinsky
 * @Author Luke Smith
 * @version 8-2022
 */
public class ChooseHeroController {
    @FXML
    private Button myBackButton, mySubmitButton;
    @FXML
    private TextField myHeroText;
    @FXML
    private RadioButton myThiefButton, myPriestessButton, myWarriorButton;
    @FXML
    private Label myHeroLabel, myNameLabel;

    private Stage myStage;
    private Scene myScene;
    private Parent myRoot;

    private static Dungeon myDungeon;
    private Hero myHero;
    private String myHeroName;
    private HeroType myHeroType;



    /**
     * When back button is hit, switches to welcome screen
     * @param theEvent - button click
     */
    @FXML
    private void backToWelcomeScreen(final ActionEvent theEvent) {
        switchStageBuilder(theEvent, "welcome_screen.fxml");
    }

    /**
     * When submit button is clicked and name is filled out, saves the name
     * @param theEvent - button click
     */
    @FXML
    private void heroNameSet(final ActionEvent theEvent) {
        if (!Objects.equals(myHeroText.getText(), null)) {
            String heroName = myHeroText.getText();
            myHeroText.setText(heroName);
            System.out.println(heroName);
            myNameLabel.setTextFill(Color.WHITE);
            myHeroName = heroName;
        }
    }

    /**
     * When a hero is selected from choices, saves the hero type
     * @param theEvent - radio button click
     */
    @FXML
    private void chooseHero(final ActionEvent theEvent) {
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

    /**
     * When name and hero are chosen and the next button is clicked will go to the
     * first room in the dungeon
     * @param theEvent - button click
     */
    @FXML
    private void switchToRoom1(final ActionEvent theEvent) throws IOException {
        if (myHeroText.getText().length() == 0) {
            myNameLabel.setTextFill(Color.RED);
        } else if (!myThiefButton.isSelected() && !myPriestessButton.isSelected()
                    && !myWarriorButton.isSelected()) {
            myHeroLabel.setTextFill(Color.RED);
        } else {
            myDungeon = new Dungeon();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("room1.fxml"));
            Parent root = loader.load();
            TestRoomController roomController = loader.getController();
            roomController.setDungeon(myDungeon);
            createHero(myHeroType, myHeroName);
            switchStageBuilder(theEvent, "room1.fxml");
        }
    }

    /**
     * Switches screens back to the welcome screen or the first room in the dungeon
     * @param theEvent - button click
     * @param theFxmlName - fxml file name for the screen to switch to
     */
    @FXML
    private void switchStageBuilder(final ActionEvent theEvent, final String theFxmlName) {
        try {
            myRoot = FXMLLoader.load(getClass().getClassLoader().getResource(theFxmlName));
            myStage = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
            myScene = new Scene(myRoot);
            myStage.setScene(myScene);
            myStage.show();
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
        myDungeon.setHero(myHero);
    }

}