package main.dungeonadventure.model;

public class Warrior extends Hero {
    private final int myCrushingBlowMax;
    private final int myCrushingBlowMin;

    public Warrior() {
        super("Warrior", 125, 35, 60, 4, 8, 2);
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
