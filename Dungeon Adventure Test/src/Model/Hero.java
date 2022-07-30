package Model;

public class Hero extends DungeonCharacters {

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

    public static class Warrior extends Hero {

        public Warrior() {
            super("Warrior", 125, 35, 60, 4, 8, 2);

        }
    }

    public static class Priestess extends Hero {

        public Priestess() {
            super("Priestess", 75, 25, 45, 5, 7, 3);
        }
    }

    public static class Thief extends Hero {

        public Thief() {
            super("Thief", 75, 20, 40, 6, 8, 4);
        }
    }
}

