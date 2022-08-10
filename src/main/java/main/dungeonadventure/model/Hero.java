package main.dungeonadventure.model;

public class Hero extends DungeonCharacter {

    private final String myCharName;
    private final int myBlockChance;

    private final HeroType myHeroType;
    private int myHealPotionCount;
    private int myVisionPotionCount;
    private int myPillarCount;

    public Hero (final HeroType theHeroType, final String theCharacterName, final int theHP,
                 final int theDmgMin, final int theDmgMax,
                 final int theAtkSpd, final int theHitRate,
                 int theBlockChance) {

        super(theHP, theDmgMin, theDmgMax, theAtkSpd, theHitRate);
        this.myCharName = theCharacterName;
        this.myBlockChance = theBlockChance;
        this.myHeroType = theHeroType;
        this.myHealPotionCount = 0;
        this.myVisionPotionCount = 0;
        this.myPillarCount = 0;
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


}
