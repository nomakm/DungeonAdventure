package main.dungeonadventure.model;

import java.util.Random;

public class Dungeon {

    /** 2D array for storing rooms. */
    private Room[][] myRooms;


    /** Random object for random number generation in code.*/
    private static final Random RAND_GEN = new Random();
    /** This integer is used for creating a random number from 0 - 100*/
    private static final int RAND_UPPERBOUND = 100;
    /** Length of Dungeon rooms */
    private static final int DUNGEON_LENGTH = 4;
    /** Width of Dungeon rooms */
    private static final int DUNGEON_WIDTH = 4;

    /**
     * Constructs a solvable Dungeon map.
     */
    public Dungeon() {

        //Create dungeon with interconnecting rooms
        //(2 added for buffer space)
        myRooms = new Room[DUNGEON_WIDTH + 2][DUNGEON_LENGTH + 2];

        do {
            buildDungeon();
        } while (dungeonIsSolvable());

    }

    /**
     * Verifies input dungeon is solvable. Dungeon can be navigated
     * from start to finish with all pillars collected.
     * @return if Dungeon can be solved.
     */
    private boolean dungeonIsSolvable() {

        return false;
    }

    /**
     *
     */
    private void buildDungeon() {
        int chanceToSpawnDoor = 50;
        for (int row = 1; row < myRooms.length - 1; row++) {
            for (int col = 1; col < myRooms[0].length - 1; col++) {

                myRooms[row][col] = new Room();

                //Chance to open north door
                if (chanceToSpawnDoor < getRandomRoll()
                    && row != 1 ) {
                    myRooms[row][col].openDoor("NORTH");
                    myRooms[row - 1][col].openDoor("SOUTH");
                }
                //Chance to open south door
                if (chanceToSpawnDoor < getRandomRoll()
                    && row != myRooms.length - 2) {
                    myRooms[row][col].openDoor("SOUTH");
                    //myRooms[row + 1][col].openDoor("NORTH");
                }
                //Chance to open west door
                if (chanceToSpawnDoor < getRandomRoll()
                    && col != 1 ) {
                    myRooms[row][col].openDoor("WEST");
                    myRooms[row][col - 1].openDoor("EAST");
                }
                //Chance to open east door
                if (chanceToSpawnDoor < getRandomRoll()
                    && col != myRooms[0].length - 2) {
                    myRooms[row][col].openDoor("EAST");
                    //myRooms[row][col + 1].openDoor("WEST");
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
            //dungeonMap.append("\n");
        }

        return dungeonMap.toString();
    }


}
