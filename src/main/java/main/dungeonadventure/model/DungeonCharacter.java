package main.dungeonadventure.model;

import java.io.Serializable;
import java.util.Random;

/**
 * Higher class for shared values between Hero and Monster classes.
 *  * @author Luke Smith
 *  * @author Micaela Nomakchteinsky
 *  * @author Michael Doan
 */
public abstract class DungeonCharacter implements Serializable {

    /** This integer is used for creating a random number from 0 - 100*/
    private static final int RAND_UPPERBOUND = 100;

    /** Character's health points */
    private int myHP;
    /** Minimum damage output character can do. */
    private final int myDmgMin;
    /** Maximum damage output character can do. */
    private final int myDmgMax;
    /** Character's attack speed Value 0 - 10 */
    private final int myAtkSpd;
    /** Chance for character to land a hit Value 0 - 10 */
    private final int myHitRate;
    /**  Character's starting HP. */
    private int myStartHP;
    /** Random object for random number generation in code.*/
    protected static RandomGenInt myRand = new RandomGen();


    /**
     * Dungeon character constructor.
     * @param theHP HP of character
     * @param theDmgMin Min possible damage output
     * @param theDmgMax Max possible damage output
     * @param theAtkSpd Attack speed
     * @param theHitRate Chance for character to land a hit
     */
    public DungeonCharacter(final int theHP, final int theDmgMin,
                            final int theDmgMax, final int theAtkSpd,
                            final int theHitRate) {

        this.myHP = theHP;
        this.myDmgMin = theDmgMin;
        this.myDmgMax = theDmgMax;
        this.myAtkSpd = theAtkSpd;
        this.myHitRate = theHitRate;
        this.myStartHP = theHP;

    }

    /**
     * Sets the Random Generator for the hero
     * @param theRandom - random generator to be used
     */
    public void setRandom(RandomGenInt theRandom) {
        myRand = theRandom;
    }


    /**
     * Gets minimum damage of character
     * @return Gets minimum damage of character
     */
    public int getDmgMin() {
        return myDmgMin;
    }

    /**
     * Gets maximum damage of character
     * @return Gets maximum damage of character
     */
    public int getDmgMax() {
        return myDmgMax;
    }

    /**
     * Gets hit of character
     * @return Gets hit rate of character
     */
    public int getHitRate() {
        return myHitRate;
    }


    /**
     * Gets HP of character
     * @return Gets HP of character
     */
    public int getHP() {
        return myHP;
    }


    /**
     * Sets HP of character
     * @param theHP HP to set
     */
    public void setHP(final int theHP) {
        if (theHP < 0) {
            this.myHP = 0;
        }
        this.myHP = theHP;
    }


    /**
     * Gets starting HP of character.
     * @return Character's initial HP
     */
    public int getStartHP() {
        return myStartHP;
    }


    /**
     * Gets character's attack speed
     * @return character's attack speed
     */
    public int getAtkSpd() {
        return myAtkSpd;

    }


    /**
     * Attacks an opponent. Rolls a random num to determine if the hit
     * will land.
     * @param theOpponent The Character to attack (Hero or Monster)
     */
    public void attack(final DungeonCharacter theOpponent) {
        if ((myHitRate * 10) > myRand.nextInt(RAND_UPPERBOUND)) {
            int damage = myRand.nextInt(((myDmgMax + 1)) - myDmgMin) + myDmgMin;
            theOpponent.setHP(theOpponent.getHP() - damage);
            System.out.println(theOpponent.getClass().getSimpleName() + " loses " + damage + " points");
        }
    }
}
