package main.dungeonadventure.controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import main.dungeonadventure.model.*;
import main.dungeonadventure.view.DungeonAdventureGUI;

import java.awt.*;

/**
 * Controller class for the Rooms
 * @Author Micaela Nomakchteinsky
 * @Author Luke Smith
 * @version 8-2022
 */
public class TestRoomController extends CheckRoom {
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

    /**
     * Switches between rooms in the dungeon based on room items.
     * @param theEvent - button click
     */
    @FXML
    private void switchRoom(final ActionEvent theEvent) {
        // when switching between rooms when in a room you just need to get the items and doors
        // maybe a bunch of if statements to check which doors are closed then in each if door.setOpacity(0.0), arrow.setDisable(true), arrow.setOpacity(0.0)
        // check which items are there then setOpacity(0.0), setDisable(true) if not there

        Button button = (Button) theEvent.getSource();
        String directionName  = button.getText();
        moveHero(Direction.valueOf(directionName));
        Room room = getHeroRoom();
        setDoors(room);
        setItems(room);
    }

//    /**
//     * Gives the hero the potion when a potion is clicked
//     * @param theEvent - button click
//     */
//    @FXML
//    private void potionPickedUp(final ActionEvent theEvent) {
//        //method when potion is clicked. Gives hero the potion (doesnt drink it)
//    }
//
//    /**
//     * Starts battle with the monster when monster is clicked
//     * @param theEvent - button click
//     */
//    @FXML
//    private void monsterClicked(final ActionEvent theEvent) {
//        // calls battle method
//        // goes to battle screen
//        // sets opacity of monster to 0 when monster battle is done
//        fightPane.setVisible(true);
//    }
//
//    /**
//     * moves the hero position in the dungeon based on which room the user is in
//     * @param theDirection - direction in which the user goes
//     */
//    public void moveHero(final Direction theDirection) {
//        Point currentPos = myDungeon.getHeroPosition();
//        if (theDirection == Direction.NORTH) {
//            myDungeon.setHeroPosition(currentPos.x - 1, currentPos.y);
//        } else if (theDirection == Direction.WEST) {
//            myDungeon.setHeroPosition(currentPos.x, currentPos.y - 1);
//        } else if (theDirection == Direction.EAST) {
//            myDungeon.setHeroPosition(currentPos.x, currentPos.y + 1);
//        } else if (theDirection == Direction.SOUTH) {
//            myDungeon.setHeroPosition(currentPos.x + 1, currentPos.y);
//        }
//
//    }
//
//    /**
//     * Checks the closed doors in a room to set room details
//     * @param theRoom - the Room to check
//     */
//    private void setDoors(final Room theRoom) {
//        if(!theRoom.isDoorOpen(Direction.NORTH)) {
//            myNorthDoor.setVisible(false);
//            myNorthArrow.setVisible(false);
//        }
//        if(!theRoom.isDoorOpen(Direction.EAST)) {
//            myEastDoors.setVisible(false);
//            myEastArrow.setVisible(false);
//        }
//        if(!theRoom.isDoorOpen(Direction.SOUTH)) {
//            mySouthDoor.setVisible(false);
//            mySouthArrow.setVisible(false);
//        }
//        if(!theRoom.isDoorOpen(Direction.WEST)) {
//            myWestDoors.setVisible(false);
//            myWestArrow.setVisible(false);
//        }
//    }
//
//    /**
//     * Checks the items in a room to set the room details
//     * @param theRoom - the room to check
//     */
//    private void setItems(final Room theRoom) {
//        if(!theRoom.containsPillar()) {
//            myColumn.setVisible(false);
//        }
//
//        // needs finishing for room items
//    }
//
//    /**
//     * Gets the current room the hero is in
//     * @return Room - the current room the hero is in
//     */
//    public Room getHeroRoom() {
//        return myDungeon.getCurrentRoom();
//    }

}
