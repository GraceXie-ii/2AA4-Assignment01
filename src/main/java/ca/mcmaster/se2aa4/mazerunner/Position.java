package ca.mcmaster.se2aa4.mazerunner;

public class Position { //Class to represent the position of the explorer
    private int x, y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Position getPosition(){
        return new Position(x, y); //Return a new position object with the same coordinates
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Position move(Direction direction) { //Method to move the explorer in a given direction
        String relevantDirection = direction.getCurrentDirection(); //Get the current direction of the explorer
        if(relevantDirection.equals("NORTH")){ //If the direction is north, move the explorer up
            return new Position(x - 1, y);
        } else if(relevantDirection.equals("SOUTH")){ //If the direction is south, move the explorer down
            return new Position(x + 1, y);
        } else if(relevantDirection.equals("EAST")){ //If the direction is east, move the explorer right
            return new Position(x, y + 1);
        } else if(relevantDirection.equals("WEST")){ //If the direction is west, move the explorer left
            return new Position(x, y - 1);
        } else {
            throw new IllegalArgumentException("Invalid direction"); //Throw an exception if the direction is invalid
        }
    }
}
