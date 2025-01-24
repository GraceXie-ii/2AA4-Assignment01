package ca.mcmaster.se2aa4.mazerunner;

public class Direction {
    public enum DirectionEnum {NORTH, EAST, SOUTH, WEST};
    private DirectionEnum currentDirection;

    public Direction(String direction){
        this.currentDirection = DirectionEnum.valueOf(direction.toUpperCase());
    }

    public void turnLeft(){
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

    public void turnRight(){
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

    public String getCurrentDirection(){
        return currentDirection.name();
    }
}
