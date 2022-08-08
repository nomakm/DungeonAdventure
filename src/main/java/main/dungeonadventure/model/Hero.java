package main.dungeonadventure.model;

public class Hero extends DungeonCharacter {

    //    private final DungeonCharacters myHero;
    private final int myChancetoBlock;

    public Hero (String myCharName, int myHP, int myDmgMin, int myDmgMax, int myAtkSpd, int myHitRate, int myChancetoBlock) {

        super(myCharName, myHP, myDmgMin, myDmgMax, myAtkSpd, myHitRate);
//        this.myHero = new DungeonCharacters(myCharName, myHP, myDmgMin, myDmgMax, myAtkSpd, myHitRate);
        this.myChancetoBlock = myChancetoBlock;
    }

    public int getMyChancetoBlock() {
        return myChancetoBlock;
    }

}
