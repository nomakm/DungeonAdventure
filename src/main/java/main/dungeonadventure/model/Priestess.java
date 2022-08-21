package main.dungeonadventure.model;

import main.dungeonadventure.controller.DungeonAdventureSQLDataBase;
import java.util.Random;

/**
 * Priestess object extending hero
 * @author Luke Smith
 * @author Micaela Nomakchteinsky
 * @author Michael Doan
 */
public class Priestess extends Hero {
    /** max possible heal points if healing */
    private final int myHealMax;
    /** min possible heal points if healing */
    private final int myHealMin;

    /**
     * Constructor for creating priestess
     * @param theCharacterName name of character
     */
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

        this.myHealMax = DungeonAdventureSQLDataBase.pullHeroValues(HeroType.PRIESTESS).get("HealMax");
        this.myHealMin = DungeonAdventureSQLDataBase.pullHeroValues(HeroType.PRIESTESS).get("HealMin");
    }

    /**
     * Overrides DungeonCharacter attack()
     * Rolls for a chance to heal self while attacking (Special)
     * @param theOpponent monster to attack
     */
    @Override
    public void attack(final DungeonCharacter theOpponent) {
        int chanceForSpecial = 2;
        if(myRand.nextInt(10) < chanceForSpecial) {
            System.out.println("Healed priestess special attack");
            setHP(getHP() + (myRand.nextInt(myHealMax - myHealMin + 1) + myHealMin));
        }
        super.attack(theOpponent);
    }
}
