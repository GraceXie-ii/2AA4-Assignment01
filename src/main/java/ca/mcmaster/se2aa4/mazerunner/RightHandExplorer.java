package ca.mcmaster.se2aa4.mazerunner;

public class RightHandExplorer implements Explorer {
    private Path path = new Path();

    @Override
    public boolean explore(Maze maze) { //Method to explore the maze 
       Position currentPosition = maze.getEntry(); 
       Direction currentDirection = new Direction("EAST");
       
       while (!(currentPosition.getX() == maze.getExit().getX() && currentPosition.getY() == maze.getExit().getY()))  { //While the explorer has not reached the exit
            Direction rightDirection = new Direction(currentDirection.getCurrentDirection());  //Create a new direction to the right of the current direction
            rightDirection.turnRight();
            Position rightPosition = currentPosition.move(rightDirection);
            
            if (maze.isPath(rightPosition)) { //If the right position is empty, turn right and move forward
                currentDirection.turnRight();
                path.addInstructions("R");
                currentPosition = rightPosition;
                path.addInstructions("F");
            } else {
                Position forwardPosition = currentPosition.move(currentDirection); //If the right position is not empty, move forward
                Direction leftDirection = new Direction(currentDirection.getCurrentDirection());
                leftDirection.turnLeft();
                Position leftPosition = currentPosition.move(leftDirection);
                
                if (maze.isPath(forwardPosition)) { //If the forward position is empty, move forward
                    currentPosition = forwardPosition;
                    path.addInstructions("F");
                } else if (maze.isPath(leftPosition)) { //If the forward position is not empty but the left position is empty, turn left and move forward
                    currentDirection.turnLeft();
                    path.addInstructions("L");
                    currentPosition = leftPosition;
                    path.addInstructions("F");
                } else { //If the forward and left positions are not empty, turn around and move forward
                    currentDirection.turnRight();
                    currentDirection.turnRight();
                    path.addInstructions("R");
                    path.addInstructions("R");
                }
            }
        }     
       return true;
    }

    public Path getPath() { //Method to get the path taken by the explorer
        return path;
    }
}