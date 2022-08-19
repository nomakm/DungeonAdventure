package main.dungeonadventure.model;

public class MockRandomGen implements RandomGenInt {
    private int myInt;

    public MockRandomGen(int theInt) {
        this.myInt = theInt;
    }

    public int nextInt(int theMax) {
        return myInt;
    }
}
