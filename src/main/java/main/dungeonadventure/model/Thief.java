package main.dungeonadventure.model;

import main.dungeonadventure.controller.DungeonAdventureSQLDataBase;

import java.util.Random;

public class Thief extends Hero {
    private final int mySurpriseAtk;
    private final int myCaught;

    public Thief(final String theCharacterName) {
        //This is incredibly inefficient as each call to pullHeroValues
        //will create the same hashmap, can't call it before super though
        //for an easy fix. A new solution will have to be made.
        super(
                HeroType.THIEF,
                theCharacterName,
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.THIEF).get("HP"),
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.THIEF).get("DmgMin"),
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.THIEF).get("DmgMax"),
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.THIEF).get("AtkSpd"),
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.THIEF).get("HitRate"),
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.THIEF).get("BlockChance")

        );
        this.mySurpriseAtk = DungeonAdventureSQLDataBase.pullHeroValues(HeroType.THIEF).get("SurpriseAttackChance");
        this.myCaught = DungeonAdventureSQLDataBase.pullHeroValues(HeroType.THIEF).get("CaughtChance");
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
