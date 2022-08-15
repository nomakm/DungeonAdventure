package main.dungeonadventure.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;

public class SQLDataBase {

    public static void main(String[] args) {
        SQLiteDataSource ds = null;

        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:monsters.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println( "Opened database successfully" );

        //now create a table
        String query = "CREATE TABLE IF NOT EXISTS monsters ( " +
                "MONSTER TEXT NOT NULL, " +
                "THEHP TEXT NOT NULL, " +
                "THEDMGMIN TEXT NOT NULL, " +
                "THEDMGMAX TEXT NOT NULL, " +
                "THEATKSPD TEXT NOT NULL, " +
                "THEHITRATE TEXT NOT NULL, " +
                "THECHANCETOHEAL TEXT NOT NULL, " +
                "THEMINHEALPOINTS TEXT NOT NULL, " +
                "THEMAXHEALPOINTS TEXT NOT NULL )";


        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query );
            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        System.out.println( "Created monsters table successfully" );

        //next insert three rows of data
        System.out.println( "Attempting to insert three rows into monsters table" );

        String query1 = "INSERT INTO monsters ( MONSTER, THEHP, THEDMGMIN, THEDMGMAX, THEATKSPD, THEHITRATE, THECHANCETOHEAL, THEMINHEALPOINTS, THEMAXHEALPOINTS ) " +
                "VALUES ( 'GREMLIN', '70', '15', '30', '5', '8', '4', '20', '40' )";
        String query2 = "INSERT INTO monsters ( MONSTER, THEHP, THEDMGMIN, THEDMGMAX, THEATKSPD, THEHITRATE, THECHANCETOHEAL, THEMINHEALPOINTS, THEMAXHEALPOINTS ) " +
                "VALUES ( 'SKELETON', '100', '30', '50', '3', '8', '3', '30', '50' )";
        String query3 = "INSERT INTO monsters ( MONSTER, THEHP, THEDMGMIN, THEDMGMAX, THEATKSPD, THEHITRATE, THECHANCETOHEAL, THEMINHEALPOINTS, THEMAXHEALPOINTS ) " +
                "VALUES ( 'OGRE', '200', '30', '60', '2', '6', '4', '30', '60' )";

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query1 );
            System.out.println( "1st executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query2 );
            System.out.println( "2nd executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query3 );
            System.out.println( "3nd executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        //now query the database table for all its contents and display the results
        System.out.println( "Selecting all rows from test table" );
        query = "SELECT * FROM MONSTERS";

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            //and print it
            while ( rs.next() ) {
                String monster = rs.getString("MONSTER" );
                String theHP = rs.getString( "THEHP" );
                String theDmgMin = rs.getString( "THEDMGMIN" );
                String theDmgMax = rs.getString( "THEDMGMAX" );
                String theAtkSpd = rs.getString( "THEATKSPD" );
                String theHitRate = rs.getString( "THEHITRATE" );
                String theChanceToHeal = rs.getString( "THECHANCETOHEAL" );
                String theMinHealPoints = rs.getString( "THEMINHEALPOINTS" );
                String theMaxHealPoints = rs.getString( "THEMAXHEALPOINTS" );

                System.out.println( "Result: Monster = " + monster +
                        ", THEHP = " + theHP + ", THEDMGMIN = "
                        + theDmgMin + ", THEDMGMAX = " + theDmgMax
                        + ", THEATKSPD = " + theAtkSpd + ", THEHITRATE = "
                        + theHitRate + ", THECHANCETOHEAL = " + theChanceToHeal
                        + ", THEMINHEALPOINTS = " + theMinHealPoints + ", THEMAXHEALPOINTS"
                        + theMaxHealPoints );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }
}
