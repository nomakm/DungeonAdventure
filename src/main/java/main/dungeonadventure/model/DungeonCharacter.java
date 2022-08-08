package main.dungeonadventure.model;

public class DungeonCharacter {

    private final String myCharName;
    private int myHP;
    private final int myDmgMin;
    private final int myDmgMax;
    private final int myAtkSpd;
    private final int myHitRate;

    public DungeonCharacter(String myCharName, int myHP, int myDmgMin, int myDmgMax, int myAtkSpd, int myHitRate) {

        this.myCharName = myCharName;
        this.myHP = myHP;
        this.myDmgMin = myDmgMin;
        this.myDmgMax = myDmgMax;
        this.myAtkSpd = myAtkSpd;
        this.myHitRate = myHitRate;

    }

    public String getMyCharName() {
        return myCharName;
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
