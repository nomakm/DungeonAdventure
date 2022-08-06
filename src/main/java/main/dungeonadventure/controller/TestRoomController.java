package main.dungeonadventure.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TestRoomController {
    @FXML
    private Group myEastDoors;
    @FXML
    private Group myWestDoor;
    @FXML
    private ImageView myNorthDoor;
    @FXML
    private ImageView mySouthDoor;
    @FXML
    private Button myNorthArrow;
    @FXML
    private Button mySouthArrow;
    private Button myEastArrow;
    private Button myWestArrow;

    @FXML
    private void testDoorAndArrow(ActionEvent event) {
        //myEastDoors and myWestDoors are a group
        //myNorthDoor and mySouthDoor are an image
        myNorthDoor.setOpacity(0.0); // making images disappear (works with groups too)
        //myNorthArrow.setOpacity(0.0);
        myNorthArrow.setDisable(true); // for disabling buttons
    }

    @FXML
    private void testButtonDisable(ActionEvent event) {
        mySouthDoor.setOpacity(0.0);
    }
}
