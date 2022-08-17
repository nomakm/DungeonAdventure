package main.dungeonadventure.controller;

import main.dungeonadventure.model.MonsterType;

import java.sql.*;
import java.util.HashMap;

public class DungeonAdventureSQLDataBase {

    private static Connection myConnection;

    public static void startDB() {

        connect();
        createTable();
        placeValues();

    }

    public static void connect() {
        myConnection = null;

        //establish connection (creates db file if it does not exist :-)
        try {
            Class.forName("org.sqlite.JDBC");
            myConnection = DriverManager.getConnection("jdbc:sqlite:monsters.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println( "Opened database successfully" );

    }

    private static void createTable() {
        try {

            Statement stmt = myConnection.createStatement();

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
            myConnection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        System.out.println("Table created successfully");

    }

    private static void placeValues() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:monsters.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
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
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");


    }

    public static HashMap<String, Integer> pullValues(MonsterType theMonster) {
        Connection c = null;
        Statement stmt = null;
        HashMap<String, Integer> monsterData = new HashMap<>();

        try {
            c = DriverManager.getConnection("jdbc:sqlite:monsters.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

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






}
