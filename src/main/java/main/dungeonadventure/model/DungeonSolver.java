package main.dungeonadventure.model;


import java.awt.Point;
import java.util.HashSet;
import java.util.Stack;

/**
 * Class is used to transverse through an input dungeon to find out if
 * it is solvable. This means it can reach the exit and collect all
 * items required to successfully end the game.
 *
 * @author Luke Smith
 * @version 8-4-22
 */
public class DungeonSolver {

    /**
     * Navigates through dungeon, returns if it can reach end with all
     * pillars or not.
     * @param theDungeon input dungeon object.
     * @return is dungeon can be beat.
     */
    public static boolean isDungeonSolvable(final Dungeon theDungeon) {

        Room[][] rooms = theDungeon.getRooms();

        //Visited locations are added to set
        HashSet<Point> visited = new HashSet<>();
        //Position order added to stack for back tracking
        Stack<Point> posStack = new Stack<>();

        Point curPos = new Point(1,1);

        int pillarCounter = 0;
        boolean exitLocated = false;
        boolean enteranceLocated = false;

        posStack.add(curPos);

        while (!posStack.isEmpty()) {

//System.out.println("DEBUG - curPos: (" + curPos.x + ", " + curPos.y + ")");


            Room currentRoom = rooms[curPos.x][curPos.y];

            //Check if room is an enterance
            if (currentRoom.isEntrance() && !enteranceLocated) {

//System.out.println("DEBUG - ENTERANCE FOUND");
                enteranceLocated = true;
            }

            //Check if room is an exit
            if (currentRoom.isExit() && !exitLocated) {

//System.out.println("DEBUG - EXIT FOUND");
                exitLocated = true;
            }

            //Check if room contains a pillar
            if (currentRoom.containsPillar()
                && !visited.contains(curPos)) {

                pillarCounter++;
//System.out.println("DEBUG - PILLAR FOUND, COUNT: " + pillarCounter);
            }

            visited.add(curPos);

            if (currentRoom.isDoorOpen(Direction.SOUTH)
                && !visited.contains(new Point(curPos.x + 1,curPos.y))) {

                posStack.add(curPos);
                curPos = new Point(curPos.x + 1, curPos.y);

            } else if (currentRoom.isDoorOpen(Direction.EAST)
                && !visited.contains(new Point(curPos.x, curPos.y + 1))) {

                posStack.add(curPos);
                curPos = new Point(curPos.x, curPos.y + 1);

            } else if (currentRoom.isDoorOpen(Direction.NORTH)
                && !visited.contains(new Point(curPos.x - 1,curPos.y))) {

                posStack.add(curPos);
                curPos = new Point(curPos.x - 1, curPos.y);

            } else if (currentRoom.isDoorOpen(Direction.WEST)
                && !visited.contains(new Point(curPos.x,curPos.y - 1))) {

                posStack.add(curPos);
                curPos = new Point(curPos.x, curPos.y - 1);

            } else {
//System.out.println("DEBUG - POPPING CURRENT POSITION");
                curPos = posStack.pop();
            }

        }

        //Is the dungeon solvable?
        if (enteranceLocated && exitLocated
            && pillarCounter == theDungeon.getPillarCount()) {
//System.out.println("DEBUG - DUNGEON IS SOLVABLE");
            return true;
        }
//System.out.println("DEBUG - DUNGEON NOT SOLVABLE");
//System.out.println("_______________________________");
        return false;
    }

}
