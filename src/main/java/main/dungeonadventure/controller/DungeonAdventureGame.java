package main.dungeonadventure.controller;

import main.dungeonadventure.model.Dungeon;
import main.dungeonadventure.view.DungeonAdventureGUI;

public class DungeonAdventureGame {


    private static Dungeon myDungeon;
    private static DungeonAdventureGUI myGui;

    public static void main(final String[] theArgs) {

        //Generate a Dungeon
        myDungeon = new Dungeon();

        //Generate a Dungeon GUI object for commanding the GUI
        myGui = new DungeonAdventureGUI();
        myGui.addDungeon(myDungeon);
        myGui.launchGUI();


    }





}
