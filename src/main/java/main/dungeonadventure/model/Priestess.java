package main.dungeonadventure.model;

import java.util.Random;

public class Priestess extends Hero {
    private final int myHealMax;
    private final int myHealMin;

    public Priestess(final String theCharacterName) {
        super(HeroType.PRIESTESS, theCharacterName, 1000000, 25, 45, 5, 7, 3);

        //TODO Will need to get these values from the database
        this.myHealMax = 50;
        this.myHealMin = 10;
    }

    public int getMyHealMax() {
        return myHealMax;
    }

    public int getMyHealMin() {
        return myHealMin;
    }

    @Override
    public void attack(DungeonCharacter opponent) {
        Random rand = new Random();
        System.out.println("Healed priestess special attack");
        setHP((rand.nextInt(myHealMax) + myHealMin));
        super.attack(opponent);
    }
}
