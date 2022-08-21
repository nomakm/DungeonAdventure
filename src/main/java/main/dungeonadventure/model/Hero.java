package main.dungeonadventure.model;

/**
 * Hero object that extends Dungeon character.
 * @author Luke Smith
 * @author Micaela Nomakchteinsky
 * @author Michael Doan
 */
public abstract class Hero extends DungeonCharacter {

    /** Character name */
    private final String myCharName;
    /** Chance to block incoming attack */
    private final int myBlockChance;
    /** Type of Hero */
    private final HeroType myHeroType;
    /** Number of healing potions in inventory */
    private int myHealPotionCount;
    /** Number of vision potions in inventory */
    private int myVisionPotionCount;
    /** Number of pillars in inventory */
    private int myPillarCount;
    /** Number of bombs in inventory */
    private int myBombCount;

    /**
     * Constructor for creating hero object
     * @param theHeroType type of hero
     * @param theCharacterName name of hero
     * @param theHP HP of hero
     * @param theDmgMin minimum possible damage
     * @param theDmgMax maximum possible damage
     * @param theAtkSpd Attack speed
     * @param theHitRate Hit rate
     * @param theBlockChance Chance to block
     */
    public Hero (final HeroType theHeroType, final String theCharacterName, final int theHP,
                 final int theDmgMin, final int theDmgMax,
                 final int theAtkSpd, final int theHitRate,
                 int theBlockChance) {

        super(theHP, theDmgMin, theDmgMax, theAtkSpd, theHitRate);
        this.myHeroType = theHeroType;
        this.myCharName = theCharacterName;
        this.myBlockChance = theBlockChance;
        this.myHealPotionCount = 0;
        this.myVisionPotionCount = 0;
        this.myPillarCount = 0;
        this.myBombCount = 0;
    }


    /**
     * @return Returns number of healing potions in inventory
     */
    public int getHealthPotionCount() {
        return myHealPotionCount;
    }


    /**
     * Subtracts or adds healing potion to inventory
     */
    public void setHealPotionCount(final int theAmount) {
        if (theAmount == 1 || theAmount == -1)  {
            myHealPotionCount += theAmount;
        } else {
            throw new IllegalArgumentException("Potion is either added or used");
        }
    }


    /**
     * @return Returns number of vision potions in inventory
     */
    public int getVisionPotionCount() {
        return myVisionPotionCount;
    }


    /**
     * Subtracts or adds vision potion to inventory
     */
    public void setVisionPotionCount(final int theAmount) {
        if (theAmount == 1 || theAmount == -1)  {
            myVisionPotionCount += theAmount;
        } else {
            throw new IllegalArgumentException("Potion is either added or used");
        }
    }


    /**
     * @return Returns number of bombs in inventory
     */
    public int getBombCount() {
        return myBombCount;
    }


    /**
     * Subtracts or adds bomb to inventory
     */
    public void setBombCount(final int theAmount) {
        if (theAmount == 1 || theAmount == -1)  {
            myBombCount += theAmount;
        } else {
            throw new IllegalArgumentException("Bomb is either added or used");
        }
    }


    /**
     * @return Returns number of pillars in inventory
     */
    public int getPillarCount() {
        return myPillarCount;
    }


    /**
     * Adds 1 pillar to inventory.
     */
    public void addPillarToInventory() {
            myPillarCount++;
    }


    /**
     * Returns hero type
     * @return type of hero
     */
    public HeroType getHeroType() {
        return myHeroType;
    }


    /**
     * Return character name
     * @return name of character
     */
    public String getMyCharacterName() {
        return myCharName;
    }


    /**
     * Returns chance to block
     * @return chance to block
     */
    public int getMyChanceToBlock() {
        return myBlockChance;
    }

}
