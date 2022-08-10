package main.dungeonadventure.model;

/**
 * Factory used for building monster objects
 */

public class monsterFactory {



    public static Monster buildMonster(final MonsterType theMonsterType) {

        Monster generatedMonster;

        //TODO: For each monster type, add it's specific parameters, will pull this info from database
        if (theMonsterType == MonsterType.GOBLIN) {
            generatedMonster = new Monster(MonsterType.GOBLIN, 70,15,30, 5,8,4,20,40);
        } else if (theMonsterType == MonsterType.SKELETON) {
            generatedMonster = new Monster(MonsterType.SKELETON, 70,15,30, 5,8,4,20,40);
        } else if (theMonsterType == MonsterType.OGRE) {
            generatedMonster = new Monster(MonsterType.OGRE, 70,15,30, 5,8,4,20,40);
        } else {
            throw new IllegalArgumentException("Invalid Monster");
        }
        return generatedMonster;
    }

}
