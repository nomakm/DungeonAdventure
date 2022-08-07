package main.dungeonadventure.model;

import java.awt.*;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Dungeon class used to hold maze of game
 *
 * @author Luke Smith
 * @version 8-6-22
 */
public class Dungeon {


    /** 2D array for storing rooms. */
    private Room[][] myRooms;
    /** */
    Point myHeroPosition;
    /** Position of entrance*/
    Point myEntranceLocation;


    /** Random object for random number generation in code.*/
    public static final Random RAND_GEN = new Random();
    /** This integer is used for creating a random number from 0 - 100*/
    private static final int RAND_UPPERBOUND = 100;
    /** Length of Dungeon rooms */
    private static final int DUNGEON_HEIGHT = 7;
    /** Width of Dungeon rooms */
    private static final int DUNGEON_WIDTH = 5;
    /** Total pillars on map*/
    private static final int PILLAR_COUNT = 4;

    //singleton model?
    private static Dungeon uniqueInstance = new Dungeon();

    /**
     * Constructs a solvable Dungeon map.
     */
    public Dungeon() {

        //Create dungeon with interconnecting rooms
        //(2 added for buffer space)
        myRooms = new Room[DUNGEON_HEIGHT + 2][DUNGEON_WIDTH + 2];

        do {
            buildDungeon();
        } while (!DungeonSolver.isDungeonSolvable(this));

    }

    //singleton model
    public static Dungeon getInstance() {
        return uniqueInstance;
    }

    /**
     *
     */
    private void buildDungeon() {
        generateRooms();
        generateEntrancesAndExits();
        generateDoorsInRooms();
        spawnPillars();
        findEntrance();
    }

    private void spawnPillars() {
        int spawnedPillars = 0;
        while (spawnedPillars < PILLAR_COUNT) {
            int x = RAND_GEN.nextInt(DUNGEON_HEIGHT);
            int y = RAND_GEN.nextInt(DUNGEON_WIDTH);

            if (!myRooms[x + 1][y + 1].containsPillar()) {
                myRooms[x + 1][y + 1].setContainsPillar(true);
                spawnedPillars++;
            }

        }

    }

    private void generateRooms() {
        for (int row = 1; row < myRooms.length - 1; row++) {
            for (int col = 1; col < myRooms[0].length - 1; col++) {
                myRooms[row][col] = new Room();
            }
        }
    }

    private void generateEntrancesAndExits() {
        //Set Entrance and Exit points of maze
        myRooms[1][1].setEntrance(true);
        myRooms[DUNGEON_HEIGHT][DUNGEON_WIDTH].setExit(true);
    }

    private void generateDoorsInRooms() {
        int chanceToSpawnDoor = 50;
        for (int row = 1; row < myRooms.length - 1; row++) {
            for (int col = 1; col < myRooms[0].length - 1; col++) {
                //Chance to open north door
                if (chanceToSpawnDoor < getRandomRoll()
                        && row != 1 ) {
                    myRooms[row][col].openDoor(Direction.NORTH);
                    myRooms[row - 1][col].openDoor(Direction.SOUTH);
                }
                //Chance to open south door
                if (chanceToSpawnDoor < getRandomRoll()
                        && row != myRooms.length - 2) {
                    myRooms[row][col].openDoor(Direction.SOUTH);
                    myRooms[row + 1][col].openDoor(Direction.NORTH);
                }
                //Chance to open west door
                if (chanceToSpawnDoor < getRandomRoll()
                        && col != 1 ) {
                    myRooms[row][col].openDoor(Direction.WEST);
                    myRooms[row][col - 1].openDoor(Direction.EAST);
                }
                //Chance to open east door
                if (chanceToSpawnDoor < getRandomRoll()
                        && col != myRooms[0].length - 2) {
                    myRooms[row][col].openDoor(Direction.EAST);
                    myRooms[row][col + 1].openDoor(Direction.WEST);
                }

            }
        }

    }

    public static int getRandomRoll() {
        return RAND_GEN.nextInt(RAND_UPPERBOUND);
    }

    /**
     * USED FOR TESTING TO DELETE
     * @return
     */
    @Override
    public String toString() {
        StringBuilder dungeonMap = new StringBuilder();
        for (int row = 1; row < myRooms.length - 1; row++) {
            for (int i = 0; i < 3; i++) {
                for (int col = 1; col < myRooms[0].length - 1; col++) {
                    dungeonMap.append(myRooms[row][col].getRow(i));

                }
                dungeonMap.append("\n");
            }
        }

        return dungeonMap.toString();
    }

    private void setEntrance(Point theLocation) {
        //Check for out of bounds
        if (theLocation.x < 1 || theLocation.x > DUNGEON_WIDTH
            && theLocation.y < 1 || theLocation.y > DUNGEON_HEIGHT) {
            throw new IllegalArgumentException("Position of entrance is out of bounds");
        }
        myEntranceLocation = theLocation;

    }

    private void findEntrance() {
        Point entrance = null;
        for (int row = 1; row < myRooms.length - 1; row++) {
            for (int col = 1; col < myRooms[0].length - 1; col++) {
                if (myRooms[row][col].isEntrance()) {
                    entrance = new Point(row, col);
                    setEntrance(entrance);
                    break;
                }
            }
        }
        if (entrance == null) {
            throw new NoSuchElementException("Entrance could not be found");
        }
    }

    public Point getHeroPosition(){
        return myHeroPosition;
    }

    public void setHeroPosition(final int theX, final int theY) {
        Point location = new Point(theX, theY);
        //Check for out of bounds
        if (location.x < 1 || location.x > DUNGEON_WIDTH
                && location.y < 1 || location.y > DUNGEON_HEIGHT) {
            throw new IllegalArgumentException("Position of entrance is out of bounds");
        }
        myHeroPosition = location;
    }


    /**
     * Return 2d array of rooms
     * @return
     */
    public Room[][] getRooms() {
        return myRooms;
    }

    public int getPillarCount() {
        return PILLAR_COUNT;
    }

    public Room getCurrentRoom() {
        return myRooms[myHeroPosition.x][myHeroPosition.y];
    }
}
