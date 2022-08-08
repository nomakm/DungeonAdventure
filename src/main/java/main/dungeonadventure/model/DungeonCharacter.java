package main.dungeonadventure.model;

public class DungeonCharacter {


    private int myHP;
    private final int myDmgMin;
    private final int myDmgMax;
    private final int myAtkSpd;
    private final int myHitRate;

    public DungeonCharacter(final int theHP, final int theDmgMin,
                            final int theDmgMax, final int theAtkSpd,
                            final int theHitRate) {


        this.myHP = theHP;
        this.myDmgMin = theDmgMin;
        this.myDmgMax = theDmgMax;
        this.myAtkSpd = theAtkSpd;
        this.myHitRate = theHitRate;

    }



    public int getHP() {
        return myHP;
    }

    public void setHP(int HP) {
        this.myHP = HP;
    }

    public int getMyDmgMin() {
        return myDmgMin;
    }

    public int getMyDmgMax() {
        return myDmgMax;
    }

    public int getMyAtkSpd() {
        return myAtkSpd;

    }

    public int getMyHitRate() {
        return myHitRate;
    }

    public int attack(DungeonCharacter opponent) {
        int opponentHP = opponent.getHP();
        int opponentDmgMin = opponent.getMyDmgMin();
        int opponentDmgMax = opponent.getMyDmgMax();
        int opponentAtkSped = opponent.getMyAtkSpd();
        int opponentHitRate = opponent.getMyHitRate();


        return opponentHP;
    }
}
