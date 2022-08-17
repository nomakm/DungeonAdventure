package main.dungeonadventure.model;

import java.io.Serializable;
import java.util.Random;

/**
 * Higher class for shared values between Hero and Monster classes.
 */
public abstract class DungeonCharacter implements Serializable {

    /** Random object for random number generation in code.*/
    public static final Random RAND_GEN = new Random();
    /** This integer is used for creating a random number from 0 - 100*/
    private static final int RAND_UPPERBOUND = 100;

    /** Character's health points */
    private int myHP;
    /** Minimum damage output character can do. */
    private final int myDmgMin;
    /** Maximum damage output character can do. */
    private final int myDmgMax;
    /** Character's attack speed */
    private final int myAtkSpd;
    /** Chance for character to land a hit */
    private final int myHitRate;
    /**  Character's starting HP. */
    private int myStartHP;


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
     * Gets HP of character
     * @return Gets HP of character
     */
    public int getHP() {
        return myHP;
    }


    /**
     * Sets HP of character
     * @param theHP
     */
    public void setHP(final int theHP) {
        if (theHP < 0) {
            this.myHP = 0;
        }
        this.myHP = theHP;
    }

    /**
     * Gets starting HP of character.
     * @return
     */
    public int getStartHP() {
        return myStartHP;
    }


    /**
     * Gets minimum damage output.
     * @return
     */
    public int getDmgMin() {
        return myDmgMin;
    }


    /**
     * Gets maximum damage output.
     * @return
     */
    public int getDmgMax() {
        return myDmgMax;
    }


    /**
     * Gets character's attack speed
     * @return
     */
    public int getAtkSpd() {
        return myAtkSpd;

    }


    /**
     * Gets characters chance to hit.
     * @return
     */
    public int getHitRate() {
        return myHitRate;
    }

    public void attack(final DungeonCharacter theOpponent) {
        if ((myHitRate * 10) > RAND_GEN.nextInt(RAND_UPPERBOUND)) {
            int damage = RAND_GEN.nextInt(((myDmgMax + 1)) - myDmgMin) + myDmgMin;
            theOpponent.setHP(theOpponent.getHP() - damage);
            System.out.println(theOpponent.getClass().descriptorString() + " loses " + damage + " points");
        }
    }
}
