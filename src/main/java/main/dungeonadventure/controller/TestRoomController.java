package main.dungeonadventure.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.dungeonadventure.model.*;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Controller class for the Rooms
 * @Author Micaela Nomakchteinsky
 * @Author Luke Smith
 * @version 8-2022
 */
public class TestRoomController {
    @FXML
    private Group myEastDoors, myWestDoors, myHeroFight;
    @FXML
    private ImageView myNorthDoor, mySouthDoor, myHeroImg, myMonster, myBattleHero, myBattleMonster;
    @FXML
    private Button myNorthArrow, mySouthArrow, myEastArrow, myWestArrow;
    @FXML
    private Button  myColumn, myMonsterButton, myVisPot, myHPPot,
            myUseVisionPotionButton,  myUseHealthPotionButton2, myAttackButton;
    @FXML
    private Circle myPit;
    @FXML
    private AnchorPane fightPane, myEnterPane, myInventoryPane, myVisionPane;
    @FXML
    private Label myPillarCountLabel, myHPPotionCountLabel, myVisionPotionCountLabel;
    @FXML
    private Label myNWLabel, myNLabel, myNELabel, myWLabel, myCurrLabel, myELabel,
            mySWLabel, mySLabel, mySELabel;
    @FXML
    private ProgressBar myHeroHPBar, myMonsterHPBar;
    @FXML
    private Parent someRoot;

    private static Dungeon myDungeon;
    private Room myRoom;
    private Hero myHero;
    private Monster myRoomMonster;
    private HashSet<RoomItem> myItems;
    private HashMap<String, Label> myLabels;
    private Random myRand = new Random();
    private Media myMedia;
    private MediaPlayer myMediaPlayer;


    /**
     * Switches between rooms in the dungeon based on room items.
     * @param theEvent - button click
     */
    @FXML
    protected void switchRoom(final ActionEvent theEvent) {
        if(!myItems.contains(RoomItem.MONSTER)) {
            Button button = (Button) theEvent.getSource();
            String directionName = button.getText();
            moveHero(Direction.valueOf(directionName));
            myRoom = getHeroRoom();
            myHero = myDungeon.getHero();
            if (myRoom.isExit() && myHero.getPillarCount() == 4) {
                finishGame("won");
            }
            myItems = myRoom.getItems();
            setDoors(myRoom);
            setItems(myRoom);
        }
    }

    private void finishGame(String finishGame) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("finish_screen.fxml"));
            Parent root = loader.load();
            FinishGameController finishGameController = loader.getController();
            finishGameController.setGameLabel(finishGame);
            System.out.println("fxml was loaded.");
            Stage stage = (Stage) someRoot.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDungeon(Dungeon dungeon) {
        myDungeon = dungeon;
    }

    /**
     * Gives the hero the potion when a potion is clicked
     */
    @FXML
    private void pickUpHealingPotion() {
        myHero.setHealPotionCount(1);
        myHPPot.setVisible(false);
        myUseHealthPotionButton2.setVisible(true);
        myRoom.removeItem(RoomItem.HEALTH_POTION);
    }

    /**
     * Gives the hero a vision potion when picked up
     */
    @FXML
    private void pickUpVisionPotion() {
        myHero.setVisionPotionCount(1);
        myVisPot.setVisible(false);
        myUseVisionPotionButton.setVisible(true);
        myRoom.removeItem(RoomItem.VISION_POTION);
    }

    @FXML
    private void useHealthPotion(ActionEvent event) {
        if (myHero.getHealthPotionCount() > 0) {
            int healingPoints = myHero.getHP() + 10;
            myHero.setHP(healingPoints);
            myHero.setHealPotionCount(-1);
            Button button = (Button)event.getSource();
            if (button.getId().equals("" + myUseHealthPotionButton2.getId())) {
                openInventory();
            }
            System.out.println("Health Potion used");
            if (myHero.getHealthPotionCount() == 0) {
                myUseHealthPotionButton2.setVisible(false);
            }
        } else {
            System.out.println("No health potions available");
        }
    }

    @FXML
    private void useVisionPotion(final ActionEvent theEvent) {
        if (myHero.getVisionPotionCount() > 0) {
            HashMap<String, Room> neighbors = myDungeon.getNeighbors();
            for (String direction : neighbors.keySet()) {
                String label = "";
                Room room = neighbors.get(direction);
                if (neighbors.get(direction) != null) {
                    HashSet<RoomItem> items = room.getItems();
                    if (items.contains(RoomItem.HEALTH_POTION)) {
                        label += "H ";
                    }
                    if (items.contains(RoomItem.VISION_POTION)) {
                        label += "V ";
                    }
                    if (items.contains(RoomItem.MONSTER)) {
                        label += "M ";
                    }
                    if (items.contains(RoomItem.PIT)) {
                        label += "P ";
                    }
                    if (items.contains(RoomItem.PILLAR)) {
                        label += "PI ";
                    }
                    myLabels.get(direction).setText(label);
                } else {
                    myLabels.get(direction).setText("No Room");
                }
            }
            myVisionPane.setVisible(true);
            myHero.setVisionPotionCount(-1);
            openInventory();
            System.out.println("Vision Potion used");
            if (myHero.getVisionPotionCount() == 0) {
                myUseVisionPotionButton.setVisible(false);
            }
        } else {
            System.out.println("No vision potions available");
        }
    }

    @FXML
    private void exitVision() {
        myVisionPane.setVisible(false);
    }

    @FXML
    protected void enterDungeon() {
        myRoom = getHeroRoom();
        myHero = myDungeon.getHero();
        myItems = myRoom.getItems();
        setDoors(myRoom);
        setItems(myRoom);
        HeroType currentHeroType = myHero.getHeroType();
        String imageURL = "/assets/" + currentHeroType.toString() + ".png";
        Image image = new Image(getClass().getResourceAsStream(imageURL));
        myHeroImg.setImage(image);
        myBattleHero.setImage(image);
        myEnterPane.setVisible(false);
        myInventoryPane.setVisible(false);
        myLabels = addLabels();
        playMedia("/assets/dungeon.mp3");
    }

    private void playMedia(String theSongName) {
        myMedia = new Media(getClass().getResource(theSongName).toString());
        myMediaPlayer = new MediaPlayer(myMedia);
        myMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        myMediaPlayer.play();
    }

    private void stopMedia() {
        myMediaPlayer.stop();
    }

    private HashMap<String, Label> addLabels() {
        HashMap<String, Label> labels = new HashMap<>();
        labels.put("NORTHWEST", myNWLabel);
        labels.put("NORTH", myNLabel);
        labels.put("NORTHEAST", myNELabel);
        labels.put("WEST", myWLabel);
        labels.put("CURR", myCurrLabel);
        labels.put("EAST", myELabel);
        labels.put("SOUTHWEST", mySWLabel);
        labels.put("SOUTH", mySLabel);
        labels.put("SOUTHEAST", mySELabel);
        return labels;
    }

    /**
     * Starts battle with the monster when monster is clicked
     */
    @FXML
    private void monsterClicked() {
        fightPane.setVisible(true);
        stopMedia();
        playMedia("/assets/battle.mp3");
        Double hp = myHero.getHP() * 1.0;
        myHeroHPBar.setProgress((myHero.getHP() * 1.0) / myHero.getStartHP());
        myMonsterHPBar.setProgress((myRoomMonster.getHP() * 1.0) / myRoomMonster.getStartHP());
    }

    @FXML
    private void attackClicked() {
            int monsterSpd = myRoomMonster.getMyAtkSpd();
            int heroSpd = myHero.getMyAtkSpd();
            int timesAtk = heroSpd / monsterSpd;
            if (timesAtk == 0) {
                timesAtk = 1;
            }
            for (int i = 0; i < timesAtk; i++) {
                    System.out.println("Hero attacking monster");
                    animateAttack();
                    myHero.attack(myRoomMonster);
                if (myRoomMonster.getHP() <= 0) {
                    myMonsterHPBar.setProgress(0.0);
                    System.out.println("Monster was defeated");
                    myRoom.removeItem(RoomItem.MONSTER);
                    myItems.remove(RoomItem.MONSTER);
                    myMonsterButton.setVisible(false);
                    stopMedia();
                    playMedia("/assets/dungeon.mp3");
                    fightPane.setVisible(false);
                } else {
                    Double monsterHP = (myRoomMonster.getHP() * 1.0) / myRoomMonster.getStartHP();
                    myMonsterHPBar.setProgress((myRoomMonster.getHP() * 1.0) / myRoomMonster.getStartHP());
                }
            }
            if (myItems.contains(RoomItem.MONSTER)) {
                int chanceToBlock = myHero.getMyChanceToBlock();
                if (chanceToBlock <= (myRand.nextInt(10) + 1)) {
                    System.out.println("Monster attacking hero");
                    myRoomMonster.attack(myHero);
                    myHeroHPBar.setProgress((myHero.getHP() * 1.0) / myHero.getStartHP());
                    if (myHero.getHP() <= 0) {
                        finishGame("lost");
                        System.out.println("Hero died, game lost");
                    }
                }
            }
    }

    private void animateAttack() {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(myHeroFight);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(2);
        Double origPos = myHeroFight.getLayoutX();
        translate.setByX((myBattleMonster.getLayoutX() - origPos - 20));
        translate.setAutoReverse(true);
        translate.play();
    }

    @FXML
    public void closeInventory() {
        myInventoryPane.setVisible(false);
    }

    @FXML
    public void openInventory() {
        myHPPotionCountLabel.setText("x " + myHero.getHealthPotionCount()+ " HP Potions");
        myVisionPotionCountLabel.setText("x " + myHero.getVisionPotionCount()+ " Vision Potions");
        myPillarCountLabel.setText("x " + myHero.getPillarCount() + " Pillars");
        myInventoryPane.setVisible(true);
    }

    @FXML
    public void pickupPillar() {
        myHero.addPillarToInventory();
        myItems.remove(RoomItem.PILLAR);
        myColumn.setVisible(false);
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
    protected void setItems(final Room theRoom) {
        myItems = theRoom.getItems();

        if(!myItems.contains(RoomItem.PILLAR)) {
            myColumn.setVisible(false);
        } else {
            myColumn.setVisible(true);
        }
        if(!myItems.contains(RoomItem.MONSTER)) {
            myMonsterButton.setVisible(false);
        } else {
            myMonsterButton.setVisible(true);
            myRoomMonster = myRoom.getMonster();
            MonsterType currentMonster = myRoomMonster.getMonsterType();
            String imageURL = "/assets/" + currentMonster.toString() + ".png";
            Image image = new Image(getClass().getResourceAsStream(imageURL));
            myBattleMonster.setImage(image);
            myMonster.setImage(image);
        }

        if(!myItems.contains(RoomItem.PIT)) {
            myPit.setVisible(false);
        } else {
            myPit.setVisible(true);
            fallInPit();
        }

        if(!myItems.contains(RoomItem.HEALTH_POTION)) {
            myHPPot.setVisible(false);
        } else {
            myHPPot.setVisible(true);
        }

        if(!myItems.contains(RoomItem.VISION_POTION)) {
            myVisPot.setVisible(false);
        } else {
            myVisPot.setVisible(true);
        }
    }

    private void fallInPit() {
        int damagePit = myHero.getHP() - (myRand.nextInt(20) + 1);
        System.out.println("you now have " + damagePit + " HP");
        myHero.setHP(damagePit);
        if(myHero.getHP() <= 0) {
            finishGame("lost");
            System.out.println("you lost the game");
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
