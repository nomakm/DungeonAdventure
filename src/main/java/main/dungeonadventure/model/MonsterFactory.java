package main.dungeonadventure.model;

import main.dungeonadventure.controller.DungeonAdventureSQLDataBase;

import java.util.HashMap;

/**
 * Factory used for building monster objects
 */

public class MonsterFactory {

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
