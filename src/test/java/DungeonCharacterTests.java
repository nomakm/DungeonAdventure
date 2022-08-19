import static org.junit.jupiter.api.Assertions.*;

import main.dungeonadventure.controller.DungeonAdventureSQLDataBase;
import main.dungeonadventure.model.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;

import java.util.HashMap;


public class DungeonCharacterTests {
    private static Dungeon myDungeon;
    private static DungeonCharacter myOpponent;

    @BeforeAll
    @Timeout(1)
    static void createDungeon() throws InterruptedException {
        myDungeon = new Dungeon();
    }

    private void regAttackTests(DungeonCharacter attacker, DungeonCharacter victim, int randomInt) {
        RandomGenInt random = new MockRandomGen(randomInt);
        attacker.setRandom(random);
        attacker.attack(victim);
        assertEquals(victim.getStartHP() - attacker.getDmgMin() - randomInt, victim.getHP());
    }

    @Test
    @DisplayName("Regular Attack with Thief")
    void regAttackThief() {
        DungeonCharacter hero = new Thief("name");
        myOpponent = new MonsterFactory().buildMonster(MonsterType.GOBLIN);
        regAttackTests(hero, myOpponent, 3);
    }

    @Test
    @DisplayName("Special Attack with Thief")
    void specialAttackThief() {
        RandomGenInt random = new MockRandomGen(1);
        DungeonCharacter hero = new Thief("name");
        hero.setRandom(random);
        myOpponent = new MonsterFactory().buildMonster(MonsterType.GOBLIN);
        hero.attack(myOpponent);
        assertEquals((myOpponent.getStartHP() - (2 * hero.getDmgMin()) - 2 * 1), myOpponent.getHP());
    }

    @Test
    @DisplayName("Attack with Priestess")
    void regAttackPriestess() {
        DungeonCharacter hero = new Priestess("name");
        myOpponent = new MonsterFactory().buildMonster(MonsterType.GOBLIN);
        regAttackTests(hero, myOpponent, 1);
        assertEquals(hero.getStartHP() + 11, hero.getHP());
    }

    @Test
    @DisplayName("Regular Attack with Warrior")
    void regAttackWarrior() {
        DungeonCharacter hero = new Warrior("name");
        myOpponent = new MonsterFactory().buildMonster(MonsterType.GOBLIN);
        regAttackTests(hero, myOpponent, 3);
    }

    @Test
    @DisplayName("Special Attack with Warrior")
    void specialAttackWarrior() {
        RandomGenInt random = new MockRandomGen(0);
        DungeonCharacter hero = new Warrior("name");
        hero.setRandom(random);
        myOpponent = new MonsterFactory().buildMonster(MonsterType.GOBLIN);
        hero.attack(myOpponent);
        assertEquals(myOpponent.getStartHP() - ((Warrior) hero).getCrushingBlowMin(), myOpponent.getHP());
    }

    @Test
    @DisplayName("Monster Attacks Thief")
    void MAttackT() {
        DungeonCharacter hero = new Thief("name");
        myOpponent = new MonsterFactory().buildMonster(MonsterType.GOBLIN);
        regAttackTests(myOpponent, hero, 1);
    }

    @Test
    @DisplayName("Monster Attacks Priestess")
    void MAttackP() {
        DungeonCharacter hero = new Priestess("name");
        myOpponent = new MonsterFactory().buildMonster(MonsterType.GOBLIN);
        regAttackTests(myOpponent, hero, 1);
    }

    @Test
    @DisplayName("Monster Attacks Warrior")
    void MAttackW() {
        DungeonCharacter hero = new Warrior("name");
        myOpponent = new MonsterFactory().buildMonster(MonsterType.GOBLIN);
        regAttackTests(myOpponent, hero, 1);
    }

    @Test
    @DisplayName("Set HP Test Hero")
    void setHPTest() {
        Hero hero = new Warrior("name");
        hero.setHP(10);
        assertEquals(10, hero.getHP());
    }

    @Test
    @DisplayName("Set HP Test Monster")
    void setHPMonTest() {
        Monster monster = MonsterFactory.buildMonster(MonsterType.GOBLIN);
        monster.setHP(10);
        assertEquals(10, monster.getHP());
    }

    @Test
    @DisplayName("SQL Build Goblin Test")
    void goblinBuild() {
        monsterBuildTest(MonsterType.GOBLIN);
    }

    @Test
    @DisplayName("SQL Build Ogre Test")
    void ogreBuild() {
        monsterBuildTest(MonsterType.OGRE);
    }

    @Test
    @DisplayName("SQL Build Skeleton Test")
    void skeletonBuild() {
        monsterBuildTest(MonsterType.SKELETON);
    }

    private void monsterBuildTest(MonsterType theMonster) {
        HashMap<String, Integer> monsterData = DungeonAdventureSQLDataBase.pullMonsterValues(theMonster);
        Monster monster = MonsterFactory.buildMonster(theMonster);
        assertAll(
                () -> assertEquals(monsterData.get("HP"), monster.getStartHP()),
                () -> assertEquals(monsterData.get("DmgMin"), monster.getDmgMin()),
                () -> assertEquals(monsterData.get("DmgMax"), monster.getDmgMax()),
                () -> assertEquals(monsterData.get("AtkSpd"), monster.getAtkSpd()),
                () -> assertEquals(monsterData.get("HitRate"), monster.getHitRate()),
                () -> assertEquals(monsterData.get("MinHealPoints"), monster.getMinHealPoints()),
                () -> assertEquals(monsterData.get("MaxHealPoints"), monster.getMaxHealPoints())

        );
    }

    @Test
    @DisplayName("Set HP Potion Count Test")
    void setHPPotionCount() {
        Hero hero = new Warrior("name");
        hero.setHealPotionCount(1);
        assertEquals(1, hero.getHealthPotionCount());
        hero.setHealPotionCount(-1);
        assertEquals(0, hero.getHealthPotionCount());
    }

    @Test
    @DisplayName("Set HP Vision Count Test")
    void setVisPotionCount() {
        Hero hero = new Warrior("name");
        hero.setVisionPotionCount(1);
        assertEquals(1, hero.getVisionPotionCount());
        hero.setVisionPotionCount(-1);
        assertEquals(0, hero.getVisionPotionCount());
    }

    @Test
    @DisplayName("Set Bomb Count Test")
    void setBombPotionCount() {
        Hero hero = new Warrior("name");
        hero.setBombCount(1);
        assertEquals(1, hero.getBombCount());
        hero.setBombCount(-1);
        assertEquals(0, hero.getBombCount());
    }

    @Test
    @DisplayName("Set Pillar Count Test")
    void setPillarCount() {
        Hero hero = new Warrior("name");
        hero.addPillarToInventory();
        assertEquals(1, hero.getPillarCount());
    }

}
