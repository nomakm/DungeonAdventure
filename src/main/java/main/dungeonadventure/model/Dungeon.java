package main.dungeonadventure.model;

public class Dungeon {

    Room[][] myRooms;

    public Dungeon() {
        do {
            buildDungeon();
        } while (dungeonIsSolvable());

    }

    private boolean dungeonIsSolvable() {

        return false;
    }

    private void buildDungeon() {
    }
}
