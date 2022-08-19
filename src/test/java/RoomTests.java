import static org.junit.jupiter.api.Assertions.*;
import main.dungeonadventure.model.*;
import org.junit.jupiter.api.*;


public class RoomTests {
    private static Dungeon myDungeon;
    private static Room myRoom;

    @BeforeAll
    static void buildDungeon() {
        myDungeon = new Dungeon();
        myRoom = myDungeon.getCurrentRoom();
    }

    @Test
    @DisplayName("Adds Pit")
    void addPit() {
        myRoom.getItems().add(RoomItem.PIT);
        assertTrue(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.PIT));
    }

    @Test
    @DisplayName("Removes Pit")
    void removePit() {
        myRoom.getItems().add(RoomItem.PIT);
        myDungeon.getCurrentRoom().removeItem(RoomItem.PIT);
        assertFalse(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.PIT));
    }

    @Test
    @DisplayName("Adds HP Potion")
    void addHPPotion() {
        myRoom.getItems().add(RoomItem.HEALTH_POTION);
        assertTrue(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.HEALTH_POTION));
    }

    @Test
    @DisplayName("Removes HP Potion")
    void removeHPPotion() {
        myRoom.getItems().add(RoomItem.HEALTH_POTION);
        myDungeon.getCurrentRoom().removeItem(RoomItem.HEALTH_POTION);
        assertFalse(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.HEALTH_POTION));
    }

    @Test
    @DisplayName("Adds Vision Potion")
    void addVisPotion() {
        myRoom.getItems().add(RoomItem.VISION_POTION);
        assertTrue(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.VISION_POTION));
    }

    @Test
    @DisplayName("Removes Vision Potion")
    void removeVisPotion() {
        myRoom.getItems().add(RoomItem.VISION_POTION);
        myDungeon.getCurrentRoom().removeItem(RoomItem.VISION_POTION);
        assertFalse(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.VISION_POTION));
    }

    @Test
    @DisplayName("Adds Monster")
    void addMonster() {
        myRoom.getItems().add(RoomItem.MONSTER);
        assertTrue(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.MONSTER));
    }

    @Test
    @DisplayName("Removes Monster")
    void removeMonster() {
        myRoom.getItems().add(RoomItem.MONSTER);
        myDungeon.getCurrentRoom().removeItem(RoomItem.MONSTER);
        assertFalse(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.MONSTER));
    }

    @Test
    @DisplayName("Adds Pillar")
    void addPillar() {
        myRoom.addPillar();
        assertTrue(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.PILLAR));
    }

    @Test
    @DisplayName("Removes Pillar")
    void removePillar() {
        myRoom.addPillar();
        myDungeon.getCurrentRoom().removeItem(RoomItem.PILLAR);
        assertFalse(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.PILLAR));
    }

    @Test
    @DisplayName("Adds Bomb")
    void addBomb() {
        myRoom.getItems().add(RoomItem.BOMB);
        assertTrue(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.BOMB));
    }

    @Test
    @DisplayName("Removes Bomb")
    void removeBomb() {
        myRoom.getItems().add(RoomItem.BOMB);
        myDungeon.getCurrentRoom().removeItem(RoomItem.BOMB);
        assertFalse(() -> myDungeon.getCurrentRoom().getItems().contains(RoomItem.BOMB));
    }

    @Test
    @DisplayName("Opens North Door")
    void openNDoor() {
        myRoom.openDoor(Direction.NORTH);
        assertTrue(myRoom.isDoorOpen(Direction.NORTH));
    }

    @Test
    @DisplayName("Closes North Door")
    void closeNDoor() {
        myRoom.closeDoor(Direction.NORTH);
        assertFalse(myRoom.isDoorOpen(Direction.NORTH));
    }

    @Test
    @DisplayName("Opens East Door")
    void openEDoor() {
        myRoom.openDoor(Direction.EAST);
        assertTrue(myRoom.isDoorOpen(Direction.EAST));
    }

    @Test
    @DisplayName("Closes East Door")
    void closeEDoor() {
        myRoom.closeDoor(Direction.EAST);
        assertFalse(myRoom.isDoorOpen(Direction.EAST));
    }
    @Test
    @DisplayName("Opens South Door")
    void openSDoor() {
        myRoom.openDoor(Direction.SOUTH);
        assertTrue(myRoom.isDoorOpen(Direction.SOUTH));
    }

    @Test
    @DisplayName("Closes South Door")
    void closeSDoor() {
        myRoom.closeDoor(Direction.SOUTH);
        assertFalse(myRoom.isDoorOpen(Direction.SOUTH));
    }

    @Test
    @DisplayName("Opens West Door")
    void openWDoor() {
        myRoom.openDoor(Direction.WEST);
        assertTrue(myRoom.isDoorOpen(Direction.WEST));
    }

    @Test
    @DisplayName("Closes West Door")
    void closeWDoor() {
        myRoom.closeDoor(Direction.WEST);
        assertFalse(myRoom.isDoorOpen(Direction.WEST));
    }

    @Test
    @DisplayName("Checks non-Entrance Room")
    void checkEntrance() {
        myDungeon.setHeroPosition(1,2);
        myRoom = myDungeon.getCurrentRoom();
        assertFalse(myRoom.isEntrance());
    }

    @Test
    @DisplayName("Checks non-Exit Room")
    void checkExit() {
        myDungeon.setHeroPosition(1,2);
        myRoom = myDungeon.getCurrentRoom();
        assertFalse(myRoom.isExit());
    }
}
