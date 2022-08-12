package main.dungeonadventure.model;

import java.util.Random;

public class Thief extends Hero {
    private final int mySurpriseAtk;
    private final int myCaught;

    public Thief(final String theCharacterName) {
        super(HeroType.THIEF, theCharacterName, 75, 20, 40, 6, 8, 4);
        this.mySurpriseAtk = 4;
        this.myCaught = 2;
    }

    public int getMySurpriseAtk() {
        return mySurpriseAtk;
    }

    public int getMyCaught() {
        return myCaught;
    }

    @Override
    public void attack(DungeonCharacter theOpponent) {
        Random rand = new Random();
        int chance = (rand.nextInt(10) + 1);
        if (mySurpriseAtk > chance) {
            System.out.println("Special Attack used");
            for (int i = 0; i < 2; i++) {
                super.attack(theOpponent);
            }
        } else if (myCaught <= chance) {
            System.out.println("Hero not caught");
            super.attack(theOpponent);
        }
    }
}
