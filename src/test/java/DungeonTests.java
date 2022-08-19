import static org.junit.jupiter.api.Assertions.*;
import main.dungeonadventure.model.*;
import org.junit.jupiter.api.*;
import java.awt.*;
import java.time.Duration;
import java.util.HashMap;

public class DungeonTests {

    private static Dungeon myDungeon;

    @BeforeAll
    @Timeout(1)
    static void makeDungeon() throws InterruptedException {
        myDungeon = new Dungeon();
    }

    @Test
    @DisplayName("Get Position starts at Beginning")
    void getPosBeg() {
        Point expectedPoint = new Point(1, 1);
        Point actualPoint = myDungeon.getHeroPosition();
        assertEquals(expectedPoint, actualPoint);
    }

    @Test
    @DisplayName("Set Pos at Start Test")
    void setPosAtStart() {
        Point pointExpected = new Point(1,1);
        myDungeon.setHeroPosition(pointExpected.x, pointExpected.y);
        Point pointActual = myDungeon.getHeroPosition();
        assertEquals(pointExpected, pointActual);
    }

    @Test
    @DisplayName("Set Pos at End Test")
    void setPosAtEnd() {
        Point pointExpected = new Point(4, 4);
        myDungeon.setHeroPosition(pointExpected.x, pointExpected.y);
        Point pointActual = myDungeon.getHeroPosition();
            assertEquals(pointExpected, pointActual);
    }

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

    @Test
    @DisplayName("Set Hero Throws Exception")
    void setHeroException() {
        assertThrows(IllegalArgumentException.class, () -> myDungeon.setHero(null));
    }

    @Test
    @DisplayName("Room Size is Correct")
    void roomSize() {
        int actualSize = myDungeon.getRooms().length;
        assertEquals(6, actualSize);
    }

    @Test
    @DisplayName("Pillar Count is set to 4")
    void test() {
        int actualCount = myDungeon.getPillarCount();
        assertEquals(4, actualCount);
    }

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
