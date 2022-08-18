package main.dungeonadventure.controller;

import main.dungeonadventure.model.HeroType;
import main.dungeonadventure.model.MonsterType;

import java.sql.*;
import java.util.HashMap;

/**
 * Class is used to build/access database for storing Hero and Monster values
 * depending on the type.
 * @author Luke Smith
 */
public class DungeonAdventureSQLDataBase {

    //Not really needed if database already exists, DB could be just edited
    //with an editor.
    public static void buildDB() {

        createMonstersTable();
        createHeroesTable();
        placeMonsterValues();
        placeHeroValues();

    }


    /**
     * Builds monsters table
     */
    private static void createMonstersTable() {
        Connection c = null;
        Statement stmt = null;

        try {

            c = DriverManager.getConnection("jdbc:sqlite:dungeon_adventure.db");
            stmt = c.createStatement();

            String query = "CREATE TABLE IF NOT EXISTS MONSTERS " +
                    "(" +
                    " MONSTER_TYPE  MonsterType         NOT NULL, " +
                    " HP            INT                 NOT NULL, " +
                    " MIN_DMG       INT                 NOT NULL, " +
                    " MAX_DMG       INT                 NOT NULL, " +
                    " ATK_SPD       INT                 NOT NULL, " +
                    " HIT_RATE      INT                 NOT NULL, " +
                    " HEAL_CHANCE   INT                 NOT NULL, " +
                    " MIN_HP        INT                 NOT NULL, " +
                    " MAX_HP        INT                 NOT NULL," +
                    "UNIQUE(MONSTER_TYPE)" +
                    ") ";

            stmt.executeUpdate(query);

            stmt.close();
            c.close();
//System.out.println("DEBUG - Monsters Table created successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }


    /**
     * Builds hero table
     */
    private static void createHeroesTable() {
        Connection c = null;
        Statement stmt = null;

        try {

            c = DriverManager.getConnection("jdbc:sqlite:dungeon_adventure.db");
            stmt = c.createStatement();

            String query = "CREATE TABLE IF NOT EXISTS HEROES " +
                    "(" +
                    " HERO_TYPE                 HeroType            ," +
                    " HP                        INT                 ," +
                    " MIN_DMG                   INT                 ," +
                    " MAX_DMG                   INT                 ," +
                    " ATK_SPD                   INT                 ," +
                    " HIT_RATE                  INT                 ," +
                    " BLOCK_CHANCE              INT                 ," +
                    " CRUSHING_BLOW_MAX         INT                 ," +
                    " CRUSHING_BLOW_MIN         INT                 ," +
                    " SURPRISE_ATTACK_CHANCE     INT                 ," +
                    " CAUGHT_CHANCE             INT                 ," +
                    " HEAL_MAX                  INT                 ," +
                    " HEAL_MIN                  INT                 ," +
                    "UNIQUE(HERO_TYPE)" +
                    ") ";

            stmt.executeUpdate(query);

            stmt.close();
            c.close();
//System.out.println("DEBUG - Heroes Table created successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }


    /**
     * Places monster values in monsters table
     */
    private static void placeMonsterValues() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dungeon_adventure.db");
            c.setAutoCommit(false);
//System.out.println("DEBUG - Opened monsters database successfully");
            stmt = c.createStatement();

            String sql = "";

            sql = "INSERT OR IGNORE INTO MONSTERS (" +
                    "MONSTER_TYPE, HP, MIN_DMG, MAX_DMG, ATK_SPD, HIT_RATE, HEAL_CHANCE, MIN_HP, MAX_HP" +
                    ") " +
                    "VALUES (" +
                    "'OGRE', 225, 20, 32, 10, 2, 1, 10, 30 " +
                    ");";
            stmt.executeUpdate(sql);

            sql = "INSERT OR IGNORE INTO MONSTERS (" +
                    "MONSTER_TYPE, HP, MIN_DMG, MAX_DMG, ATK_SPD, HIT_RATE, HEAL_CHANCE, MIN_HP, MAX_HP" +
                    ") " +
                    "VALUES (" +
                    "'SKELETON', 150, 10, 25, 2, 3, 2, 10, 30 " +
                    ");";
            stmt.executeUpdate(sql);

            sql = "INSERT OR IGNORE INTO MONSTERS (" +
                    "MONSTER_TYPE, HP, MIN_DMG, MAX_DMG, ATK_SPD, HIT_RATE, HEAL_CHANCE, MIN_HP, MAX_HP" +
                    ") " +
                    "VALUES (" +
                    "'GOBLIN', 100, 18, 20, 7, 7, 1, 20, 70 " +
                    ");";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
//System.out.println("DEBUG - Monster Records created successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }


    /**
     * Places hero values in heroes table
     */
    private static void placeHeroValues() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dungeon_adventure.db");
            c.setAutoCommit(false);
//System.out.println("DEBUG - Opened heroes database successfully");
            stmt = c.createStatement();

            String sql = "";

            sql = "INSERT OR IGNORE INTO HEROES (" +
                    "HERO_TYPE, HP, MIN_DMG, MAX_DMG, ATK_SPD, HIT_RATE, BLOCK_CHANCE, " +
                    "CRUSHING_BLOW_MAX, CRUSHING_BLOW_MIN, SURPRISE_ATTACK_CHANCE" +
                    ") " +
                    "VALUES (" +
                    "'WARRIOR', 250, 30, 50, 4, 7, 2, " +
                    "120, 75, 2" +
                    ");";
            stmt.executeUpdate(sql);

            sql = "INSERT OR IGNORE INTO HEROES (" +
                    "HERO_TYPE, HP, MIN_DMG, MAX_DMG, ATK_SPD, HIT_RATE, BLOCK_CHANCE, " +
                    "SURPRISE_ATTACK_CHANCE, CAUGHT_CHANCE" +
                    ") " +
                    "VALUES (" +
                    "'THIEF', 175, 20, 35, 7, 8, 4, " +
                    "4, 2" +
                    ");";
            stmt.executeUpdate(sql);

            sql = "INSERT OR IGNORE INTO HEROES (" +
                    "HERO_TYPE, HP, MIN_DMG, MAX_DMG, ATK_SPD, HIT_RATE, BLOCK_CHANCE, " +
                    "HEAL_MAX, HEAL_MIN" +
                    ") " +
                    "VALUES (" +
                    "'PRIESTESS', 175, 25, 45, 5, 7, 4, " +
                    "20, 10" +
                    ");";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
//System.out.println("DEBUG - Hero Records created successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }



    }


    /**
     * Pulls values from monsters table depending on the type
     * @param theMonster Monster type to pull data from
     * @return HashMap of compiled monster values
     */
    public static HashMap<String, Integer> pullMonsterValues(final MonsterType theMonster) {
        Connection c = null;
        Statement stmt = null;
        HashMap<String, Integer> monsterData = new HashMap<>();

        try {
            c = DriverManager.getConnection("jdbc:sqlite:dungeon_adventure.db");
            c.setAutoCommit(false);
//System.out.println("DEBUG - Opened monster database successfully");

            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM MONSTERS WHERE MONSTER_TYPE = \'" +
                    theMonster.toString() +
                    "\' ;");


            monsterData.put("HP", rs.getInt("HP"));
            monsterData.put("DmgMin", rs.getInt("MIN_DMG"));
            monsterData.put("DmgMax", rs.getInt("MAX_DMG"));
            monsterData.put("AtkSpd", rs.getInt("ATK_SPD"));
            monsterData.put("HitRate", rs.getInt("HIT_RATE"));
            monsterData.put("ChanceToHeal", rs.getInt("HEAL_CHANCE"));
            monsterData.put("MinHealPoints", rs.getInt("MIN_HP"));
            monsterData.put("MaxHealPoints", rs.getInt("MAX_HP"));

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return monsterData;
    }


    /**
     * Pulls hero values depending on the type
     * @param theHero Hero type to pull data from
     * @return HashMap of compiled hero values
     */
    public static HashMap<String, Integer> pullHeroValues(final HeroType theHero) {
        Connection c = null;
        Statement stmt = null;
        HashMap<String, Integer> heroData = new HashMap<>();

        try {
            c = DriverManager.getConnection("jdbc:sqlite:dungeon_adventure.db");
            c.setAutoCommit(false);
//System.out.println("DEBUG - Opened hero database successfully");

            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM HEROES WHERE HERO_TYPE = \'" +
                    theHero.toString() +
                    "\' ;");


            heroData.put("HP", rs.getInt("HP"));
            heroData.put("DmgMin", rs.getInt("MIN_DMG"));
            heroData.put("DmgMax", rs.getInt("MAX_DMG"));
            heroData.put("AtkSpd", rs.getInt("ATK_SPD"));
            heroData.put("HitRate", rs.getInt("HIT_RATE"));
            heroData.put("BlockChance", rs.getInt("BLOCK_CHANCE"));
            heroData.put("CrushingBlowMax", rs.getInt("CRUSHING_BLOW_MAX"));
            heroData.put("CrushingBlowMin", rs.getInt("CRUSHING_BLOW_MIN"));
            heroData.put("SurpriseAttackChance", rs.getInt("SURPRISE_ATTACK_CHANCE"));
            heroData.put("CaughtChance", rs.getInt("CAUGHT_CHANCE"));
            heroData.put("HealMax", rs.getInt("HEAL_MAX"));
            heroData.put("HealMin", rs.getInt("HEAL_MIN"));

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return heroData;
    }

}
