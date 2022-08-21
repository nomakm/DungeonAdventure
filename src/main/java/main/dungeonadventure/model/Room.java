package main.dungeonadventure.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Each room of a dungeon maze. Contains items, traps, monsters.
 * @author Luke Smith
 * @version 8-6-22
 */
public class Room implements Serializable {

    /** Value used for chance to spawn pit */
    private static final int CHANCE_FOR_PIT = 10;
    /** Value used for chance to spawn monster */
    private static final int CHANCE_FOR_MONSTER = 40;
    /** Value used for chance to spawn HP potion */
    private static final int CHANCE_FOR_HP_POTION = 20;
    /** Value used for chance to spawn vision potion */
    private static final int CHANCE_FOR_VISION_POTION = 15;
    /** Value used for chance to spawn vision potion */
    private static final int CHANCE_FOR_BOMB = 5;

    /** Items contained in room */
    final private HashSet<RoomItem> myRoomItems;
    /** Doors in room */
    final private HashMap<Direction, Boolean> myDoors;
    /** If room is an exit */
    private boolean myIsExit;
    /** If room is an entrance */
    private boolean myIsEntrance;
    /** Monster in room */
    private Monster myMonster;


    /**
     * Constructor for room object
     */
    public Room() {
        myRoomItems = new HashSet<>();
        myDoors = new HashMap<>();
        myIsExit = false;
        myIsEntrance = false;
        myMonster = null;

        generateRoomItems();
        generateDoors();
    }


    /**
     * Rolls random values to spawn items in room
     */
    private void generateRoomItems() {
        //Spawn items/entities in room.
        if (CHANCE_FOR_PIT > Dungeon.getRandomRoll()) {
            myRoomItems.add(RoomItem.PIT);
        }

        if (CHANCE_FOR_MONSTER > Dungeon.getRandomRoll()) {
            myRoomItems.add(RoomItem.MONSTER);
            generateMonster();
        }

        if (CHANCE_FOR_HP_POTION > Dungeon.getRandomRoll()) {
            myRoomItems.add(RoomItem.HEALTH_POTION);
        }

        if (CHANCE_FOR_VISION_POTION > Dungeon.getRandomRoll()) {
            myRoomItems.add(RoomItem.VISION_POTION);
        }

        if (CHANCE_FOR_BOMB > Dungeon.getRandomRoll()) {
            myRoomItems.add(RoomItem.BOMB);
        }

    }


    /**
     * Randomly generates a monster and places it in the room
     */
    private void generateMonster() {
        //Rolls a random value of total monster types
        int roll = Dungeon.RAND_GEN.nextInt(MonsterType.values().length);
        MonsterType type = MonsterType.values()[roll];
        myMonster = MonsterFactory.buildMonster(type);
    }


    /**
     * Returns monster object in room
     * @return Monster object in room
     */
    public Monster getMonster() {
        return myMonster;
    }


    /**
     * Generates door values, all closed by default.
     */
    public void generateDoors() {
        myDoors.put(Direction.NORTH, false);
        myDoors.put(Direction.SOUTH, false);
        myDoors.put(Direction.WEST, false);
        myDoors.put(Direction.EAST, false);
    }


    /**
     * Opens specific door
     * @param theDoorDirection Door to open
     */
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


    /**
     * Closes specific door
     * @param theDoorDirection Door to close
     */
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


    /**
     * Adds pillar to room
     */
    public void addPillar() {
        myRoomItems.add(RoomItem.PILLAR);
    }


    /**
     * Checks to see if pillar is in room
     * @return if pillar in room
     */
    public boolean containsPillar() {
        return myRoomItems.contains(RoomItem.PILLAR);
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


    /**
     * Sets whether room is entrance or not
     * @param theValue is entrance or not
     */
    public void setEntrance(final boolean theValue) {
        myIsEntrance = theValue;
        if (theValue) {
            myRoomItems.clear();
        }
    }


    /**
     * Checks to see if room is an entrance
     * @return is entrance?
     */
    public boolean isEntrance() {
        return myIsEntrance;
    }


    /**
     * Sets whether room is exit or not
     * @param theValue is exit or not
     */
    public void setExit(final boolean theValue) {
        myIsExit = theValue;
        if (theValue) {
            myRoomItems.clear();
        }
    }


    /**
     * Checks to see if room is an exit
     * @return is exit?
     */
    public boolean isExit() {
        return myIsExit;
    }


    /**
     * Checks to see if specific door is open
     * @param theDirection direction of door
     * @return if door is open
     */
    public boolean isDoorOpen(final Direction theDirection) {
        return myDoors.get(theDirection);
    }


    /**
     * Returns hashset of room items
     * @return room items
     */
    public HashSet<RoomItem> getItems() {
//System.out.println("DEBUG - ROOM CONTAINS: " + myRoomItems.toString());
        return myRoomItems;
    }


    /**
     * Removes item from room
     * @param theItem item to remove
     */
    public void removeItem(final RoomItem theItem) {
System.out.println("DEBUG - ATTEMPTING TO REMOVE " + theItem.toString());
        if (!myRoomItems.contains(theItem)) {
            throw new IllegalArgumentException("Item isn't in room.");
        }
        myRoomItems.remove(theItem);
    }

}
