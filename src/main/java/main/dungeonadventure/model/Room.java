package main.dungeonadventure.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Room {

    final private HashSet<String> myRoomItems;
    private boolean myContainsPillar;
    private HashMap<String, Boolean> myDoors;

    /** Value used for chance to spawn pit */
    private static final int CHANCE_FOR_PIT = 15;
    /** Value used for chance to spawn monster */
    private static final int CHANCE_FOR_MONSTER = 40;
    /** Value used for chance to spawn HP potion */
    private static final int CHANCE_FOR_HP_POTION = 20;
    /** Value used for chance to spawn vision potion */
    private static final int CHANCE_FOR_VISION_POTION = 15;

    public Room() {
        myDoors = new HashMap<>();
        myContainsPillar = false;
        myRoomItems = new HashSet<>();
        generateRoomItems();
        generateDoors();
    }

    private void generateRoomItems() {
        //Spawn items/entities in room.
        if (CHANCE_FOR_PIT < Dungeon.getRandomRoll()) {
            myRoomItems.add("PIT");
        }

        if (CHANCE_FOR_MONSTER < Dungeon.getRandomRoll()) {
            myRoomItems.add("MONSTER");
        }

        if (CHANCE_FOR_HP_POTION < Dungeon.getRandomRoll()) {
            myRoomItems.add("HP_POTION");
        }

        if (CHANCE_FOR_VISION_POTION < Dungeon.getRandomRoll()) {
            myRoomItems.add("VISION_POTION");
        }

    }

    public void generateDoors() {
        myDoors.put("NORTH", false);
        myDoors.put("SOUTH", false);
        myDoors.put("WEST", false);
        myDoors.put("EAST", false);
    }

    public void openDoor(final String theDoorDirection) {
        if (theDoorDirection.toUpperCase().equals("NORTH")) {
            myDoors.replace("NORTH",true);
        }
        else if (theDoorDirection.toUpperCase().equals("SOUTH")) {
            myDoors.replace("SOUTH",true);
        }
        else if (theDoorDirection.toUpperCase().equals("WEST")) {
            myDoors.replace("WEST",true);
        }
        else if (theDoorDirection.toUpperCase().equals("EAST")) {
            myDoors.replace("EAST",true);
        }
        else {
            throw new IllegalArgumentException("Invalid door direction");
        }
    }

    public void closeDoor(final String theDoorDirection) {
        if (theDoorDirection.toUpperCase().equals("NORTH")) {
            myDoors.replace("NORTH", false);
        }
        else if (theDoorDirection.toUpperCase().equals("SOUTH")) {
            myDoors.replace("SOUTH", false);
        }
        else if (theDoorDirection.toUpperCase().equals("WEST")) {
            myDoors.replace("WEST", false);
        }
        else if (theDoorDirection.toUpperCase().equals("EAST")) {
            myDoors.replace("EAST", false);
        }
        else {
            throw new IllegalArgumentException("Invalid door direction");
        }
    }



    public void setContainsPillar(final boolean theValue) {
        myContainsPillar = theValue;
    }

    public boolean getContainsPillar() {
        return myContainsPillar;
    }

    /**
     * USED FOR TESTING TO DELETE
     * @return
     */
    @Override
    public String toString() {
        StringBuilder roomVisual = new StringBuilder();

        //North Door
        roomVisual.append("#");
        if (myDoors.get("NORTH")) {
            roomVisual.append("-");
        } else {
            roomVisual.append("*");
        }
        roomVisual.append("#");

        roomVisual.append("\n");

        //West and East Door
        if (myDoors.get("WEST")) {
            roomVisual.append("|");
        } else {
            roomVisual.append("*");
        }

        roomVisual.append("O");

        if (myDoors.get("EAST")) {
            roomVisual.append("|");
        } else {
            roomVisual.append("*");
        }

        roomVisual.append("\n");

        //South door
        roomVisual.append("#");
        if (myDoors.get("SOUTH")) {
            roomVisual.append("-");
        } else {
            roomVisual.append("*");
        }
        roomVisual.append("#");

        roomVisual.append("\n");

        return roomVisual.toString();
    }

    /** USED FOR TESTING
     *
     */
    public String getRow(final int theRowNum) {
        if (theRowNum < 0 || theRowNum > 2)
            throw new IndexOutOfBoundsException("Index Out of Bounds");

        Scanner tempScanner = new Scanner(this.toString());
        String[] row = new String[3];
        row[0] = tempScanner.nextLine();
        row[1] = tempScanner.nextLine();
        row[2] = tempScanner.nextLine();


        return row[theRowNum];


    }
}
