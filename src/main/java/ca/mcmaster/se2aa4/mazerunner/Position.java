package ca.mcmaster.se2aa4.mazerunner;

public class Position { //Class to represent the position of the explorer
    private int x, y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Position move(Direction direction) { //Method to move the explorer in a given direction
        switch (direction.getCurrentDirection()) { //Switch statement to determine the direction
            case "NORTH": //If the direction is north, move the explorer up
                return new Position(x- 1, y);
            case "SOUTH": //If the direction is south, move the explorer down
                return new Position(x + 1, y);
            case "EAST": //If the direction is east, move the explorer right
                return new Position(x, y + 1);
            case "WEST": //If the direction is west, move the explorer left
                return new Position(x, y - 1);
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }
}
