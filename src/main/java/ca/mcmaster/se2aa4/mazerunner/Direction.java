package ca.mcmaster.se2aa4.mazerunner;

public class Direction {
    public enum DirectionEnum {NORTH, EAST, SOUTH, WEST}; //Set up an enum to represent the four directions
    private DirectionEnum currentDirection; //The current direction of the explorer

    public Direction(String direction){ //Constructor to set the current direction
        this.currentDirection = DirectionEnum.valueOf(direction.toUpperCase());
    }

    public void turnLeft(){ //Method to turn the explorer left, updates the current direction
        if(currentDirection == DirectionEnum.NORTH) { //If the current direction is north, turn left to west
            currentDirection = DirectionEnum.WEST;
        } else if (currentDirection == DirectionEnum.EAST) { //If the current direction is east, turn left to north
            currentDirection = DirectionEnum.NORTH;
        } else if (currentDirection == DirectionEnum.SOUTH) { //If the current direction is south, turn left to east
            currentDirection = DirectionEnum.EAST;
        } else if (currentDirection == DirectionEnum.WEST) { //If the current direction is west, turn left to south
            currentDirection = DirectionEnum.SOUTH;
        }
    }

    public void turnRight(){ //Method to turn the explorer right, updates the current direction
        if(currentDirection == DirectionEnum.NORTH) { //If the current direction is north, turn right to east
            currentDirection = DirectionEnum.EAST;
        } else if (currentDirection == DirectionEnum.EAST) { //If the current direction is east, turn right to south
            currentDirection = DirectionEnum.SOUTH;
        } else if (currentDirection == DirectionEnum.SOUTH) { //If the current direction is south, turn right to west
            currentDirection = DirectionEnum.WEST;
        } else if (currentDirection == DirectionEnum.WEST) { //If the current direction is west, turn right to north
            currentDirection = DirectionEnum.NORTH;
        }
    }

    public String getCurrentDirection(){ //Method to get the current direction
        return currentDirection.name();
    }
}
