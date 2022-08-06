package main.dungeonadventure.controller;

import javafx.scene.control.skin.TextInputControlSkin;
import main.dungeonadventure.model.*;
import main.dungeonadventure.view.DungeonAdventureGUI;

import java.awt.*;

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
        myDungeon = new Dungeon();

        //Generate a Dungeon GUI object for commanding the GUI
        myGui = new DungeonAdventureGUI();
        myGui.addDungeon(myDungeon);
        myGui.launchGUI();


    }

    public void moveHero(final Direction theDirection) {
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

    public Room getHeroRoom() {
        return myDungeon.getCurrentRoom();
    }

    public void createHero(final HeroType theHeroType,
                             final String theHeroName) {
        if (theHeroType == HeroType.WARRIOR) {
            myHero = new Warrior(theHeroName);
        } else if (theHeroType == HeroType.THIEF) {
            myHero = new Thief(theHeroName);
        } else if (theHeroType == HeroType.PRIESTESS) {
            myHero = new Priestess(theHeroName);
        } else {
            throw new IllegalArgumentException("Invalid HeroType");
        }

    }

//    public void monsterBattle() {
//        Monster monster = getHeroRoom().getMonster();
//
//        while (myHero.isAlive() && monster.isAlive()) {
//
//        }
//
//    }











}
