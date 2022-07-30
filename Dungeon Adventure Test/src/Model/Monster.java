package Model;

public class Monster extends DungeonCharacters {

    private final int myChanceToHeal;
    private final int myMinHealPoints;
    private final int myMaxHealPoints;

    public Monster(String myCharName, int myHP, int myDmgMin, int myDmgMax, int myAtkSpd, int myHitRate, int myChanceToHeal, int myMinHealPoints, int myMaxHealPoints) {
        super(myCharName, myHP, myDmgMin, myDmgMax, myAtkSpd, myHitRate);

        this.myChanceToHeal = myChanceToHeal;
        this.myMinHealPoints = myMinHealPoints;
        this.myMaxHealPoints = myMaxHealPoints;

    }

    public int getMyChanceToHeal() {
        return myChanceToHeal;
    }

    public int getMyMinHealPoints() {
        return myMinHealPoints;
    }

    public int getMyMaxHealPoints() {
        return myMaxHealPoints;
    }
}
