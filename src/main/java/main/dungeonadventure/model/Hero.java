package main.dungeonadventure.model;

/**
 * Hero object that extends Dungeon character.
 */
public abstract class Hero extends DungeonCharacter {

    private final String myCharName;
    private final int myBlockChance;
    private final HeroType myHeroType;
    private int myHealPotionCount;
    private int myVisionPotionCount;
    private int myPillarCount;
    private int myBombCount;

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

    public int getHealthPotionCount() {
        return myHealPotionCount;
    }

    public void setHealPotionCount(int setAmount) {
        if (setAmount == 1 || setAmount == -1)  {
            myHealPotionCount += setAmount;
        } else {
            throw new IllegalArgumentException("Potion is either added or used");
        }
    }

    public int getVisionPotionCount() {
        return myVisionPotionCount;
    }

    public void setVisionPotionCount(int setAmount) {
        if (setAmount == 1 || setAmount == -1)  {
            myVisionPotionCount += setAmount;
        } else {
            throw new IllegalArgumentException("Potion is either added or used");
        }
    }

    public void setBombCount(int setAmount) {
        if (setAmount == 1 || setAmount == -1)  {
            myBombCount += setAmount;
        } else {
            throw new IllegalArgumentException("Bomb is either added or used");
        }
    }

    public int getBombCount() {
        return myBombCount;
    }

    public int getPillarCount() {
        return myPillarCount;
    }

    public void addPillarToInventory() {
            myPillarCount++;
    }


    public HeroType getHeroType() {
        return myHeroType;
    }

    public String getMyCharacterName() {
        return myCharName;
    }

    public int getMyChanceToBlock() {
        return myBlockChance;
    }

    public void setPillarCount(int thePillar) {
        if (thePillar == 1)  {
            myPillarCount += thePillar;
        } else if (myPillarCount > 4) {
            throw new IndexOutOfBoundsException("Too Many Pillars");
        } else {
            throw new IllegalArgumentException("Potion is only added");
        }
    }


}
