package main.dungeonadventure.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.dungeonadventure.controller.FinishGameController;
import main.dungeonadventure.model.Dungeon;

import java.io.IOException;

public class DungeonAdventureGUI extends Application {

    //private Dungeon myDungeon;

    @Override
    public void start(final Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("welcome_screen.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to launch GUI from main controller class DungeonAdventureGame
     */
    public void launchGUI() {
        launch();
    }

    /**
     * Adds a dungeon object and assigns to myDungeon.
     * Can't create a constructor to handle this due to class
     * extending Application.
     * @param theDungeon Input Dungeon
     */
    public void addDungeon(final Dungeon theDungeon) {
        //myDungeon = theDungeon;
    }
}