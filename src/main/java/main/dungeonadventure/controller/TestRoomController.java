package main.dungeonadventure.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.dungeonadventure.model.*;
import main.dungeonadventure.view.DungeonAdventureGUI;

import java.awt.*;

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
    @FXML
    private Button myEastArrow;
    @FXML
    private Button myWestArrow;

    private static Dungeon myDungeon;

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

    @FXML
    private void switchRoom(ActionEvent event) {
        // when switching between rooms when in a room you just need to get the items and doors
        // maybe a bunch of if statements to check which doors are closed then in each if door.setOpacity(0.0), arrow.setDisable(true), arrow.setOpacity(0.0)
        // check which items are there then setOpacity(0.0), setDisable(true) if not there
    }

    public void moveHero(final Direction theDirection) {
        Point currentPos = myDungeon.getHeroPosition();
        if (theDirection == Direction.NORTH) {
            myDungeon.setHeroPosition(currentPos.x - 1, currentPos.y);
        } else if (theDirection == Direction.WEST) {
            myDungeon.setHeroPosition(currentPos.x, currentPos.y - 1);
        } else if (theDirection == Direction.EAST) {
            myDungeon.setHeroPosition(currentPos.x, currentPos.y + 1);
        } else if (theDirection == Direction.SOUTH) {
            myDungeon.setHeroPosition(currentPos.x + 1, currentPos.y);
        }
    }

    public Room getHeroRoom() {
        return myDungeon.getCurrentRoom();
    }

}
