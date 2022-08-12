package main.dungeonadventure.model;

import java.io.Serializable;
import java.util.Random;

public class DungeonCharacter implements Serializable {


    private int myHP;
    private final int myDmgMin;
    private final int myDmgMax;
    private final int myAtkSpd;
    private final int myHitRate;
    private final Random rand;
    private int myStartHP;

    public DungeonCharacter(final int theHP, final int theDmgMin,
                            final int theDmgMax, final int theAtkSpd,
                            final int theHitRate) {


        this.myHP = theHP;
        this.myDmgMin = theDmgMin;
        this.myDmgMax = theDmgMax;
        this.myAtkSpd = theAtkSpd;
        this.myHitRate = theHitRate;

        myStartHP = theHP;
        rand = new Random();
    }



    public int getHP() {
        return myHP;
    }

    public void setHP(int HP) {
        this.myHP = HP;
    }

    public int getStartHP() {
        return myStartHP;
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

    public void attack(DungeonCharacter opponent) {
        if ((myHitRate * 10) > rand.nextInt(100)) {
            int damage = rand.nextInt((myDmgMax + 1)) +  myDmgMin;
            opponent.setHP(opponent.getHP() - damage);
            System.out.println(opponent.getClass().descriptorString() + " loses " + damage + " points");
        }
    }
}
