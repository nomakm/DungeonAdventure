import static org.junit.jupiter.api.Assertions.*;
import main.dungeonadventure.model.*;
import org.junit.jupiter.api.*;
import java.awt.*;
import java.time.Duration;
import java.util.HashMap;

/**
 * Tests class used for testing the Dungeon class
 * @author Micaela Nomakchteinsky
 * @version 8-2022
 */
public class DungeonTests {

    /** Dungeon used for tests */
    private static Dungeon myDungeon;


    /**
     * Creates dungeon for use in tests
     * @throws InterruptedException - throws exception if timed out. Timeout = 1s
     */
    @BeforeAll
    @Timeout(1)
    static void makeDungeon() throws InterruptedException {
        myDungeon = new Dungeon();
    }

    /**
     * Tests that when starting the dungeon the hero is set at the Top Left room
     */
    @Test
    @DisplayName("Get Position starts at Beginning")
    void getPosBeg() {
        Point expectedPoint = new Point(1, 1);
        Point actualPoint = myDungeon.getHeroPosition();
        assertEquals(expectedPoint, actualPoint);
    }


    /**
     * Tests setting the hero position at the start. Top left corner
     */
    @Test
    @DisplayName("Set Pos at Start Test")
    void setPosAtStart() {
        Point pointExpected = new Point(1,1);
        myDungeon.setHeroPosition(pointExpected.x, pointExpected.y);
        Point pointActual = myDungeon.getHeroPosition();
        assertEquals(pointExpected, pointActual);
    }


    /**
     * Tests setting the hero position at the end. Bottom right corner
     */
    @Test
    @DisplayName("Set Pos at End Test")
    void setPosAtEnd() {
        Point pointExpected = new Point(4, 4);
        myDungeon.setHeroPosition(pointExpected.x, pointExpected.y);
        Point pointActual = myDungeon.getHeroPosition();
            assertEquals(pointExpected, pointActual);
    }


    /**
     * Tests setPosition throws exception when setting hero position in buffer rows/columns
     */
    @Test
    @DisplayName("Set Position Throws Exception")
    void setPosException() {
        assertAll("Exceptions",
                () -> assertThrows(IllegalArgumentException.class, () -> myDungeon.setHeroPosition(0,0)),
                () -> assertThrows(IllegalArgumentException.class, () -> myDungeon.setHeroPosition(5,5)),
                () -> assertThrows(IllegalArgumentException.class, () -> myDungeon.setHeroPosition(0,5)),
                () -> assertThrows(IllegalArgumentException.class, () -> myDungeon.setHeroPosition(5,0))
        );
    }


    /**
     * Tests setting hero works for all types of heroes
     */
    @Test
    @DisplayName("Set Hero Works For All Types")
    void setHeroAllTypes() {
        Hero expectedHero = new Warrior("name");
        myDungeon.setHero(expectedHero);
        Hero actualHero = myDungeon.getHero();
        assertEquals(expectedHero, actualHero);

        expectedHero = new Thief("name");
        myDungeon.setHero(expectedHero);
        actualHero = myDungeon.getHero();
        assertEquals(expectedHero, actualHero);

        expectedHero = new Priestess("name");
        myDungeon.setHero(expectedHero);
        actualHero = myDungeon.getHero();
        assertEquals(expectedHero, actualHero);
    }


    /**
     * Tests setHero throws exception when heroType is null
     */
    @Test
    @DisplayName("Set Hero Throws Exception")
    void setHeroException() {
        assertThrows(IllegalArgumentException.class, () -> myDungeon.setHero(null));
    }


    /**
     * Tests that room size is correct 4x4 with 1 room buffer on each side
     */
    @Test
    @DisplayName("Room Size is Correct")
    void roomSize() {
        int actualSize = myDungeon.getRooms().length;
        assertEquals(6, actualSize);
    }


    /**
     * Tests that getPillarCount is set to 4
     */
    @Test
    @DisplayName("Pillar Count is set to 4")
    void test() {
        int actualCount = myDungeon.getPillarCount();
        assertEquals(4, actualCount);
    }


    /**
     * Tests correct neighbors are set to null for the top left corner room
     */
    @Test
    @DisplayName("Neighbors for Top Left corner return correct null values")
    void neighborTLCorners() {
        myDungeon.setHeroPosition(1, 1);
        HashMap<String, Room> neighbors = myDungeon.getNeighbors();
        assertAll(
                () -> assertEquals(null, neighbors.get("NORTHWEST")),
                () -> assertEquals(null, neighbors.get("NORTH")),
                () -> assertEquals(null, neighbors.get("SOUTHWEST")),
                () -> assertEquals(null, neighbors.get("WEST")),
                () -> assertEquals(null, neighbors.get("NORTHEAST"))
        );
    }


    /**
     * Tests correct neighbors are set to null for the top right corner room
     */
    @Test
    @DisplayName("Neighbors for Top Right corner return correct null values")
    void neighborTRCorners() {
        myDungeon.setHeroPosition(4, 1);
        HashMap<String, Room> neighbors = myDungeon.getNeighbors();
        assertAll(
                () -> assertEquals(null, neighbors.get("SOUTHWEST")),
                () -> assertEquals(null, neighbors.get("NORTHWEST")),
                () -> assertEquals(null, neighbors.get("SOUTH")),
                () -> assertEquals(null, neighbors.get("SOUTHEAST")),
                () -> assertEquals(null, neighbors.get("WEST"))
        );
    }


    /**
     * Tests correct neighbors are set to null for the bottom left corner room
     */
    @Test
    @DisplayName("Neighbors for Bottom Left corner return correct null values")
    void neighborBLCorners() {
        myDungeon.setHeroPosition(1, 4);
        HashMap<String, Room> neighbors = myDungeon.getNeighbors();
        assertAll(
                () -> assertEquals(null, neighbors.get("NORTHEAST")),
                () -> assertEquals(null, neighbors.get("EAST")),
                () -> assertEquals(null, neighbors.get("SOUTHEAST")),
                () -> assertEquals(null, neighbors.get("NORTH")),//f
                () -> assertEquals(null, neighbors.get("NORTHWEST"))//f
        );
    }


    /**
     * Tests correct neighbors are set to null for the bottom right corner room
     */
    @Test
    @DisplayName("Neighbors for Bottom Right corner return correct null values")
    void neighborBRCorners() {
        myDungeon.setHeroPosition(4, 4);
        HashMap<String, Room> neighbors = myDungeon.getNeighbors();
        assertAll(
                () -> assertEquals(null, neighbors.get("SOUTHWEST")),
                () -> assertEquals(null, neighbors.get("SOUTH")),
                () -> assertEquals(null, neighbors.get("SOUTHEAST")),
                () -> assertEquals(null, neighbors.get("EAST")),
                () -> assertEquals(null, neighbors.get("NORTHEAST"))

        );
    }


    /**
     * Tests that doors do not open for top left corner room
     */
    @Test
    @DisplayName("Does not open Corner Doors")
    void cornerDoors() {
        myDungeon.setHeroPosition(1, 1);
        Boolean northDoor = myDungeon.getCurrentRoom().isDoorOpen(Direction.NORTH);
        Boolean westDoor = myDungeon.getCurrentRoom().isDoorOpen(Direction.WEST);
        assertAll(
                () -> assertEquals(false, northDoor),
                () -> assertEquals(false, westDoor)
        );
    }
}
