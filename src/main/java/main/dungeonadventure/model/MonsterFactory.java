package main.dungeonadventure.model;

import main.dungeonadventure.controller.DungeonAdventureSQLDataBase;

import java.util.HashMap;

/**
 * Factory used for building monster objects
 * @author Luke Smith
 */

public class MonsterFactory {

    /**
     * Pulls data from the database depending on the monster to be built
     * @param theMonsterType Type of monster to build
     * @return Monster object built per monster type
     */
    public static Monster buildMonster(final MonsterType theMonsterType) {

        HashMap<String, Integer> monsterData = DungeonAdventureSQLDataBase.pullMonsterValues(theMonsterType);

        Monster generatedMonster = new Monster(theMonsterType,
                monsterData.get("HP"),
                monsterData.get("DmgMin"),
                monsterData.get("DmgMax"),
                monsterData.get("AtkSpd"),
                monsterData.get("HitRate"),
                monsterData.get("ChanceToHeal"),
                monsterData.get("MinHealPoints"),
                monsterData.get("MaxHealPoints")
        );

        return generatedMonster;
    }

}
