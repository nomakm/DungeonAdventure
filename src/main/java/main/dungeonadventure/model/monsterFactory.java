package main.dungeonadventure.model;

/**
 * Factory used for building monster objects
 */

public class monsterFactory {

    //TODO: For each monster type, add it's specific parameters

    public static Monster buildMonster(final MonsterType theMonsterType) {
        Monster theMonster;
        if (theMonsterType == MonsterType.GOBLIN) {
            theMonster = new Monster();
        } else if (theMonsterType == MonsterType.SKELETON) {
            theMonster = new Monster();
        } else if (theMonsterType == MonsterType.OGRE) {
            theMonster = new Monster();
        } else {
            throw new IllegalArgumentException("Invalid Monster");
        }
        return theMonster;
    }

}
