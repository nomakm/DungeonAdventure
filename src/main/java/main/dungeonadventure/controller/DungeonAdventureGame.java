package main.dungeonadventure.controller;

import javafx.scene.control.skin.TextInputControlSkin;
import main.dungeonadventure.model.*;
import main.dungeonadventure.view.DungeonAdventureGUI;

import java.awt.*;
import java.io.*;

/**
 * Main class used for launching game GUI and handling communication between GUI
 * and Model.
 * @author Luke Smith
 */
public class DungeonAdventureGame {

    private static Dungeon myDungeon;
    private static DungeonAdventureGUI myGui;
    private Hero myHero;

    public static void main(final String[] theArgs) {

        //Generate a Dungeon
        //myDungeon = new Dungeon();

        //Generate a Dungeon GUI object for commanding the GUI
        myGui = new DungeonAdventureGUI();
        //switch to chooseHeroControler
        //myGui.addDungeon(myDungeon);
        myGui.launchGUI();


    }

    public void saveGame(String theFilePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream("/saves/game.ser");
            //FileOutputStream fileOut = new FileOutputStream(theFilePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(myDungeon);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /saves/game.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void loadGame(String theFilePath) {
        try {
            FileInputStream fileIn = new FileInputStream("/saves/game.ser");
            //FileInputStream fileIn = new FileInputStream(theFilePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            myDungeon = (Dungeon) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Dungeon class not found");
            c.printStackTrace();
            return;
        }
    }

    //moved to testRoomController
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
//    }

    //moved to testRoomController
//    public Room getHeroRoom() {
//        return myDungeon.getCurrentRoom();
//    }

    //moved to ChooseHeroController
//    public void createHero(final HeroType theHeroType,
//                             final String theHeroName) {
//        if (theHeroType == HeroType.WARRIOR) {
//            myHero = new Warrior(theHeroName);
//        } else if (theHeroType == HeroType.THIEF) {
//            myHero = new Thief(theHeroName);
//        } else if (theHeroType == HeroType.PRIESTESS) {
//            myHero = new Priestess(theHeroName);
//        } else {
//            throw new IllegalArgumentException("Invalid HeroType");
//        }
//
//    }

//    public void monsterBattle() {
//        Monster monster = getHeroRoom().getMonster();
//
//        while (myHero.isAlive() && monster.isAlive()) {
//
//        }
//
//    }




}
