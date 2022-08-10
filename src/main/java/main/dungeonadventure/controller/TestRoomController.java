package main.dungeonadventure.controller;

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

import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.dungeonadventure.model.*;
import main.dungeonadventure.view.DungeonAdventureGUI;

import java.awt.*;
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
    private Group myEastDoors, myWestDoors;
    @FXML
    private ImageView myNorthDoor, mySouthDoor, myHeroImg, myMonster, myBattleHero, myBattleMonster;
    @FXML
    private Button myNorthArrow, mySouthArrow, myEastArrow, myWestArrow;
    @FXML
    private Button  myColumn, myHeroButton, myMonsterButton, myVisPot, myHPPot,
            myUseVisionPotionButton, myUseHealthPotionButton, myUseHealthPotionButton2;
    @FXML
    private Button myEnterDungeonBut, myAttackButton, myDrinkHPButton, myDrinkHPButton2;
    @FXML
    private Circle myPit;
    @FXML
    private AnchorPane fightPane, myEnterPane, myInventoryPane;
    @FXML
    private Label myPillarCountLabel, myHPPotionCountLabel, myVisionPotionCountLabel;
    @FXML
    private ProgressBar myHeroHPBar, myMonsterHPBar;
    @FXML
    private Parent someRoot;

    private Room myRoom;
    private Monster myRoomMonster;
    private HashSet<RoomItem> myItems;



    private static Dungeon myDungeon;
    private Hero myHero;


    /**
     * Switches between rooms in the dungeon based on room items.
     * @param theEvent - button click
     */
    @FXML
    private void switchRoom(final ActionEvent theEvent) {
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
    private void enterDungeon(final ActionEvent theEvent) {
        myRoom = getHeroRoom();
        myHero = myDungeon.getHero();
        myItems = myRoom.getItems();
        setDoors(myRoom);
        setItems(myRoom);
        HeroType currentHeroType = myHero.getHeroType();
        String imageURL = "/assets/" + currentHeroType.toString() + ".png";
        Image image = new Image(getClass().getResourceAsStream(imageURL));
        myBattleHero.setImage(image);
        myHeroImg.setImage(image);
        myEnterPane.setVisible(false);
        myInventoryPane.setVisible(false);
    }

    /**
     * Starts battle with the monster when monster is clicked
     */
    @FXML
    private void monsterClicked() {
        fightPane.setVisible(true);
        Double hp = myHero.getHP() * 1.0;
        myHeroHPBar.setProgress((myHero.getHP() * 1.0) / myHero.getStartHP());
        myMonsterHPBar.setProgress((myRoomMonster.getHP() * 1.0) / myRoomMonster.getStartHP());
    }

    @FXML
    private void attackClicked() {
            int monsterSpd = myRoomMonster.getMyAtkSpd();
            int heroSpd = myHero.getMyAtkSpd();
            int timesAtk = heroSpd / monsterSpd;
            for (int i = 0; i < timesAtk; i++) {
                    System.out.println("Hero attacking monster");
                    myHero.attack(myRoomMonster);
                if (myRoomMonster.getHP() <= 0) {
                    myMonsterHPBar.setProgress(0.0);
                    System.out.println("Monster was defeated");
                    myRoom.removeItem(RoomItem.MONSTER);
                    myItems.remove(RoomItem.MONSTER);
                    myMonsterButton.setVisible(false);
                    fightPane.setVisible(false);
                } else {
                    Double monsterHP = (myRoomMonster.getHP() * 1.0) / myRoomMonster.getStartHP();
                    myMonsterHPBar.setProgress((myRoomMonster.getHP() * 1.0) / myRoomMonster.getStartHP());
                }
            }
            if (myItems.contains(RoomItem.MONSTER)) {
                System.out.println("Monster attacking hero");
                myRoomMonster.attack(myHero);
                myHeroHPBar.setProgress((myHero.getHP() * 1.0) / myHero.getStartHP());
                if (myHero.getHP() <= 0) {
                    finishGame("lost");
                    System.out.println("Hero died, game lost");
                }
            }
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
    private void moveHero(final Direction theDirection) {
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
    private void setDoors(final Room theRoom) {
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
        Random rand = new Random();
        int damagePit = myHero.getHP() - (rand.nextInt(20) + 1);
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
