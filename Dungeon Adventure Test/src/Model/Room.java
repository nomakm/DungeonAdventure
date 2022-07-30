package Model;

public class Room {

    private Room NDirection;
    private Room SDirection;
    private Room WDirection;
    private Room EDirection;

    private final String Roomtype;

    public Room(String roomtype) {
        Roomtype = roomtype;
    }
    //private final String Pillar;


}
