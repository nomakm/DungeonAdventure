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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.dungeonadventure.model.*;

import java.util.HashSet;
import java.util.Random;

public class BattleController {
    @FXML
    private Group myHeroFight;
    @FXML
    private ImageView myBattleHero, myBattleMonster;
    @FXML
    private ProgressBar myHeroHPBar, myMonsterHPBar;
    @FXML
    private Parent someRoot;

    private static Dungeon myDungeon;
    private Room myRoom;
    private Hero myHero;
    private Monster myMonster;
    private HashSet<RoomItem> myItems;
    private Random myRand = new Random();
    private MediaPlayer myMediaPlayer;
    private Image myHeroImage;
    private Media myMedia;

    public void setScreen(Dungeon theDungeon, MediaPlayer myMediaPlayer, Image theHeroImage, Image theMonsterImage) {
        this.myDungeon = theDungeon;
        this.myRoom = myDungeon.getCurrentRoom();
        this.myHero = myDungeon.getHero();
        this.myMonster = myRoom.getMonster();
        this.myItems = myRoom.getItems();
        this.myMediaPlayer = myMediaPlayer;
        this.myHeroImage = theHeroImage;
        myBattleHero.setImage(theHeroImage);
        myBattleMonster.setImage(theMonsterImage);
        Double hp = myHero.getHP() * 1.0;
        myHeroHPBar.setProgress((myHero.getHP() * 1.0) / myHero.getStartHP());
        myMonsterHPBar.setProgress((myMonster.getHP() * 1.0) / myMonster.getStartHP());
        playMedia("/assets/battle.mp3");
    }

    @FXML
    private void attackClicked() {
        int monsterSpd = myMonster.getMyAtkSpd();
        int heroSpd = myHero.getMyAtkSpd();
        int timesAtk = heroSpd / monsterSpd;
        if (timesAtk == 0) {
            timesAtk = 1;
        }
        for (int i = 0; i < timesAtk; i++) {
            System.out.println("Hero attacking monster");
            animateAttack();
            myHero.attack(myMonster);
            if (myMonster.getHP() <= 0) {
                myMonsterHPBar.setProgress(0.0);
                switchScreen("room1.fxml");
            } else {
                Double monsterHP = (myMonster.getHP() * 1.0) / myMonster.getStartHP();
                myMonsterHPBar.setProgress((myMonster.getHP() * 1.0) / myMonster.getStartHP());
            }
        }
        if (myItems.contains(RoomItem.MONSTER)) {
            int chanceToBlock = myHero.getMyChanceToBlock();
            if (chanceToBlock <= (myRand.nextInt(10) + 1)) {
                System.out.println("Monster attacking hero");
                myMonster.attack(myHero);
                myHeroHPBar.setProgress((myHero.getHP() * 1.0) / myHero.getStartHP());
                if (myHero.getHP() <= 0) {
                    switchScreen("finish_screen.fxml");
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
    private void useHealthPotion(ActionEvent event) {
        if (myHero.getHealthPotionCount() > 0) {
            int healingPoints = myHero.getHP() + 10;
            myHero.setHP(healingPoints);
            myHero.setHealPotionCount(-1);
            System.out.println("Health Potion used");
        } else {
            System.out.println("No health potions available");
        }
    }

    private void switchScreen(String FxmlName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(FxmlName));
            Parent root = loader.load();
            if (FxmlName.equals("finish_screen.fxml")) {
                FinishGameController finishGameController = loader.getController();
                finishGameController.setScreen("lost");
            } else {
                TestRoomController roomController = loader.getController();
                roomController.setStatsAfterBattle(myDungeon, myMediaPlayer, myHeroImage);
                myMediaPlayer.stop();
            }
            System.out.println("fxml was loaded.");
            Stage stage = (Stage) someRoot.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

}
