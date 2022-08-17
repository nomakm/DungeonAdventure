package main.dungeonadventure.model;

import java.util.Random;

public class Warrior extends Hero {
    private final int myCrushingBlowMax;
    private final int myCrushingBlowMin;
    private final int mySurpriseAtk;

    public Warrior(final String theCharacterName) {

        //TODO These values should be retrieved from the database
        super(HeroType.WARRIOR, theCharacterName, 600, 35, 120, 4, 8, 2);

        //TODO These values should be retrieved from the database
        this.myCrushingBlowMax = 175;
        this.myCrushingBlowMin = 75;
        this.mySurpriseAtk = 4;
    }

    public int getMyCrushingBlowMax() {
        return myCrushingBlowMax;
    }

    public int getMyCrushingBlowMin() {
        return myCrushingBlowMin;
    }

    @Override
    public void attack(DungeonCharacter theOpponent) {
        Random rand = new Random();
        if (mySurpriseAtk > (rand.nextInt(10) + 1)) {
            System.out.println("Special Attack used");
            int damage = rand.nextInt(myCrushingBlowMax) + myCrushingBlowMin;
            theOpponent.setHP(theOpponent.getHP() - damage);
            System.out.println(theOpponent.getClass().descriptorString() + " loses " + damage + " points");
        } else {
            super.attack(theOpponent);
        }
    }
}
