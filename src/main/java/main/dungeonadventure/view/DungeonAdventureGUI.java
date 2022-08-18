package main.dungeonadventure.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Starting class for launching Dungeon Adventure GUI
 */
public class DungeonAdventureGUI extends Application {


    /**
     * Starts game to initial starting screen.
     * @param theStage JavaFX Stage to execute.
     */
    @Override
    public void start(final Stage theStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("welcome_screen.fxml"));
            Scene scene = new Scene(root);
            theStage.setScene(scene);
            theStage.setResizable(false);
            theStage.show();
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

}