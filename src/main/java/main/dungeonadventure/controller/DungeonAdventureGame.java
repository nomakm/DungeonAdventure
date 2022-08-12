package main.dungeonadventure.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import main.dungeonadventure.model.*;
import main.dungeonadventure.view.DungeonAdventureGUI;

import java.io.*;

/**
 * Main class used for launching game GUI and handling communication between GUI
 * and Model.
 * @author Luke Smith
 */
public class DungeonAdventureGame {

    //private static Dungeon myDungeon;
    private static DungeonAdventureGUI myGui;

    public static void main(final String[] theArgs) {

        //Generate a Dungeon
        //myDungeon = new Dungeon();

        //Generate a Dungeon GUI object for commanding the GUI
        myGui = new DungeonAdventureGUI();
        //switch to chooseHeroControler
        //myGui.addDungeon(myDungeon);
        myGui.launchGUI();


    }

    public static void saveGame(File theFilePath) {
        try {
            //FileOutputStream fileOut = new FileOutputStream("/src/main/saves/game.ser");
            FileOutputStream fileOut = new FileOutputStream(theFilePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            Dungeon currentDungeon = TestRoomController.getDungeon();
            out.writeObject(currentDungeon);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /saves/game.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static boolean loadGame(File theFilePath) {
        try {
            //FileInputStream fileIn = new FileInputStream("/src/main/saves/game.ser");
            FileInputStream fileIn = new FileInputStream(theFilePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Dungeon loadedDungeon = (Dungeon) in.readObject();

System.out.println("DEBUG - Dungeon object loaded");

            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();
            return false;
        } catch (ClassNotFoundException c) {
            System.out.println("Dungeon class not found");
            c.printStackTrace();
            return false;
        }
        return true;
    }
}
