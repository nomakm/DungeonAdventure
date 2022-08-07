package main.dungeonadventure.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.dungeonadventure.model.*;
import main.dungeonadventure.view.DungeonAdventureGUI;

import java.awt.*;

public class TestRoomController {
    @FXML
    private Group myEastDoors, myWestDoors;
    @FXML
    private ImageView myNorthDoor, mySouthDoor;
    @FXML
    private Button myNorthArrow, mySouthArrow, myEastArrow, myWestArrow;
    @FXML
    private Button  myColumn, myHeroButton, myMonsterButton;
    @FXML
    private AnchorPane fightPane;

    private static Dungeon myDungeon = Dungeon.getInstance();

    //this is a test method, will be deleted
    @FXML
    private void testDoorAndArrow(ActionEvent event) {
        //myEastDoors and myWestDoors are a group
        //myNorthDoor and mySouthDoor are an image
        myNorthDoor.setVisible(false); // making images disappear (works with groups too)
        //myNorthArrow.setOpacity(0.0);
        //myNorthArrow.setDisable(true); // for disabling buttons
        myNorthArrow.setVisible(false);
    }

    @FXML
    private void switchRoom(ActionEvent event) {
        // when switching between rooms when in a room you just need to get the items and doors
        // maybe a bunch of if statements to check which doors are closed then in each if door.setOpacity(0.0), arrow.setDisable(true), arrow.setOpacity(0.0)
        // check which items are there then setOpacity(0.0), setDisable(true) if not there

        Button button = (Button) event.getSource();
        String directionName  = button.getText();
        moveHero(Direction.valueOf(directionName));
        Room room = getHeroRoom();
        setDoors(room);
        setItems(room);
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

    private void setDoors(Room room) {
        if(!room.isDoorOpen(Direction.NORTH)) {
            myNorthDoor.setVisible(false);
            myNorthArrow.setVisible(false);
        }
        if(!room.isDoorOpen(Direction.EAST)) {
            myEastDoors.setVisible(false);
            myEastArrow.setVisible(false);
        }
        if(!room.isDoorOpen(Direction.SOUTH)) {
            mySouthDoor.setVisible(false);
            mySouthArrow.setVisible(false);
        }
        if(!room.isDoorOpen(Direction.WEST)) {
            myWestDoors.setVisible(false);
            myWestArrow.setVisible(false);
        }
    }

    private void setItems(Room room) {
        if(!room.containsPillar()) {
            myColumn.setVisible(false);
        }
        // needs finishing for room items
    }

    @FXML
    private void potionPickedUp(ActionEvent event) {
        //method when potion is clicked. Gives hero the potion (doesnt drink it)
    }

    @FXML
    private void monsterClicked(ActionEvent event) {
        // calls battle method
        // goes to battle screen
        // sets opacity of monster to 0 when monster battle is done
        fightPane.setVisible(true);
    }

    public Room getHeroRoom() {
        return myDungeon.getCurrentRoom();
    }

}
