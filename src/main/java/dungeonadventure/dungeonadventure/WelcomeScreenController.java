package dungeonadventure.dungeonadventure;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.util.Objects;

public class WelcomeScreenController {
    @FXML
    private Button myNewGameButton;
    @FXML
    private MenuBar menu;
    @FXML MenuItem menuItem;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void switchToStartScreen(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("start_game.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();scene = new Scene(root);
            String css = this.getClass().getResource("styleSheet.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
