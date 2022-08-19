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

    /** Dungeon object to be used throughout game */
    private static Dungeon myDungeon;


    /**
     * Starting method for launching game
     * @param theArgs command line args
     */
    public static void main(final String[] theArgs) {

        DungeonAdventureSQLDataBase.buildDB();
        //Start the GUI / Game
        DungeonAdventureGUI myGui = new DungeonAdventureGUI();
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


    /**
     * Saves game by serializing dungeon object
     * @param theFilePath File location/name to save to
     */
    protected static void saveGame(final File theFilePath) {
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


    /**
     * Loads in game from existing save
     * @param theFilePath File location/name to load from
     * @return
     */
    protected static boolean loadGame(final File theFilePath) {
        try {
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
