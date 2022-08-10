package main.dungeonadventure.model;

public class Priestess extends Hero {
    private final int myHealMax;
    private final int myHealMin;

    public Priestess(final String theCharacterName) {
        super(HeroType.PRIESTESS, theCharacterName, 1000000, 25, 45, 5, 7, 3);

        //TODO Will need to get these values from the database
        this.myHealMax = 0;
        this.myHealMin = 0;
    }

    public int getMyHealMax() {
        return myHealMax;
    }

    public int getMyHealMin() {
        return myHealMin;
    }
}
