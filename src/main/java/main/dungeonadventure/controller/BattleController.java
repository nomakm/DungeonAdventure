package main.dungeonadventure.controller;

import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import main.dungeonadventure.model.*;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Random;

/**
 * Starts battle with monster
 * @Author Micaela Nomakchteinsky
 * @Author Luke Smith
 * @version 8-2022
 */
public class BattleController {

    /** Group representing hero with sword */
    @FXML
    private Group myHeroFight;
    /**
     * Label controls
     * myMonsterNameLabel - label with monster type as text
     * myHeroLabel - label with hero name as text
     * myHeroHPLabel - label displaying hero HP
     * myMonsterHPLabel - label displaying monster HP
     * noItemLabel - label displayed if hero does not contain an item
     */
    @FXML
    private Label myMonsterNameLabel, myHeroNameLabel, myHeroHPLabel, myMonsterHPLabel,
            myItemLabel;
    /**
     * ImageView controls
     * myBattleHero - Image of the hero
     * myBattleMonster - Image of the monster
     */
    @FXML
    private ImageView myBattleHero, myBattleMonster;
    /**
     * ProgressBar controls
     * myHeroHPBar - progress bar for hero
     * myMonsterHPBar - progress bar for monster
     */
    @FXML
    private ProgressBar myHeroHPBar, myMonsterHPBar;
    /** Button control used for attacking */
    @FXML
    private Button myAttackButton;
    /** First Anchor Pane in hierarchy is the parent root */
    @FXML
    private Parent myRoot;

    /** Game Dungeon */
    private static Dungeon myDungeon;
    /** Current Room hero is in */
    private Room myRoom;
    /** Game Hero */
    private Hero myHero;
    /** Room Monster*/
    private Monster myMonster;
    /** Items in the Room */
    private HashSet<RoomItem> myItems;
    /** Random Object used in battle */
    private Random myRand = new Random();
    /** Used to play Media */
    private MediaPlayer myMediaPlayer;
    /** Image used for Hero */
    private Image myHeroImage;
    /** Song Media for battle */
    private Media myMedia;
    /** Decimal Format to 10th digit */
    private DecimalFormat myDf;

    /**
     * Makes Hero attack the room Monster
     */
    @FXML
    private void attackClicked() {
            myAttackButton.setDisable(true);
            animateAttack();
            battle();
            final PauseTransition pt = new PauseTransition(Duration.millis(2500));
            pt.setOnFinished( ( ActionEvent event ) -> {
                myAttackButton.setDisable(false);
            });
            pt.play();
    }


    /**
     * Uses bomb on monster dealing 30 damage, checks if bomb is available and if monster is dead
     */
    @FXML
    private void useBomb() {
        if (myHero.getBombCount() > 0) {
            myHero.setBombCount(-1);
            myMonster.setHP(myMonster.getHP() - 30);
            myMonsterHPBar.setProgress((myMonster.getHP() * 1.0) / myMonster.getStartHP());
            myMonsterHPLabel.setText("-" + 30);
            if (myMonster.getHP() <= 0) {
                myMonsterHPBar.setProgress(0.0);
                switchScreen("room1.fxml");
            }
        } else {
            myItemLabel.setText("No bombs available");
        }
    }


    /**
     * Uses a health potion. Checks if hero has health potion and gives Hero +30 HP if so.
     * Sets progress bar and HP label for the hero.
     */
    @FXML
    private void useHealthPotion() {
        if (myHero.getHealthPotionCount() > 0) {
            int healingPoints = myHero.getHP() + 30;
            myHero.setHP(healingPoints);
            myHero.setHealPotionCount(-1);
            System.out.println("Health Potion used");
            myHeroHPLabel.setText("+30");
            myHeroHPBar.setProgress(Double.valueOf(
                    myDf.format((myHero.getHP() * 1.0) / myHero.getStartHP())));
        } else {
            myItemLabel.setText("No health potions available");
        }
    }


    /**
     * Sets room fields for screen changes. Sets battle screen with Hero and Monster images,
     * HP progress bars and labels.
     * @param theDungeon - Dungeon used in the game
     * @param theHeroImage - Image used displaying the hero
     * @param theMonsterImage - Image used displaying the monster
     */
    protected void setScreen(final Dungeon theDungeon, final Image theHeroImage,
                             final Image theMonsterImage) {
        this.myDungeon = theDungeon;
        this.myRoom = myDungeon.getCurrentRoom();
        this.myHero = myDungeon.getHero();
        this.myMonster = myRoom.getMonster();
        this.myItems = myRoom.getItems();
        this.myHeroImage = theHeroImage;

        myBattleHero.setImage(theHeroImage);
        myBattleMonster.setImage(theMonsterImage);
        myMonsterNameLabel.setText(myMonster.getMonsterType().toString() + " HP");
        myHeroNameLabel.setText(myHero.getMyCharacterName() + " HP");
        myDf = new DecimalFormat("#,#");
        myHeroHPLabel.setText("" + myHero.getHP());
        myMonsterHPLabel.setText("" + myMonster.getHP());
        myHeroHPBar.setProgress(getProgress(myHero));
        myMonsterHPBar.setProgress(getProgress(myMonster));
    }


    /**
     * Calculates the ratio of the current character HP vs their start HP level.
     * Used for progress bars
     * @param theCharacter - either the hero or room monster
     * @return Double - a Double representing the ratio of HP
     */
    private Double getProgress(final DungeonCharacter theCharacter) {
        return (theCharacter.getHP() * 1.0) / theCharacter.getStartHP();
    }


    /**
     * Initializes music when entering screen
     */
    public void initialize() {
        playMedia("/assets/battle.mp3");
    }


    /**
     * Goes through one round of battle;
     * hero attacks monster a set amount of times based on their attack speed
     * compared to the monster, then monster attacks hero, if alive.
     */
    private void battle() {
        int monsterSpd = myMonster.getAtkSpd();
        int heroSpd = myHero.getAtkSpd();
        int timesAtk = heroSpd / monsterSpd;
        if (timesAtk == 0) {
            timesAtk = 1;
        }
        System.out.println("Attack times = " + timesAtk);
        myDf = new DecimalFormat("#.#");
        for (int i = 0; i < timesAtk; i++) {
            if (myMonster.getHP() > 0) {
                System.out.println("Hero attacking monster");
                myHero.attack(myMonster);
                myMonster.heal();
                attackCharacter(myMonsterHPBar, myMonsterHPLabel, myMonster, "dungeon.fxml");
            }
        }
        if (myMonster.getHP() > 0) {
            int chanceToBlock = myHero.getMyChanceToBlock();
            if (chanceToBlock <= (myRand.nextInt(10) + 1)) {
                myMonster.attack(myHero);
                attackCharacter(myHeroHPBar, myHeroHPLabel, myHero, "game_over_screen.fxml");
            }
        }
    }


    /**
     * Sets HP progress bar and labels for the character getting attacked.
     * If character is dead the battle will end and switch to the specified screen.
     * @param theProgressBar - attacked character progress bar
     * @param theLabel - attacked character label
     * @param theCharacter - the Dungeon Character getting attacked
     * @param theFxmlName - the fxml file to switch screens
     */
    private void attackCharacter(final ProgressBar theProgressBar, final Label theLabel,
                                 final DungeonCharacter theCharacter, final String theFxmlName) {
        if (theCharacter.getHP() <= 0) {
            theProgressBar.setProgress(0.0);
            theLabel.setText("0");
            myItemLabel.setText(theCharacter.getClass().getSimpleName() + " was Defeated");
            final PauseTransition pt = new PauseTransition(Duration.millis(2500));
            pt.setOnFinished( ( ActionEvent event ) -> {
                switchScreen(theFxmlName);;
            });
            pt.play();
        } else {
            theProgressBar.setProgress(getProgress(theCharacter));
            theLabel.setText("" + theCharacter.getHP());
        }
    }


    /**
     * Animates hero attack on Monster lasting 1 second
     */
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


    /**
     * Switches screen to dungeon or game over screen based on specified fxml name
     * @param FxmlName
     */
    private void switchScreen(final String FxmlName) {
        try {
            myMediaPlayer.stop();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(FxmlName));
            Parent root = loader.load();
            if (FxmlName.equals("game_over_screen.fxml")) {
                GameOverController gameOverController = loader.getController();
                gameOverController.setScreen("lost");
            } else {
                RoomController roomController = loader.getController();
                roomController.endBattle();
            }
            Stage stage = (Stage) myRoot.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Plays specified song
     * @param theSongName - song to be played
     */
    private void playMedia(final String theSongName) {
        myMedia = new Media(getClass().getResource(theSongName).toString());
        myMediaPlayer = new MediaPlayer(myMedia);
        myMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        myMediaPlayer.play();
    }

}
