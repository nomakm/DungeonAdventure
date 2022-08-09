package main.dungeonadventure.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import main.dungeonadventure.model.Direction;
import main.dungeonadventure.model.Dungeon;
import main.dungeonadventure.model.MonsterType;
import main.dungeonadventure.model.Room;

import java.awt.*;
import java.io.InputStream;
import java.util.HashSet;

public class CheckRoom {
    @FXML
    private Group myEastDoors, myWestDoors;
    @FXML
    private ImageView myNorthDoor, mySouthDoor, myMonster, myHeroImg;
    @FXML
    private Image myImage;
    @FXML
    private Button myNorthArrow, mySouthArrow, myEastArrow, myWestArrow;
    @FXML
    private Button myColumn, myHeroButton, myMonsterButton, myVisPot, myHPPot;
    @FXML
    private AnchorPane fightPane;
    @FXML
    private Circle myPit;

    private static Dungeon myDungeon = Dungeon.getInstance();

    /**
     * Gives the hero the potion when a potion is clicked
     * @param theEvent - button click
     */
    @FXML
    protected void potionPickedUp(final ActionEvent theEvent) {
        //method when potion is clicked. Gives hero the potion (doesnt drink it)

    }

    /**
     * Starts battle with the monster when monster is clicked
     * @param theEvent - button click
     */
    @FXML
    protected void monsterClicked(final ActionEvent theEvent) {
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
            myNorthDoor.setVisible(false);
            myNorthArrow.setVisible(false);
        }
        if(!theRoom.isDoorOpen(Direction.EAST)) {
            myEastDoors.setVisible(false);
            myEastArrow.setVisible(false);
        }
        if(!theRoom.isDoorOpen(Direction.SOUTH)) {
            mySouthDoor.setVisible(false);
            mySouthArrow.setVisible(false);
        }
        if(!theRoom.isDoorOpen(Direction.WEST)) {
            myWestDoors.setVisible(false);
            myWestArrow.setVisible(false);
        }
    }

    /**
     * Checks the items in a room to set the room details
     * @param theRoom - the room to check
     */
    protected void setItems(final Room theRoom) {
        if(!theRoom.containsPillar()) {
            myColumn.setVisible(false);
        }
        HashSet<String> items = theRoom.getItems();
        if(!items.contains("MONSTER")) {
            myMonsterButton.setVisible(false);
        } else {
            //Returns type of monster
            //TODO Should we have separate assets for each monster? MonsterType can be used for this.
            MonsterType currentMonster = theRoom.getMonster().getMonsterType();

            //PSEUDO
//            if (currentMonster == MonsterType.GOBLIN) {
//                myMonsterGoblin.setVisible(1);
//                myMonsterOgre.setVisible(0);
//                myMonsterSkeleton.setVisible(10;
//            } else if (currentMonster == MonsterType.OGRE) {
            //ETC
            //Alternatively if we had one monster asset that can be updated to a different picture depending on MonsterType,
            //this would be better than have 3 separate assets for each monster.


            //myMonster.setImage();
        }
        if(!items.contains("PIT")) {
            myPit.setVisible(false);
        }
        if(!items.contains("HP_POTION")) {
            myVisPot.setVisible(false);
        }
        if(!items.contains("VISION_POTION")) {
            myHPPot.setVisible(false);
        }
    }

    protected void changeHero(String heroType) {
        String imageString = "/assets/" + heroType + ".png";
        InputStream inStream = getClass().getResourceAsStream(imageString);
        myImage = new Image(inStream);
        myHeroImg.setImage(myImage);
    }

    /**
     * Gets the current room the hero is in
     * @return Room - the current room the hero is in
     */
    protected Room getHeroRoom() {
        return myDungeon.getCurrentRoom();
    }
}
