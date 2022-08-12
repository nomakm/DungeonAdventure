package main.dungeonadventure.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonAdventureGUI extends Application {

    /**
     * Starts game to initial starting screen.
     * @param stage JavaFX Stage to execute.
     */
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

}