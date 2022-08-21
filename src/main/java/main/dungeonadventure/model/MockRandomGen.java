package main.dungeonadventure.model;

/**
 * Mock Random Generator used for testing
 * @author Micaela Nomakchteinsky
 * @version 8-2022
 */
public class MockRandomGen implements RandomGenInt {

    /** Mock integer to be used for random */
    private int myInt;

    /**
     * Creates a Mock random generator
     * @param theInt - the int used as a mock random number
     */
    public MockRandomGen(final int theInt) {
        this.myInt = theInt;
    }

    /**
     * Mocks nextInt method of Random. Will return a mock random number
     * @param theMax - max integer bound specified
     * @return myInt - the int used as a mock random number
     */
    public int nextInt(final int theMax) {
        return myInt;
    }
}
