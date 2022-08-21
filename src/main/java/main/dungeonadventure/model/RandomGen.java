package main.dungeonadventure.model;

import java.util.Random;

/**
 * Random Number Generator
 */
public class RandomGen implements RandomGenInt {

    /** Random object used for random number generation */
    private Random myRandom;

    /**
     * Creates Random number generator
     */
    public RandomGen() {
        myRandom = new Random();
    }


    /**
     * Returns a random number
     * @param theMax - max upperbound integer specified
     * @return - returns a random number between 0-theMax
     */
    public int nextInt(final int theMax) {
        return myRandom.nextInt(theMax);
    }
}
