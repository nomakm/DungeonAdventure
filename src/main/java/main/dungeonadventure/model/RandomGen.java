package main.dungeonadventure.model;

import java.util.Random;

public class RandomGen implements RandomGenInt {
    private Random myRandom;

    public RandomGen() {
        myRandom = new Random();
    }

    public int nextInt(int theMax) {
        return myRandom.nextInt(theMax);
    }
}
