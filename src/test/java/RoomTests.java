import static org.junit.jupiter.api.Assertions.*;
import main.dungeonadventure.model.*;
import org.junit.jupiter.api.*;

/**
 * Test class to test Room class
 * @author Micaela Nomakchteinsky
 * @version 8-2022
 */
public class RoomTests {

    /** Dungeon used for tests */
    private static Dungeon myDungeon;
    /** Room used for tests */
    private static Room myRoom;


    /**
     * Creates dungeon for use in tests
     * @throws InterruptedException - throws exception if timed out. Timeout = 1s
     */
    @BeforeAll
    @Timeout(1)
    static void buildDungeon() throws InterruptedException {
        myDungeon = new Dungeon();
        myRoom = myDungeon.getCurrentRoom();
    }


    /**
     * Tests adding a pit to the room
     */
    @Test
    @DisplayName("Adds Pit")
    void addPit() {
        myRoom.getItems().add(RoomItem.PIT);
        assertTrue(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.PIT));
    }


    /**
     * Tests removeItem method removes a pit from the room
     */
    @Test
    @DisplayName("Removes Pit")
    void removePit() {
        myRoom.getItems().add(RoomItem.PIT);
        myDungeon.getCurrentRoom().removeItem(RoomItem.PIT);
        assertFalse(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.PIT));
    }


    /**
     * Tests adding an HP Potion to the room
     */
    @Test
    @DisplayName("Adds HP Potion")
    void addHPPotion() {
        myRoom.getItems().add(RoomItem.HEALTH_POTION);
        assertTrue(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.HEALTH_POTION));
    }


    /**
     * Tests removeItem method removes an HP Potion from the room
     */
    @Test
    @DisplayName("Removes HP Potion")
    void removeHPPotion() {
        myRoom.getItems().add(RoomItem.HEALTH_POTION);
        myDungeon.getCurrentRoom().removeItem(RoomItem.HEALTH_POTION);
        assertFalse(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.HEALTH_POTION));
    }


    /**
     * Tests adding a Vision Potion to the room
     */
    @Test
    @DisplayName("Adds Vision Potion")
    void addVisPotion() {
        myRoom.getItems().add(RoomItem.VISION_POTION);
        assertTrue(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.VISION_POTION));
    }


    /**
     * Tests removeItem method removes a Vision Potion from the room
     */
    @Test
    @DisplayName("Removes Vision Potion")
    void removeVisPotion() {
        myRoom.getItems().add(RoomItem.VISION_POTION);
        myDungeon.getCurrentRoom().removeItem(RoomItem.VISION_POTION);
        assertFalse(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.VISION_POTION));
    }


    /**
     * Tests adding a Monster to the room
     */
    @Test
    @DisplayName("Adds Monster")
    void addMonster() {
        myRoom.getItems().add(RoomItem.MONSTER);
        assertTrue(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.MONSTER));
    }


    /**
     * Tests removeItem method removes a Monster from the room
     */
    @Test
    @DisplayName("Removes Monster")
    void removeMonster() {
        myRoom.getItems().add(RoomItem.MONSTER);
        myDungeon.getCurrentRoom().removeItem(RoomItem.MONSTER);
        assertFalse(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.MONSTER));
    }


    /**
     * Tests addPillar adds a Pillar to the room
     */
    @Test
    @DisplayName("Adds Pillar")
    void addPillar() {
        myRoom.addPillar();
        assertTrue(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.PILLAR));
    }


    /**
     * Tests removeItem method removes a Pillar from the room
     */
    @Test
    @DisplayName("Removes Pillar")
    void removePillar() {
        myRoom.addPillar();
        myDungeon.getCurrentRoom().removeItem(RoomItem.PILLAR);
        assertFalse(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.PILLAR));
    }


    /**
     * Tests adding a Bomb to the room
     */
    @Test
    @DisplayName("Adds Bomb")
    void addBomb() {
        myRoom.getItems().add(RoomItem.BOMB);
        assertTrue(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.BOMB));
    }


    /**
     * Tests removeItem method removes a Bomb from the room
     */
    @Test
    @DisplayName("Removes Bomb")
    void removeBomb() {
        myRoom.getItems().add(RoomItem.BOMB);
        myDungeon.getCurrentRoom().removeItem(RoomItem.BOMB);
        assertFalse(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.BOMB));
    }


    /**
     * Tests openDoor method opens the North Door
     */
    @Test
    @DisplayName("Opens North Door")
    void openNDoor() {
        myRoom.closeDoor(Direction.NORTH);
        myRoom.openDoor(Direction.NORTH);
        assertTrue(myRoom.isDoorOpen(Direction.NORTH));
    }


    /**
     * Tests closeDoor method closes the North Door
     */
    @Test
    @DisplayName("Closes North Door")
    void closeNDoor() {
        myRoom.openDoor(Direction.NORTH);
        myRoom.closeDoor(Direction.NORTH);
        assertFalse(myRoom.isDoorOpen(Direction.NORTH));
    }


    /**
     * Tests openDoor method opens the East Door
     */
    @Test
    @DisplayName("Opens East Door")
    void openEDoor() {
        myRoom.closeDoor(Direction.EAST);
        myRoom.openDoor(Direction.EAST);
        assertTrue(myRoom.isDoorOpen(Direction.EAST));
    }


    /**
     * Tests closeDoor method closes the East Door
     */
    @Test
    @DisplayName("Closes East Door")
    void closeEDoor() {
        myRoom.openDoor(Direction.EAST);
        myRoom.closeDoor(Direction.EAST);
        assertFalse(myRoom.isDoorOpen(Direction.EAST));
    }


    /**
     * Tests openDoor method opens the South Door
     */
    @Test
    @DisplayName("Opens South Door")
    void openSDoor() {
        myRoom.closeDoor(Direction.SOUTH);
        myRoom.openDoor(Direction.SOUTH);
        assertTrue(myRoom.isDoorOpen(Direction.SOUTH));
    }


    /**
     * Tests closeDoor method closes the South Door
     */
    @Test
    @DisplayName("Closes South Door")
    void closeSDoor() {
        myRoom.openDoor(Direction.SOUTH);
        myRoom.closeDoor(Direction.SOUTH);
        assertFalse(myRoom.isDoorOpen(Direction.SOUTH));
    }


    /**
     * Tests openDoor method opens the West Door
     */
    @Test
    @DisplayName("Opens West Door")
    void openWDoor() {
        myRoom.closeDoor(Direction.WEST);
        myRoom.openDoor(Direction.WEST);
        assertTrue(myRoom.isDoorOpen(Direction.WEST));
    }


    /**
     * Tests closeDoor method closes the West Door
     */
    @Test
    @DisplayName("Closes West Door")
    void closeWDoor() {
        myRoom.openDoor(Direction.WEST);
        myRoom.closeDoor(Direction.WEST);
        assertFalse(myRoom.isDoorOpen(Direction.WEST));
    }


    /**
     * Checks that isEntrance correctly identifies that a non-Entrance room is not the entrance
     */
    @Test
    @DisplayName("Checks non-Entrance Room")
    void checkEntrance() {
        myDungeon.setHeroPosition(1,2);
        myRoom = myDungeon.getCurrentRoom();
        assertFalse(myRoom.isEntrance());
    }


    /**
     * Checks that isExit correctly identifies that a non-Exit room is not the exit
     */
    @Test
    @DisplayName("Checks non-Exit Room")
    void checkExit() {
        myDungeon.setHeroPosition(1,2);
        myRoom = myDungeon.getCurrentRoom();
        assertFalse(myRoom.isExit());
    }

}
