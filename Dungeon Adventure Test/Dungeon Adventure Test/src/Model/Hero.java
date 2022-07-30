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
        private final int myCrushingBlowMax;
        private final int myCrushingBlowMin;

        public Warrior() {
            super("Warrior", 125, 35, 60, 4, 8, 2);
            this.myCrushingBlowMax = 175;
            this.myCrushingBlowMin = 75;
        }

        public int getMyCrushingBlowMax() {
            return myCrushingBlowMax;
        }

        public int getMyCrushingBlowMin() {
            return myCrushingBlowMin;
        }
    }

    public static class Priestess extends Hero {
        private final int myHealMax;
        private final int myHealMin;

        public Priestess(int myHealMax, int myHealMin) {
            super("Priestess", 75, 25, 45, 5, 7, 3);
            this.myHealMax = myHealMax;
            this.myHealMin = myHealMin;
        }

        public int getMyHealMax() {
            return myHealMax;
        }

        public int getMyHealMin() {
            return myHealMin;
        }
    }

    public static class Thief extends Hero {
        private final int mySurpiseAtk;
        private final int myCaught;

        public Thief() {
            super("Thief", 75, 20, 40, 6, 8, 4);
            this.mySurpiseAtk = 4;
            this.myCaught = 2;
        }

        public int getMySurpiseAtk() {
            return mySurpiseAtk;
        }

        public int getMyCaught() {
            return myCaught;
        }
    }
}

