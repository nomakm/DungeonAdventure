package main.dungeonadventure.model;

public class Warrior extends Hero {
    private final int myCrushingBlowMax;
    private final int myCrushingBlowMin;

    public Warrior(final String theCharacterName) {

        //TODO These values should be retrieved from the database
        super(HeroType.WARRIOR, theCharacterName, 125, 35, 60, 4, 8, 2);

        //TODO These values should be retrieved from the database
        this.myCrushingBlowMax = 175;
        this.myCrushingBlowMin = 75;
    }

    public int getMyCrushingBlowMax() {
        return myCrushingBlowMax;
    }

    public int getMyCrushingBlowMin() {
        return myCrushingBlowMin;
    }
}
