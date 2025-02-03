package ca.mcmaster.se2aa4.mazerunner;

public class Direction {
    public enum DirectionEnum {NORTH, EAST, SOUTH, WEST}; //Set up an enum to represent the four directions
    private DirectionEnum currentDirection; //The current direction of the explorer

    public Direction(String direction){ //Constructor to set the current direction
        this.currentDirection = DirectionEnum.valueOf(direction.toUpperCase());
    }

    public void turnLeft(){ //Method to turn the explorer left, updates the current direction
        switch(currentDirection){
            case NORTH:
                currentDirection = DirectionEnum.WEST;
                break;
            case EAST:
                currentDirection = DirectionEnum.NORTH;
                break;
            case SOUTH:
                currentDirection = DirectionEnum.EAST;
                break;
            case WEST:
                currentDirection = DirectionEnum.SOUTH;
                break;
        }
    }

    public void turnRight(){ //Method to turn the explorer right, updates the current direction
        switch(currentDirection){
            case NORTH:
                currentDirection = DirectionEnum.EAST;
                break;
            case EAST:
                currentDirection = DirectionEnum.SOUTH;
                break;
            case SOUTH:
                currentDirection = DirectionEnum.WEST;
                break;
            case WEST:
                currentDirection = DirectionEnum.NORTH;
                break;
        }
    }

    public String getCurrentDirection(){ //Method to get the current direction
        return currentDirection.name();
    }
}
