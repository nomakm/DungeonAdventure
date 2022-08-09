package main.dungeonadventure.controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import javafx.scene.shape.Circle;
import main.dungeonadventure.model.*;
import main.dungeonadventure.view.DungeonAdventureGUI;

import java.awt.*;
import java.util.HashSet;

/**
 * Controller class for the Rooms
 * @Author Micaela Nomakchteinsky
 * @Author Luke Smith
 * @version 8-2022
 */
public class TestRoomController {
    @FXML
    private Group myEastDoors, myWestDoors;
    @FXML
    private ImageView myNorthDoor, mySouthDoor, myHeroImg, myMonster;
    @FXML
    private Button myNorthArrow, mySouthArrow, myEastArrow, myWestArrow;
    @FXML
    private Button  myColumn, myHeroButton, myMonsterButton, myVisPot, myHPPot;
    @FXML
    private Button myEnterDungeonBut;
    @FXML
    private Circle myPit;
    @FXML
    private AnchorPane fightPane, myEnterPane;
    Image image;



    private static Dungeon myDungeon = Dungeon.getInstance();
    private Hero myHero;
    /**
     * Switches between rooms in the dungeon based on room items.
     * @param theEvent - button click
     */
    @FXML
    private void switchRoom(final ActionEvent theEvent) {
        //TODO: wont switch rooms if monster is still in the room. easy way would be to check if monster is visible in the room but
        // probably better way is to add a isMonster fought boolean in the room class
        Button button = (Button) theEvent.getSource();
        String directionName  = button.getText();
        moveHero(Direction.valueOf(directionName));
        Room room = getHeroRoom();
        setDoors(room);
        setItems(room);
    }
    /**
     * Gives the hero the potion when a potion is clicked
     * @param theEvent - button click
     */
    @FXML
    protected void potionPickedUp(final ActionEvent theEvent) {
        //method when potion is clicked. Gives hero the potion (doesnt drink it)

    }

    @FXML
    private void enterDungeon(final ActionEvent theEvent) {
        Room room = getHeroRoom();
        setDoors(room);
        setItems(room);
        myHero = myDungeon.getHero();
        HeroType currentHeroType = myHero.getHeroType();
        String imageURL = "/assets/" + currentHeroType.toString() + ".png";
        Image image = new Image(getClass().getResourceAsStream(imageURL));
        myHeroImg.setImage(image);
        myEnterPane.setVisible(false);
    }

    /**
     * Starts battle with the monster when monster is clicked
     * @param theEvent - button click
     */
    @FXML
    protected void monsterClicked(final ActionEvent theEvent) {
        //TODO battle method
        // calls battle method
        // goes to battle screen
        // sets opacity of monster to 0 when monster battle is done
        fightPane.setVisible(true);
    }

    /**
     * moves the hero position in the dungeon based on which room the user is in
     * @param theDirection - direction in which the user goes
     */
    protected void moveHero(final Direction theDirection) {
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

    /**
     * Checks the closed doors in a room to set room details
     * @param theRoom - the Room to check
     */
    protected void setDoors(final Room theRoom) {
        if(!theRoom.isDoorOpen(Direction.NORTH)) {
            myNorthDoor.setVisible(true);
            myNorthArrow.setVisible(false);
        } else {
            myNorthDoor.setVisible(false);
            myNorthArrow.setVisible(true);
        }
        if(!theRoom.isDoorOpen(Direction.EAST)) {
            myEastDoors.setVisible(true);
            myEastArrow.setVisible(false);
        } else {
            myEastDoors.setVisible(false);
            myEastArrow.setVisible(true);
        }
        if(!theRoom.isDoorOpen(Direction.SOUTH)) {
            mySouthDoor.setVisible(true);
            mySouthArrow.setVisible(false);
        } else {
            mySouthDoor.setVisible(false);
            mySouthArrow.setVisible(true);
        }
        if(!theRoom.isDoorOpen(Direction.WEST)) {
            myWestDoors.setVisible(true);
            myWestArrow.setVisible(false);
        } else {
            myWestDoors.setVisible(false);
            myWestArrow.setVisible(true);
        }
    }

    /**
     * Checks the items in a room to set the room details
     * @param theRoom - the room to check
     */
    private void setItems(final Room theRoom) {
        if(!theRoom.containsPillar()) {
            myColumn.setVisible(false);
        } else {
            myColumn.setVisible(true);
        }
        HashSet<String> items = theRoom.getItems();
        if(!items.contains("MONSTER")) {
            myMonsterButton.setVisible(false);
        } else {
            myMonsterButton.setVisible(true);
            //TODO get the monster type and set the image to the monster
            MonsterType currentMonster = theRoom.getMonster().getMonsterType();
            String imageURL = "/assets/" + currentMonster.toString() + ".png";
            Image image = new Image(getClass().getResourceAsStream(imageURL));
            myMonster.setImage(image);
            //TODO Should we have separate assets for each monster? MonsterType can be used for this.
//            MonsterType currentMonster = theRoom.getMonster().getMonsterType();
//
//            //PSEUDO
////            if (currentMonster == MonsterType.GOBLIN) {
////                myMonsterGoblin.setVisible(1);
////                myMonsterOgre.setVisible(0);
////                myMonsterSkeleton.setVisible(10;
////            } else if (currentMonster == MonsterType.OGRE) {
//            //ETC
//            //Alternatively if we had one monster asset that can be updated to a different picture depending on MonsterType,
//            //this would be better than have 3 separate assets for each monster.
        }
        if(!items.contains("PIT")) {
            myPit.setVisible(false);
        } else {
            myPit.setVisible(true);
        }
        if(!items.contains("HP_POTION")) {
            myVisPot.setVisible(false);
        } else {
            myVisPot.setVisible(true);
        }
        if(!items.contains("VISION_POTION")) {
            myHPPot.setVisible(false);
        } else {
            myHPPot.setVisible(true);
        }
    }

    /**
     * Gets the current room the hero is in
     * @return Room - the current room the hero is in
     */
    public Room getHeroRoom() {
        return myDungeon.getCurrentRoom();
    }

}
