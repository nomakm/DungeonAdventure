package main.dungeonadventure.model;

/**
 *  Monster object that extends Dungeon character.
 *  @author Luke Smith
 *  @author Micaela Nomakchteinsky
 *  @author Michael Doan
 */
public class Monster extends DungeonCharacter {

    /** Type of monster */
    private final MonsterType myMonsterType;
    /** monster's chance to heal */
    private final int myChanceToHeal;
    /** Minimum heal points if healing */
    private final int myMinHealPoints;
    /** Maximum heal points if healing */
    private final int myMaxHealPoints;

    /**
     * Constructor for monster
     * @param theMonsterType Type of monster
     * @param theHP Health points
     * @param theDmgMin min possible damage
     * @param theDmgMax max possible damage
     * @param theAtkSpd Attack speed
     * @param theHitRate Hit rate
     * @param theChanceToHeal Chance to heal
     * @param theMinHealPoints min heal points if healing
     * @param theMaxHealPoints max heal points if healing
     */
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

    /**
     * Gets minimum heal points of character
     * @return minimum heal points of character
     */
    public int getMinHealPoints() {
        return myMinHealPoints;
    }

    /**
     * Gets maximum heal points of character
     * @return maximum heal points of character
     */
    public int getMaxHealPoints() {
        return myMaxHealPoints;
    }

    /**
     * Returns what type of monster
     * @return type of monster
     */
    public MonsterType getMonsterType() {
        return myMonsterType;
    }

    /**
     * Chance to heal monster
     * Rolls for a chance to heal
     */
    public void heal() {
        if (myChanceToHeal > myRand.nextInt(10) + 1) {
            int heal = myRand.nextInt(myMaxHealPoints - myMinHealPoints + 1) + myMinHealPoints;
            setHP(getHP() + heal);
            System.out.println("Monster healing");
        }
    }

}
