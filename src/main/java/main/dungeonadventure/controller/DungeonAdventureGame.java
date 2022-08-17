package main.dungeonadventure.controller;

import main.dungeonadventure.model.*;
import main.dungeonadventure.view.DungeonAdventureGUI;
import java.io.*;

/**
 * Main class used for launching game GUI and handling communication between GUI
 * and Model. Holds main instance of dungeon that GUI controllers access to read/set
 * game data.
 * @author Luke Smith
 */
public class DungeonAdventureGame {

    private static DungeonAdventureGUI myGui;
    private static Dungeon myDungeon;

    public static void main(final String[] theArgs) {

        //Start the GUI / Game
        myGui = new DungeonAdventureGUI();
        DungeonAdventureSQLDataBase.startDB();
        myGui.launchGUI();

    }

    /**
     * Constructs a new Dungeon instance
     */
    protected static void buildNewDungeon() {
        myDungeon = new Dungeon();
    }

    /**
     * Returns game's Dungeon object.
     * @return Game Dungeon object.
     */
    protected static Dungeon getDungeon() {
        return myDungeon;
    }

    protected static void saveGame(File theFilePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(theFilePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(myDungeon);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    protected static boolean loadGame(File theFilePath) {
        try {
            //FileInputStream fileIn = new FileInputStream("/src/main/saves/game.ser");
            FileInputStream fileIn = new FileInputStream(theFilePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Dungeon loadedDungeon = (Dungeon) in.readObject();
            myDungeon = loadedDungeon;
System.out.println("DEBUG - Dungeon object loaded");

            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();
            return false;
        } catch (ClassNotFoundException c) {
System.out.println("DEBUG - Dungeon class not found");
            c.printStackTrace();
            return false;
        }
        return true;
    }
}
