package main.dungeonadventure.model;

public class Monster extends DungeonCharacter {

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

    public static class Ogre extends Monster {

        public Ogre() {
            super("Ogre", 200, 30, 60, 2, 6, 1, 30, 60);
        }
    }

    public static class Gremlin extends Monster {

        public Gremlin() {
            super("Gremlin", 70, 15, 30, 5, 8, 4, 20, 40);
        }
    }

    public static class Skeleton extends Monster {

        public Skeleton() {
            super("Skeleton", 100, 30, 50, 3, 8, 3, 30, 50);
        }
    }
}
