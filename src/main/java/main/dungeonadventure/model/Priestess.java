package main.dungeonadventure.model;

public class Priestess extends Hero {
    private final int myHealMax;
    private final int myHealMin;

    public Priestess(int myHealMax, int myHealMin) {
        super("Priestess", 75, 25, 45, 5, 7, 3);
        this.myHealMax = myHealMax;
        this.myHealMin = myHealMin;
    }

    public int getMyHealMax() {
        return myHealMax;
    }

    public int getMyHealMin() {
        return myHealMin;
    }
}
