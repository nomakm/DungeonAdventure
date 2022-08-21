package main.dungeonadventure.model;

import main.dungeonadventure.controller.DungeonAdventureSQLDataBase;

import java.util.Random;

/**
 * Warrior object extending hero
 * @author Luke Smith
 * @author Micaela Nomakchteinsky
 * @author Michael Doan
 */
public class Warrior extends Hero {
    /** Maximum damage when special is used */
    private final int myCrushingBlowMax;
    /** Min damage when special is used */
    private final int myCrushingBlowMin;
    /** Chance to land special */
    private final int mySurpriseAtk;

    /**
     * Constructor for Warrior object
     * @param theCharacterName Name of character
     */
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

    /**
     * Gets Crushing Blow Min points of character
     * @return Crushing blow min points of character
     */
    public int getCrushingBlowMin() {
        return myCrushingBlowMin;
    }


    /**
     * Overrides DungeonCharacter attack()
     * Rolls for a chance to do crushing blow (Special)
     * @param theOpponent monster to attack
     */
    @Override
    public void attack(final DungeonCharacter theOpponent) {
        if (mySurpriseAtk > (myRand.nextInt(10) + 1)) {
            System.out.println("Special Attack used");
            int damage = myRand.nextInt(myCrushingBlowMax - myCrushingBlowMin + 1) + myCrushingBlowMin;
            theOpponent.setHP(theOpponent.getHP() - damage);
            System.out.println(theOpponent.getClass().descriptorString() + " loses " + damage + " points");
        } else {
            super.attack(theOpponent);
        }
    }
}
