package main.dungeonadventure.model;

/**
 * Factory used for building monster objects
 */

public class monsterFactory {

    //TODO: For each monster type, add it's specific parameters, will pull this info from database

    public static Monster buildMonster(final MonsterType theMonsterType) {

        //Default for testing, delete later
        Monster generatedMonster = new Monster(MonsterType.GOBLIN, 70,15,30, 5,8,4,20,40);

        //Monster theMonster;
        if (theMonsterType == MonsterType.GOBLIN) {
            //generatedMonster = new Monster();
        } else if (theMonsterType == MonsterType.SKELETON) {
            //generatedMonster = new Monster();
        } else if (theMonsterType == MonsterType.OGRE) {
            //generatedMonster = new Monster();
        } else {
            throw new IllegalArgumentException("Invalid Monster");
        }
        return generatedMonster;
    }

}
