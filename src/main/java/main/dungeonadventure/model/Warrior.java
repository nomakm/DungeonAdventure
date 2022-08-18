package main.dungeonadventure.model;

import main.dungeonadventure.controller.DungeonAdventureSQLDataBase;

import java.util.Random;

public class Warrior extends Hero {
    private final int myCrushingBlowMax;
    private final int myCrushingBlowMin;
    private final int mySurpriseAtk;

    public Warrior(final String theCharacterName) {

        //This is incredibly inefficient as each call to pullHeroValues
        //will create the same hashmap, can't call it before super though
        //for an easy fix. A new solution will have to be made.
        super(
                HeroType.WARRIOR,
                theCharacterName,
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.WARRIOR).get("HP"),
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.WARRIOR).get("DmgMin"),
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.WARRIOR).get("DmgMax"),
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.WARRIOR).get("AtkSpd"),
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.WARRIOR).get("HitRate"),
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.WARRIOR).get("BlockChance")

        );

        this.myCrushingBlowMax = DungeonAdventureSQLDataBase.pullHeroValues(HeroType.WARRIOR).get("CrushingBlowMax");
        this.myCrushingBlowMin = DungeonAdventureSQLDataBase.pullHeroValues(HeroType.WARRIOR).get("CrushingBlowMin");
        this.mySurpriseAtk = DungeonAdventureSQLDataBase.pullHeroValues(HeroType.WARRIOR).get("SurpriseAttackChance");
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
