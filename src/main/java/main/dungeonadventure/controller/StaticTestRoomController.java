package main.dungeonadventure.controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import javafx.scene.shape.Circle;
import main.dungeonadventure.model.*;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;

/**
 * Controller class for the Rooms
 * @Author Micaela Nomakchteinsky
 * @Author Luke Smith
 * @version 8-2022
 */
public class StaticTestRoomController {
    @FXML
    private static Group myEastDoors, myWestDoors;
    @FXML
    private static ImageView myNorthDoor, mySouthDoor, myHeroImg, myMonster;
    @FXML
    private static Button myNorthArrow, mySouthArrow, myEastArrow, myWestArrow;
    @FXML
    private static Button  myColumn, myHeroButton, myMonsterButton, myVisPot, myHPPot,
            myUseVisionPotionButton;
    @FXML
    private static Button myEnterDungeonBut, myAttackButton, myDrinkHPButton, myDrinkHPButton2;
    @FXML
    private static Circle myPit;
    @FXML
    private static AnchorPane fightPane, myEnterPane, myInventoryPane;
    @FXML
    private static Label myPillarCountLabel, myHPPotionCountLabel, myVisionPotionCountLabel;


    private static Room myRoom;
    private static Monster myRoomMonster;
    private static Dungeon myDungeon = Dungeon.getInstance();
    private static Hero myHero;


    @FXML
    public static void loadRoom() {
        myRoom = getHeroRoom();
        myHero = myDungeon.getHero();
System.out.println("TIME 1");
        setDoors(myRoom);
System.out.println("TIME 2");
        setItems(myRoom);
        HeroType currentHeroType = myHero.getHeroType();
        String imageURL = "/assets/" + currentHeroType.toString() + ".png";
        Image image = new Image(StaticTestRoomController.class.getResourceAsStream(imageURL));
        myHeroImg.setImage(image);
        myEnterPane.setVisible(false);
        myInventoryPane.setVisible(false);

        if (myHero.getVisionPotionCount() > 1) {
            myUseVisionPotionButton.setVisible(true);
        }

    }

    /**
     * Switches between rooms in the dungeon based on room items.
     * @param theEvent - button click
     */
    @FXML
    private static void switchRoom(final ActionEvent theEvent) {
        if(myRoom.getMonsterStatus()) {
            Button button = (Button) theEvent.getSource();
            String directionName = button.getText();
            moveHero(Direction.valueOf(directionName));
            myRoom = getHeroRoom();
            setDoors(myRoom);
            setItems(myRoom);
        }
    }

    /**
     * Gives the hero the potion when a potion is clicked
     * @param theEvent - button click
     */
    @FXML
    private static void pickUpHealingPotion(final ActionEvent theEvent) {
        myHero.setHealPotionCount(1);
        myHPPot.setVisible(false);
    }

    /**
     * Gives the hero a vision potion when picked up
     * @param theEvent - button click
     */
    @FXML
    private static void pickUpVisionPotion(final ActionEvent theEvent) {
        myHero.setVisionPotionCount(1);
        myVisPot.setVisible(false);
        myUseVisionPotionButton.setVisible(true);
    }

    @FXML
    private static void useHealingPotion(final ActionEvent theEvent) {
        if (myHero.getHealthPotionCount() > 0) {
            int healingPoints = myHero.getHP() + 10;
            myHero.setHP(healingPoints);
            myHero.setHealPotionCount(-1);
            System.out.println("Health Potion used");
        } else {
            System.out.println("No health potions available");
        }
    }

    @FXML
    private static void useVisionPotion(final ActionEvent theEvent) {
        if (myHero.getVisionPotionCount() > 0) {

            System.out.println("Vision Potion used");
        } else {
            System.out.println("No vision potions available");
        }
    }



    /**
     * Starts battle with the monster when monster is clicked
     * @param theEvent - button click
     */
    @FXML
    protected static void monsterClicked(final ActionEvent theEvent) {
        fightPane.setVisible(true);
    }

    @FXML
    private static void attackClicked(final ActionEvent theEvent) {
        int monsterSpd = myRoomMonster.getMyAtkSpd();
        int heroSpd = myHero.getMyAtkSpd();
        int timesAtk = heroSpd / monsterSpd;
        for (int i = 0; i < timesAtk; i++) {
            if (myRoomMonster.getHP() <= 0) {
                //heroWins();
                System.out.println("you defeated the monster");
                myRoom.setMonsterDftd(true);
                myMonsterButton.setVisible(false);
                fightPane.setVisible(false);
            } else {
                System.out.println("Hero attacking monster");
                myHero.attack(myRoomMonster);
            }
        }
        if (!myRoom.getMonsterStatus()) {
            System.out.println("Monster attacking hero");
            myRoomMonster.attack(myHero);
            if (myHero.getHP() <= 0) {
                //loseGame();
                System.out.println("you lost the game");
            }
        }
    }

    @FXML
    public static void closeInventory() {
        myInventoryPane.setVisible(false);
    }

    @FXML
    public static void openInventory() {
        myHPPotionCountLabel.setText("x " + myHero.getHealthPotionCount()+ " HP Potions");
        myVisionPotionCountLabel.setText("x " + myHero.getVisionPotionCount()+ " Vision Potions");
        myPillarCountLabel.setText("x " + myHero.getPillarCount() + " Pillars");
        myInventoryPane.setVisible(true);
    }

    @FXML
    public static void pickupPillar() {
        myHero.addPillarToInventory();
        myColumn.setVisible(false);
    }


    /**
     * moves the hero position in the dungeon based on which room the user is in
     * @param theDirection - direction in which the user goes
     */
    protected static void moveHero(final Direction theDirection) {
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
    protected static void setDoors(final Room theRoom) {
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
    private static void setItems(final Room theRoom) {
        if(!theRoom.containsPillar()) {
            myColumn.setVisible(false);
        } else {
            myColumn.setVisible(true);
        }
        HashSet<RoomItem> items = theRoom.getItems();
        if(!items.contains(RoomItem.MONSTER)) {
            myMonsterButton.setVisible(false);
            myRoom.setMonsterDftd(true);
        } else {
            myMonsterButton.setVisible(true);
            if (myRoom.getMonsterStatus()) {
                myMonsterButton.setVisible(false);
            }
            myRoomMonster = myRoom.getMonster();
            MonsterType currentMonster = theRoom.getMonster().getMonsterType();
            String imageURL = "/assets/" + currentMonster.toString() + ".png";
            //Image image = new Image(getClass().getResourceAsStream(imageURL));
            Image image = new Image(StaticTestRoomController.class.getResourceAsStream(imageURL));
            myMonster.setImage(image);
        }
        if(!items.contains(RoomItem.PIT)) {
            myPit.setVisible(false);
        } else {
            myPit.setVisible(true);
            fallInPit();
        }
        if(!items.contains(RoomItem.HEALTH_POTION)) {
            myVisPot.setVisible(false);
        } else {
            myVisPot.setVisible(true);
        }
        if(!items.contains(RoomItem.VISION_POTION)) {
            myHPPot.setVisible(false);
        } else {
            myHPPot.setVisible(true);
        }
    }

    private static void fallInPit() {
        Random rand = new Random();
        int damagePit = myHero.getHP() - (rand.nextInt(20) + 1);
        System.out.println("you now have " + damagePit + " HP");
        myHero.setHP(damagePit);
        if(myHero.getHP() <= 0) {
            System.out.println("you lost the game");
        }
    }

    /**
     * Gets the current room the hero is in
     * @return Room - the current room the hero is in
     */
    public static Room getHeroRoom() {
        return myDungeon.getCurrentRoom();
    }



}
