package main.dungeonadventure.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * Each room of a dungeon maze. Contains items, traps, monsters.
 * @author Luke Smith
 * @version 8-6-22
 */
public class Room {

    final private HashSet<String> myRoomItems;
    final private HashMap<Direction, Boolean> myDoors;
    private boolean myContainsPillar;
    private boolean myIsExit;
    private boolean myIsEntrance;
    private Monster myMonster;

    /** Value used for chance to spawn pit */
    private static final int CHANCE_FOR_PIT = 15;
    /** Value used for chance to spawn monster */
    private static final int CHANCE_FOR_MONSTER = 40;
    /** Value used for chance to spawn HP potion */
    private static final int CHANCE_FOR_HP_POTION = 20;
    /** Value used for chance to spawn vision potion */
    private static final int CHANCE_FOR_VISION_POTION = 15;

    public Room() {
        myRoomItems = new HashSet<>();
        myDoors = new HashMap<>();
        myIsExit = false;
        myContainsPillar = false;
        myIsEntrance = false;

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
            generateMonster();
        }

        if (CHANCE_FOR_HP_POTION < Dungeon.getRandomRoll()) {
            myRoomItems.add("HP_POTION");
        }

        if (CHANCE_FOR_VISION_POTION < Dungeon.getRandomRoll()) {
            myRoomItems.add("VISION_POTION");
        }


    }

    private void generateMonster() {
        //Rolls a random value of total monster types
        int roll = Dungeon.RAND_GEN.nextInt(MonsterType.values().length - 1) + 1;
        MonsterType type = MonsterType.values()[roll];
        myMonster = monsterFactory.buildMonster(type);
    }

    public Monster getMonster() {
        return myMonster;
    }

    public void generateDoors() {
        myDoors.put(Direction.NORTH, false);
        myDoors.put(Direction.SOUTH, false);
        myDoors.put(Direction.WEST, false);
        myDoors.put(Direction.EAST, false);
    }

    public void openDoor(final Direction theDoorDirection) {
        if (theDoorDirection == Direction.NORTH) {
            myDoors.replace(Direction.NORTH, true);
        }
        else if (theDoorDirection == Direction.SOUTH) {
            myDoors.replace(Direction.SOUTH, true);
        }
        else if (theDoorDirection == Direction.WEST) {
            myDoors.replace(Direction.WEST, true);
        }
        else if (theDoorDirection ==  Direction.EAST) {
            myDoors.replace(Direction.EAST, true);
        }
        else {
            throw new IllegalArgumentException("Invalid door direction");
        }
    }

    public void closeDoor(final Direction theDoorDirection) {
        if (theDoorDirection == Direction.NORTH) {
            myDoors.replace(Direction.NORTH, false);
        }
        else if (theDoorDirection == Direction.SOUTH) {
            myDoors.replace(Direction.SOUTH, false);
        }
        else if (theDoorDirection == Direction.WEST) {
            myDoors.replace(Direction.WEST, false);
        }
        else if (theDoorDirection == Direction.EAST) {
            myDoors.replace(Direction.EAST, false);
        }
        else {
            throw new IllegalArgumentException("Invalid door direction");
        }
    }


    public void setContainsPillar(final boolean theValue) {
        myContainsPillar = theValue;
    }

    public boolean containsPillar() {
        return myContainsPillar;
    }

    /**
     * Used for visualizing the entire maze/rooms.
     * @return String concatenation of maze
     */
    @Override
    public String toString() {
        StringBuilder roomVisual = new StringBuilder();

        //North Door
        roomVisual.append("#");
        if (myDoors.get(Direction.NORTH)) {
            roomVisual.append("-");
        } else {
            roomVisual.append("*");
        }
        roomVisual.append("#");

        roomVisual.append("\n");

        //West and East Door
        if (myDoors.get(Direction.WEST)) {
            roomVisual.append("|");
        } else {
            roomVisual.append("*");
        }

        //Place a P if there is a pillar
        if (containsPillar()) {
            roomVisual.append("P");
        } else {
            roomVisual.append("O");
        }


        if (myDoors.get(Direction.EAST)) {
            roomVisual.append("|");
        } else {
            roomVisual.append("*");
        }

        roomVisual.append("\n");

        //South door
        roomVisual.append("#");
        if (myDoors.get(Direction.SOUTH)) {
            roomVisual.append("-");
        } else {
            roomVisual.append("*");
        }
        roomVisual.append("#");

        roomVisual.append("\n");

        return roomVisual.toString();
    }

    /**
     * Used with toString to get row representation of each room.
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

    public void setEntrance(final boolean theValue) {
        myIsEntrance = theValue;
    }

    public boolean isEntrance() {
        return myIsEntrance;
    }

    public void setExit(final boolean theValue) {
        myIsExit = theValue;
    }

    public boolean isExit() {
        return myIsExit;
    }

    public boolean isDoorOpen(final Direction theDirection) {
        return myDoors.get(theDirection);
    }

    //added by MN
    public HashSet<String> getItems() {
        return myRoomItems;
    }
}
