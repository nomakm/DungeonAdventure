import static org.junit.jupiter.api.Assertions.*;

import main.dungeonadventure.controller.DungeonAdventureSQLDataBase;
import main.dungeonadventure.model.*;
import org.junit.jupiter.api.*;

import java.util.HashMap;

/**
 * Test class for testing DungeonCharacter class and its children
 * (Hero, Monster, Thief, Princess, Warrior) and the Monster Factory
 * @author Micaela Nomakchteinsky
 * @version 8-2022
 */
public class DungeonCharacterTests {

    /** Dungeon used for tests */
    private static Dungeon myDungeon;
    /** Opponent used for tests */
    private static DungeonCharacter myOpponent;


    /**
     * Creates dungeon for use in tests
     * @throws InterruptedException - throws exception if timed out. Timeout = 1s
     */
    @BeforeAll
    @Timeout(1)
    static void createDungeon() throws InterruptedException {
        myDungeon = new Dungeon();
    }


    /**
     * Simulates attack between attacker (monster/hero) and victim (monster/hero)
     * Makes sure HP of victim is correct based on hero attacker damage
     * @param attacker - the Dungeon Character attacking
     * @param victim - the Dungeon Character being attacked
     * @param randomInt - Integer value specified for a regular attack to happen.
     *                  Used for the mock random number
     */
    private void regAttackTests(DungeonCharacter attacker, DungeonCharacter victim, int randomInt) {
        RandomGenInt random = new MockRandomGen(randomInt);
        attacker.setRandom(random);
        attacker.attack(victim);
        assertEquals(victim.getStartHP() - attacker.getDmgMin() - randomInt, victim.getHP());
    }


    /**
     * Simulates regular attack on monster by the Thief
     */
    @Test
    @DisplayName("Regular Attack with Thief")
    void regAttackThief() {
        DungeonCharacter hero = new Thief("name");
        myOpponent = new MonsterFactory().buildMonster(MonsterType.GOBLIN);
        regAttackTests(hero, myOpponent, 3);
    }


    /**
     * Simulates special attack on monster by the Thief where the thief gets 2 attacks per attack
     */
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

    /**
     * Simulates regular attack on monster by the Priestess
     */
    @Test
    @DisplayName("Attack with Priestess")
    void regAttackPriestess() {
        DungeonCharacter hero = new Priestess("name");
        myOpponent = new MonsterFactory().buildMonster(MonsterType.GOBLIN);
        regAttackTests(hero, myOpponent, 3);
    }

    /**
     * Simulates special heal by the Priestess during attack
     */
    @Test
    @DisplayName("Heal with Priestess")
    void healAttackPriestess() {
        DungeonCharacter hero = new Priestess("name");
        myOpponent = new MonsterFactory().buildMonster(MonsterType.GOBLIN);
        regAttackTests(hero, myOpponent, 1);
        assertEquals(hero.getStartHP() + 11, hero.getHP());
    }


    /**
     * Simulates regular attack on monster by the Warrior
     */
    @Test
    @DisplayName("Regular Attack with Warrior")
    void regAttackWarrior() {
        DungeonCharacter hero = new Warrior("name");
        myOpponent = new MonsterFactory().buildMonster(MonsterType.GOBLIN);
        regAttackTests(hero, myOpponent, 3);
    }


    /**
     * Simulates special attack on monster by the Warrior where the warrior has a crushing blow
     */
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

    /**
     * Simulates regular attack on Thief by a monster
     */
    @Test
    @DisplayName("Monster Attacks Thief")
    void MAttackT() {
        DungeonCharacter hero = new Thief("name");
        myOpponent = new MonsterFactory().buildMonster(MonsterType.GOBLIN);
        regAttackTests(myOpponent, hero, 1);
    }


    /**
     * Simulates regular attack on Priestess by a monster
     */
    @Test
    @DisplayName("Monster Attacks Priestess")
    void MAttackP() {
        DungeonCharacter hero = new Priestess("name");
        myOpponent = new MonsterFactory().buildMonster(MonsterType.GOBLIN);
        regAttackTests(myOpponent, hero, 1);
    }


    /**
     * Simulates regular attack on Warrior by a monster
     */
    @Test
    @DisplayName("Monster Attacks Warrior")
    void MAttackW() {
        DungeonCharacter hero = new Warrior("name");
        myOpponent = new MonsterFactory().buildMonster(MonsterType.GOBLIN);
        regAttackTests(myOpponent, hero, 1);
    }


    /**
     * Tests if setting HP works on hero
     */
    @Test
    @DisplayName("Set HP Test Hero")
    void setHPTest() {
        Hero hero = new Warrior("name");
        hero.setHP(10);
        assertEquals(10, hero.getHP());
    }


    /**
     * Tests if setting HP works on monster
     */
    @Test
    @DisplayName("Set HP Test Monster")
    void setHPMonTest() {
        Monster monster = MonsterFactory.buildMonster(MonsterType.GOBLIN);
        monster.setHP(10);
        assertEquals(10, monster.getHP());
    }


    /**
     * Tests if setting Goblin stats from SQL works
     */
    @Test
    @DisplayName("SQL Build Goblin Test")
    void goblinBuild() {
        monsterBuildTest(MonsterType.GOBLIN);
    }


    /**
     * Tests if setting Ogre stats from SQL works
     */
    @Test
    @DisplayName("SQL Build Ogre Test")
    void ogreBuild() {
        monsterBuildTest(MonsterType.OGRE);
    }


    /**
     * Tests if setting Skeleton stats from SQL works
     */
    @Test
    @DisplayName("SQL Build Skeleton Test")
    void skeletonBuild() {
        monsterBuildTest(MonsterType.SKELETON);
    }


    /**
     * Compares monster stats set to database stats
     * @param theMonster - the Monster to test
     */
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


    /**
     * Tests adding and removing HP Potion
     */
    @Test
    @DisplayName("Set HP Potion Count Test")
    void setHPPotionCount() {
        Hero hero = new Warrior("name");
        hero.setHealPotionCount(1);
        assertEquals(1, hero.getHealthPotionCount());
        hero.setHealPotionCount(-1);
        assertEquals(0, hero.getHealthPotionCount());
    }


    /**
     * Tests adding and removing Vision Potion
     */
    @Test
    @DisplayName("Set HP Vision Count Test")
    void setVisPotionCount() {
        Hero hero = new Warrior("name");
        hero.setVisionPotionCount(1);
        assertEquals(1, hero.getVisionPotionCount());
        hero.setVisionPotionCount(-1);
        assertEquals(0, hero.getVisionPotionCount());
    }


    /**
     * Tests adding and removing bombs
     */
    @Test
    @DisplayName("Set Bomb Count Test")
    void setBombPotionCount() {
        Hero hero = new Warrior("name");
        hero.setBombCount(1);
        assertEquals(1, hero.getBombCount());
        hero.setBombCount(-1);
        assertEquals(0, hero.getBombCount());
    }


    /**
     * Tests adding Pillars
     */
    @Test
    @DisplayName("Set Pillar Count Test")
    void setPillarCount() {
        Hero hero = new Warrior("name");
        hero.addPillarToInventory();
        assertEquals(1, hero.getPillarCount());
    }

    @Test
    @DisplayName("Monster healing")
    void monsterHeal() {
        RandomGenInt rand = new MockRandomGen(0);
        Monster monster = new MonsterFactory().buildMonster(MonsterType.SKELETON);
        monster.setRandom(rand);
        monster.heal();
        assertEquals(160, monster.getHP());
    }
}
