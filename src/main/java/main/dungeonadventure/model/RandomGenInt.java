package main.dungeonadventure.model;

/**
 * Interface for random number generation - used for dependency injection of mock random generation
 */
public interface RandomGenInt {

        /**
         * Method to give next random number
         * @param theRandUpperbound - max upperbound integer for random generation
         * @return - a random or mock integer number
         */
        int nextInt(final int theRandUpperbound);
}
