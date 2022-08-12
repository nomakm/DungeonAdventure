package main.dungeonadventure.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.dungeonadventure.model.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Controller class for the Rooms
 * @Author Micaela Nomakchteinsky
 * @Author Luke Smith
 * @version 8-2022
 */
public class RoomController {
    @FXML
    private Group myEastDoors, myWestDoors;
    @FXML
    private ImageView myNorthDoor, mySouthDoor, myHeroImg, myMonster;
    @FXML
    private Button myNorthArrow, mySouthArrow, myEastArrow, myWestArrow;
    @FXML
    private Button  myColumn, myMonsterButton, myVisPot, myHPPot,
            myUseVisionPotionButton, myUseHealthPotionButton2, myBomb;
    @FXML
    private Circle myPit;
    @FXML
    private AnchorPane myInventoryPane, myVisionPane;
    @FXML
    private Label myPillarCountLabel, myHPPotionCountLabel, myVisionPotionCountLabel, myBombCountLabel;
    @FXML
    private Label myNWLabel, myNLabel, myNELabel, myWLabel, myCurrLabel, myELabel,
            mySWLabel, mySLabel, mySELabel;
    @FXML
    private Parent someRoot;

    @FXML
    private Stage stage;

    private static Dungeon myDungeon;
    private Room myRoom;
    private Hero myHero;
    private Monster myRoomMonster;
    private HashSet<RoomItem> myItems;
    private HashMap<String, Label> myLabels;
    private Random myRand = new Random();
    private Media myMedia;
    private MediaPlayer myMediaPlayer;
    private Image myHeroImage;
    private Image myMonsterImage;


    /**
     * This method will run everytime dungeon.fxml is loaded.
     * Retrieve's the game's dungeon instance, grabs data from hero's
     * room to preload assets before launching view.
     */
    public void initialize() {
        playMedia("/assets/dungeon.mp3");
        myLabels = addLabels();
        myDungeon = DungeonAdventureGame.getDungeon();
        loadRoom();
        setHeroImage();

    }

    public void loadRoom() {
        this.myRoom = myDungeon.getCurrentRoom();
        this.myHero = myDungeon.getHero();
        this.myRoomMonster = myRoom.getMonster();
        this.myItems = myRoom.getItems();
        setItems(myRoom);
        setDoors();
    }

    public void setHeroImage() {
        String imageURL = "/assets/" + myHero.getHeroType().toString() + ".png";
        myHeroImage = new Image(getClass().getResourceAsStream(imageURL));
        myHeroImg.setImage(myHeroImage);
    }

    //Deprecated by LS
//    public void setDungeon(Image theHeroImage) {
//        this.myDungeon = DungeonAdventureGame.getDungeon();
//        this.myRoom = myDungeon.getCurrentRoom();
//        this.myHero = myDungeon.getHero();
//        this.myRoomMonster = myRoom.getMonster();
//        this.myItems = myRoom.getItems();
//        this.myHeroImage = theHeroImage;
//        myHeroImg.setImage(myHeroImage);
//        setItems(myRoom);
//        setDoors(myRoom);
//    }

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
            setDoors();
            setItems(myRoom);
        }
    }

    @FXML
    private void saveAndExit(final ActionEvent theEvent) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ser files (*.ser)", "*.ser");

        File recordsDir = new File(System.getProperty("user.home"), ".DungeonAdventure/saves");
        if (! recordsDir.exists()) {
            recordsDir.mkdirs();
        }

        fileChooser.getExtensionFilters().add(extFilter);

        fileChooser.setInitialDirectory(recordsDir);

        //Show save file dialog
        File saveLocation = fileChooser.showSaveDialog(stage);

        if (saveLocation != null) {
            DungeonAdventureGame.saveGame(saveLocation);
        }

        System.exit(0);

    }

    private void finishGame(String finishGame) {
        try {
            myMediaPlayer.stop();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("game_over_screen.fxml"));
            Parent root = loader.load();
            GameOverController gameOverController = loader.getController();
            gameOverController.setScreen(finishGame);
System.out.println("DEBUG - game_over_screen.fxml was loaded.");
            Stage stage = (Stage) someRoot.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gives the hero the potion when a potion is clicked
     */
    @FXML
    private void pickUpHealingPotion() {
        myHero.setHealPotionCount(1);
        myHPPot.setVisible(false);
        myRoom.removeItem(RoomItem.HEALTH_POTION);
    }

    /**
     * Gives the hero a vision potion when picked up
     */
    @FXML
    private void pickUpVisionPotion() {
        myHero.setVisionPotionCount(1);
        myVisPot.setVisible(false);
        myRoom.removeItem(RoomItem.VISION_POTION);
    }

    @FXML
    private void pickUpBomb() {
        myHero.setBombCount(1);
        myBomb.setVisible(false);
        myRoom.removeItem(RoomItem.BOMB);
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
                    if (items.contains(RoomItem.BOMB)) {
                        label += "B ";
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
        try {
            myMediaPlayer.stop();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("battle.fxml"));
            Parent root = loader.load();
            BattleController battleController = loader.getController();
            battleController.setScreen(myDungeon, myHeroImage, myMonsterImage);
System.out.println("DEBUG - battle.fxml was loaded.");
            Stage stage = (Stage) someRoot.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void endBattle() {
System.out.println("DEBUG - Monster was defeated");
        myRoom.removeItem(RoomItem.MONSTER);
        myItems.remove(RoomItem.MONSTER);
        myMonsterButton.setVisible(false);
        setDoors();
    }


    @FXML
    public void closeInventory() {
        myInventoryPane.setVisible(false);
    }

    @FXML
    public void openInventory() {
        myHPPotionCountLabel.setText("x " + myHero.getHealthPotionCount()+ " HP Potions");
        myVisionPotionCountLabel.setText("x " + myHero.getVisionPotionCount()+ " Vision Potions");
        myBombCountLabel.setText("x " + myHero.getBombCount() + " Bombs");
        myPillarCountLabel.setText("x " + myHero.getPillarCount() + " Pillars");
        myInventoryPane.setVisible(true);
        if (myHero.getHealthPotionCount() > 0) {
            myUseHealthPotionButton2.setVisible(true);
        }
        if (myHero.getVisionPotionCount() > 0) {
            myUseVisionPotionButton.setVisible(true);
        }
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
     */
    private void setDoors() {

        //Trap player in room if monster is in room
        if (myRoom.getItems().contains(RoomItem.MONSTER)) {
            myNorthDoor.setVisible(true);
            myNorthArrow.setVisible(false);
            myEastDoors.setVisible(true);
            myEastArrow.setVisible(false);
            mySouthDoor.setVisible(true);
            mySouthArrow.setVisible(false);
            myWestDoors.setVisible(true);
            myWestArrow.setVisible(false);
        } else {
            if (!myRoom.isDoorOpen(Direction.NORTH)) {
                myNorthDoor.setVisible(true);
                myNorthArrow.setVisible(false);
            } else {
                myNorthDoor.setVisible(false);
                myNorthArrow.setVisible(true);
            }
            if (!myRoom.isDoorOpen(Direction.EAST)) {
                myEastDoors.setVisible(true);
                myEastArrow.setVisible(false);
            } else {
                myEastDoors.setVisible(false);
                myEastArrow.setVisible(true);
            }
            if (!myRoom.isDoorOpen(Direction.SOUTH)) {
                mySouthDoor.setVisible(true);
                mySouthArrow.setVisible(false);
            } else {
                mySouthDoor.setVisible(false);
                mySouthArrow.setVisible(true);
            }
            if (!myRoom.isDoorOpen(Direction.WEST)) {
                myWestDoors.setVisible(true);
                myWestArrow.setVisible(false);
            } else {
                myWestDoors.setVisible(false);
                myWestArrow.setVisible(true);
            }
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
            myMonsterImage = new Image(getClass().getResourceAsStream(imageURL));
            myMonster.setImage(myMonsterImage);
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
        if(!myItems.contains(RoomItem.BOMB)) {
            myBomb.setVisible(false);
        } else {
            myBomb.setVisible(true);
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
    private Room getHeroRoom() {
        return myDungeon.getCurrentRoom();
    }



}
