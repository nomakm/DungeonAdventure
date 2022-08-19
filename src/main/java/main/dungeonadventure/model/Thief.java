package main.dungeonadventure.model;

import main.dungeonadventure.controller.DungeonAdventureSQLDataBase;

import java.util.Random;


/**
 * Thief object extending hero
 * @author Luke Smith
 * @author Micaela Nomakchteinsky
 * @author Michael Doan
 */
public class Thief extends Hero {
    /** Surprise attack chance Value 0 - 10 */
    private final int mySurpriseAtk;
    /** Chance of getting caught value 0 - 10 */
    private final int myCaught;

    /**
     * Constructor for creating thief
     * @param theCharacterName name of character
     */
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


    /**
     * Overrides DungeonCharacter attack()
     * Rolls for a chance to attack several times (Special)
     * @param theOpponent monster to attack
     */
    @Override
    public void attack(final DungeonCharacter theOpponent) {
        int chance = (myRand.nextInt(10) + 1);
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
