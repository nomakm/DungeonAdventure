package main.dungeonadventure.model;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Dungeon class used to hold overall game data. Holds dungeon maze
 * comprised of rooms and the position of the hero in the maze.
 *
 * @author Luke Smith
 * @Author Micaela Nomakchteinsky
 * @version 8-6-22
 */
public class Dungeon implements Serializable {

    /** Random object for random number generation in code.*/
    public static final Random RAND_GEN = new Random();
    /** This integer is used for creating a random number from 0 - 100*/
    private static final int RAND_UPPERBOUND = 100;
    /** Length of Dungeon rooms */
    private static final int DUNGEON_HEIGHT = 4;
    /** Width of Dungeon rooms */
    private static final int DUNGEON_WIDTH = 4;
    /** Total pillars on map */
    private static final int PILLAR_COUNT = 4;

    /** 2D array for storing rooms. Represents Dungeon map. */
    private Room[][] myRooms;
    /** Position of hero in maze. */
    Point myHeroPosition;
    /** Position of entrance. */
    Point myEntranceLocation;
    /** Player's hero character used in game. */
    private Hero myHero;


    /**
     * Constructs a solvable Dungeon map.
     */
    public Dungeon() {

        //Create dungeon with interconnecting rooms
        //(2 added for buffer space)
        myRooms = new Room[DUNGEON_HEIGHT + 2][DUNGEON_WIDTH + 2];
        myHero = null;
        do {
            buildDungeon();
        } while (!DungeonSolver.isDungeonSolvable(this));
    }

    /**
     * Gets a random roll used for game RNG system.
     * @return random value from 0 -> UPPERBOUND
     */
    public static int getRandomRoll() {
        return RAND_GEN.nextInt(RAND_UPPERBOUND);
    }


    /**
     * Builds a dungeon step by step.
     */
    private void buildDungeon() {
        generateRooms();
        generateEntrancesAndExits();
        generateDoorsInRooms();
        spawnPillars();
        findEntrance();
        setHeroPosition(myEntranceLocation.x, myEntranceLocation.y);
    }


    /**
     * Generates a room object in each position in the array.
     */
    private void generateRooms() {
        for (int row = 1; row < myRooms.length - 1; row++) {
            for (int col = 1; col < myRooms[0].length - 1; col++) {
                myRooms[row][col] = new Room();
            }
        }
    }


    /**
     * Places entrances and exits in specific rooms.
     */
    private void generateEntrancesAndExits() {
        //Set Entrance and Exit points of maze
        myRooms[1][1].setEntrance(true);
        myRooms[DUNGEON_HEIGHT][DUNGEON_WIDTH].setExit(true);
    }


    /**
     * Randomly places doors in rooms. Will not place a door leading
     * outside the dungeon space. A door placed in one direction will
     * have the adjacent room the door connects to also be open.
     * Ex: A room's north door is open, the room above it will have
     * it's south door open in return.
     */
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


    /**
     * Randomly spawns pillars in each room.
     */
    private void spawnPillars() {
        int spawnedPillars = 0;
        while (spawnedPillars < PILLAR_COUNT) {
            int x = RAND_GEN.nextInt(DUNGEON_HEIGHT);
            int y = RAND_GEN.nextInt(DUNGEON_WIDTH);

            if (!myRooms[x + 1][y + 1].containsPillar()) {
                myRooms[x + 1][y + 1].addPillar();
                spawnedPillars++;
            }

        }

    }


    /**
     * Sets the location of the room that is the entrance of the
     * dungeon.
     *
     * @param theLocation Location of room.
     */
    private void setEntrance(final Point theLocation) {
        //Check for out of bounds
        if (theLocation.x < 1 || theLocation.x > DUNGEON_WIDTH
            && theLocation.y < 1 || theLocation.y > DUNGEON_HEIGHT) {
            throw new IllegalArgumentException("Position of entrance is out of bounds");
        }
        myEntranceLocation = theLocation;

    }


    /**
     * Searches through dungeon for entrance.
     */
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


    /**
     * Returns position of hero in dungeon.
     * @return position of hero.
     */
    public Point getHeroPosition(){
        return myHeroPosition;
    }





    /**
     * Set's position of hero in the Dungeon.
     * @param theX value of room location, North-South axis.
     * @param theY value of room location, West-East axis.
     */
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
     * Set's hero object for dungeon.
     * @param theHero Hero object.
     */
    public void setHero(final Hero theHero) {
        if (theHero == null) {
            throw new IllegalArgumentException("Hero is null");
        } else {
            myHero = theHero;
        }
    }


    /**
     * Returns hero object used in game.
     * @return hero object used in game.
     */
    public Hero getHero() {
        return myHero;
    }


    /**
     * Return 2d array of rooms
     * @return 2d array of rooms
     */
    public Room[][] getRooms() {
        return myRooms;
    }


    /**
     * Returns how many pillars should be in the dungeon.
     * @return  how many pillars should be in the dungeon.
     */
    public int getPillarCount() {
        return PILLAR_COUNT;
    }


    /**
     * Returns the room object the Hero is located in.
     * @return Returns the room object the Hero is located in.
     */
    public Room getCurrentRoom() {
        return myRooms[myHeroPosition.x][myHeroPosition.y];
    }


    /**
     * Returns surrounding rooms of Hero's current room.
     * @return
     */
    public HashMap<String, Room> getNeighbors() {
        HashMap<String, Room> neighbors = new HashMap<>();
        neighbors.put("NORTHWEST",myRooms[myHeroPosition.x - 1][myHeroPosition.y - 1]);
        neighbors.put("NORTH",myRooms[myHeroPosition.x - 1][myHeroPosition.y]);
        neighbors.put("NORTHEAST",myRooms[myHeroPosition.x - 1][myHeroPosition.y + 1]);

        neighbors.put("WEST",myRooms[myHeroPosition.x][myHeroPosition.y - 1]);
        neighbors.put("CURR",myRooms[myHeroPosition.x][myHeroPosition.y]);
        neighbors.put("EAST",myRooms[myHeroPosition.x][myHeroPosition.y + 1]);

        neighbors.put("SOUTHWEST",myRooms[myHeroPosition.x + 1][myHeroPosition.y - 1]);
        neighbors.put("SOUTH",myRooms[myHeroPosition.x + 1][myHeroPosition.y]);
        neighbors.put("SOUTHEAST", myRooms[myHeroPosition.x + 1][myHeroPosition.y + 1]);

        return neighbors;
    }


    /**
     * Used to visualize door placements in Dungeon (Testing)
     * @return String of dungeon
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

}
