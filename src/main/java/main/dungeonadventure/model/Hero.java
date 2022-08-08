package main.dungeonadventure.model;

public class Hero extends DungeonCharacter {

    private final String myCharName;
    private final int myBlockChance;

    public Hero (final String theCharacterName, final int theHP,
                 final int theDmgMin, final int theDmgMax,
                 final int theAtkSpd, final int theHitRate,
                 int theBlockChance) {

        super(theHP, theDmgMin, theDmgMax, theAtkSpd, theHitRate);
        this.myCharName = theCharacterName;
        this.myBlockChance = theBlockChance;
    }

    public String getMyCharacterName() {
        return myCharName;
    }

    public int getMyChanceToBlock() {
        return myBlockChance;
    }

}
