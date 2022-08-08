package main.dungeonadventure.model;

public class Thief extends Hero {
    private final int mySurpriseAtk;
    private final int myCaught;

    public Thief(final String theCharacterName) {
        super(theCharacterName, 75, 20, 40, 6, 8, 4);
        this.mySurpriseAtk = 4;
        this.myCaught = 2;
    }

    public int getMySurpriseAtk() {
        return mySurpriseAtk;
    }

    public int getMyCaught() {
        return myCaught;
    }
}
