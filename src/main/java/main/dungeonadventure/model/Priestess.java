package main.dungeonadventure.model;

import main.dungeonadventure.controller.DungeonAdventureSQLDataBase;

import java.util.Random;

public class Priestess extends Hero {
    private final int myHealMax;
    private final int myHealMin;

    public Priestess(final String theCharacterName) {
        //This is incredibly inefficient as each call to pullHeroValues
        //will create the same hashmap, can't call it before super though
        //for an easy fix. A new solution will have to be made.
        super(
                HeroType.PRIESTESS,
                theCharacterName,
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.PRIESTESS).get("HP"),
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.PRIESTESS).get("DmgMin"),
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.PRIESTESS).get("DmgMax"),
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.PRIESTESS).get("AtkSpd"),
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.PRIESTESS).get("HitRate"),
                DungeonAdventureSQLDataBase.pullHeroValues(HeroType.PRIESTESS).get("BlockChance")

        );

        //TODO Will need to get these values from the database
        this.myHealMax = DungeonAdventureSQLDataBase.pullHeroValues(HeroType.PRIESTESS).get("HealMax");
        this.myHealMin = DungeonAdventureSQLDataBase.pullHeroValues(HeroType.PRIESTESS).get("HealMin");
    }

    public int getMyHealMax() {
        return myHealMax;
    }

    public int getMyHealMin() {
        return myHealMin;
    }

    @Override
    public void attack(DungeonCharacter opponent) {
        Random rand = new Random();
        int chanceForSpecial = 2;
        if(rand.nextInt(10) < chanceForSpecial) {
            System.out.println("Healed priestess special attack");
            setHP(getHP() + (rand.nextInt(myHealMax - myHealMin + 1) + myHealMin));
        }
        super.attack(opponent);
    }
}
