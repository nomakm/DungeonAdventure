package main.dungeonadventure.model;

public class Monster extends DungeonCharacter {

    private final MonsterType myMonsterType;
    private final int myChanceToHeal;
    private final int myMinHealPoints;
    private final int myMaxHealPoints;

    public Monster(final MonsterType theMonsterType, final int theHP,
                   final int theDmgMin, final int theDmgMax,
                   final int theAtkSpd, final int theHitRate,
                   final int theChanceToHeal, final int theMinHealPoints,
                   final int theMaxHealPoints) {

        super(theHP, theDmgMin, theDmgMax, theAtkSpd, theHitRate);
        this.myMonsterType = theMonsterType;
        this.myChanceToHeal = theChanceToHeal;
        this.myMinHealPoints = theMinHealPoints;
        this.myMaxHealPoints = theMaxHealPoints;

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

    public MonsterType getMonsterType() {
        return myMonsterType;
    }

}
