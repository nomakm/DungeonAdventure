package main.dungeonadventure.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.dungeonadventure.model.Dungeon;

import java.io.IOException;

public class DungeonAdventureGUI extends Application {

    private Dungeon myDungeon;

    @Override
    public void start(final Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("welcome_screen.fxml"));
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
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
        myDungeon = theDungeon;
    }
}